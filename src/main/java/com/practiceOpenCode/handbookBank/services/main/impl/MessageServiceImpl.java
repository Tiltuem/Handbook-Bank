package com.practiceOpenCode.handbookBank.services.main.impl;

import com.practiceOpenCode.handbookBank.exceptions.DuplicateFileException;
import com.practiceOpenCode.handbookBank.exceptions.NotFoundFileXmlException;
import com.practiceOpenCode.handbookBank.models.main.FileInfo;
import com.practiceOpenCode.handbookBank.models.main.Message;
import com.practiceOpenCode.handbookBank.models.security.User;
import com.practiceOpenCode.handbookBank.repositories.main.MessageRepository;
import com.practiceOpenCode.handbookBank.services.main.FileService;
import com.practiceOpenCode.handbookBank.services.main.MessageService;
import com.practiceOpenCode.handbookBank.services.security.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageRepository repository;

    @Autowired
    private FileService fileService;
    @Autowired
    private UserServiceImpl userService;

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Page<Message> getAllMessages(Pageable pageable) {
        return repository.findByDeleted(pageable, false);
    }

    @Override
    public Page<Message> searchMessages(Pageable pageable,
                                        String value,
                                        Boolean deleted,
                                        String column,
                                        String columnDate,
                                        String dateFrom,
                                        String dateBy) {
        String date = dateBy;
        if (dateBy.equals("")) {
            date = LocalDate.now().toString();
        }

        if (!value.equals("") || !dateFrom.equals("")) {
            return search(pageable, value, column, columnDate, deleted, dateFrom, date);
        }

        if (deleted) {
            return repository.findAll(pageable);
        }
        return repository.findByDeleted(pageable, false);
    }

    @Override
    @Transactional
    public void save(String date) {
        try {
            String nameFileZip = fileService.download(date);
            File fileXml = fileService.unpack(nameFileZip);
            checkFile(fileXml.getName());
            Files.deleteIfExists(Paths.get(nameFileZip));

            repository.save(setMessageInfo(fileXml));
        } catch (IOException e) {
            log.error("Ошибка при добавлении файла на стороне сервера");
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public void save(MultipartFile file) {
        String path = "src/main/resources/storage/" + file.getOriginalFilename();
        File fileXml = new File(path);

        if (path.endsWith(".xml")) {
            checkFile(file.getOriginalFilename());

            try (OutputStream os = Files.newOutputStream(fileXml.toPath())) {
                os.write(file.getBytes());
            } catch (IOException e) {
                log.error("Ошибка при добавлении файла на стороне сервера");
                throw new RuntimeException(e);
            }
        } else if (path.endsWith(".zip")) {
            try {
                file.transferTo(fileXml.getAbsoluteFile());
                fileXml = fileService.unpack(path);
                Files.deleteIfExists(Paths.get(path));
                checkFile(fileXml.getName());
            } catch (IOException e) {
                log.error("Ошибка при добавлении файла на стороне сервера");
                throw new RuntimeException(e);
            }
        } else {
            throw new NotFoundFileXmlException("Ошибка: неверный формат файла.");
        }

        repository.save(setMessageInfo(fileXml));
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        repository.deleteById(id);
    }

    @Override
    public Message getMessageById(long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public void recoveryById(long id) {
        Message message = repository.findById(id);
        message.setDeleted(false);

        repository.save(message);
    }

    private Page<Message> search(Pageable pageable,
                                 String value,
                                 String column,
                                 String columnDate,
                                 Boolean deleted,
                                 String dateFrom,
                                 String dateBy) {
        Query query;
        StringBuilder queryString = new StringBuilder("SELECT a FROM Message a WHERE a.");
        String myValue = value;
        if (!value.equals("")) {
            switch (column) {
                case "edNumber", "edAuthor", "edReceiver", "directoryVersion" -> queryString.append(column + " = ?1");
                default -> {
                    myValue = "%" + value + "%";
                    queryString.append(column + " LIKE ?1");
                }
            }

            if (!deleted) {
                queryString.append(" AND a.deleted = false");
            }

            if (!dateFrom.equals("")) {
                queryString.append(" AND a." + columnDate + " BETWEEN ?2 AND ?3");
                query = entityManager.createQuery(queryString.toString())
                        .setParameter(1, myValue)
                        .setParameter(2, LocalDate.parse(dateFrom))
                        .setParameter(3, LocalDate.parse(dateBy));
            } else {
                query = entityManager.createQuery(queryString.toString())
                        .setParameter(1, myValue);
            }
        } else {
            if (!dateFrom.equals("")) {
                queryString.append(columnDate + " BETWEEN ?1 AND ?2");

                if (!deleted) {
                    queryString.append(" AND a.deleted = false");
                }
                query = entityManager.createQuery(queryString.toString())
                        .setParameter(1, LocalDate.parse(dateFrom))
                        .setParameter(2, LocalDate.parse(dateBy));
            } else {
                if (!deleted) {
                    query = entityManager.createQuery("SELECT a FROM Message a WHERE a.deleted = false");
                } else {
                    query = entityManager.createQuery("SELECT a FROM Message a");
                }
            }
        }

        List entries = query.getResultList();
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), entries.size());
        List<Message> pageContent = entries.subList(start, end);

        return new PageImpl<>(pageContent, pageable, entries.size());
    }

    private Message setMessageInfo(File fileXml) {
        Message newMessage = fileService.unmarshall(fileXml);

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(username).get();

        FileInfo newFileInfo = fileService.addFileInfo(fileXml);
        newFileInfo.setMessage(newMessage);

        newMessage.setUser(user);
        newMessage.setFileInfo(newFileInfo);
        newMessage.setDeleted(false);

        return newMessage;
    }

    private void checkFile(String name) {
        if (fileService.checkFileExist(name)) {
            log.warn("Ошибка при добавлении файла: файл уже существует");
            throw new DuplicateFileException("Ошибка: файл уже существует");
        }
    }
}
