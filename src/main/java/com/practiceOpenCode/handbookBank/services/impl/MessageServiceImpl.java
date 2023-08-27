package com.practiceOpenCode.handbookBank.services.impl;

import com.practiceOpenCode.handbookBank.exception.DuplicateFileException;
import com.practiceOpenCode.handbookBank.exception.NotFoundFileXmlException;

import com.practiceOpenCode.handbookBank.models.Message;
import com.practiceOpenCode.handbookBank.repositories.MessageRepository;
import com.practiceOpenCode.handbookBank.services.FileService;
import com.practiceOpenCode.handbookBank.services.MessageService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageRepository repository;
    @Autowired
    private FileService fileService;

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Page<Message> getAllMessages(Pageable pageable) {
        return repository.findByDeleted(pageable, false);
    }

    @Override
    public Page<Message> searchMessages(Pageable pageable, String value, Boolean showDeleted, String column, String columnDate, String dateFrom, String dateBy) {
        if (!value.equals("") || !dateFrom.equals("")) return search(pageable, value, column, columnDate,showDeleted, dateFrom, dateBy);
        if (showDeleted) return repository.findAll(pageable);
        return repository.findByDeleted(pageable, false);
    }

    @Override
    public void save(String date) {
        try {
            String nameFileZip = fileService.download(date);
            File fileXml = fileService.unpack(nameFileZip);
            checkFile(fileXml.getName());
            Files.deleteIfExists(Paths.get(nameFileZip));
            Message newMessage = fileService.unmarshall(fileXml);
            newMessage.setFileInfo(fileService.addFileInfo(fileXml));
            newMessage.setDeleted(false);
            repository.save(newMessage);
        } catch (IOException e) {
            log.error("Ошибка при добавлении файла на стороне сервера");
            throw new RuntimeException(e);
        }
    }

    @Override
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

        Message newMessage = fileService.unmarshall(fileXml);
        newMessage.setFileInfo(fileService.addFileInfo(fileXml));
        newMessage.getFileInfo().setMessage(newMessage);
        newMessage.setDeleted(false);
        repository.save(newMessage);
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }

    @Override
    public Message getMessageById(long id) {
        return repository.findById(id);
    }

    private void checkFile(String name) {
        if (fileService.checkFileExist(name)) {
            log.warn("Ошибка при добавлении файла: файл уже существует");
            throw new DuplicateFileException("Ошибка: файл уже существует");
        }
    }

    @Override
    public void recoveryById(long id) {
        Message message = repository.findById(id);
        message.setDeleted(false);
        repository.save(message);
    }

    private Page<Message> search(Pageable pageable, String value, String column, String columnDate, Boolean deleted, String dateFrom, String dateBy) {
        Query query;
        StringBuilder queryString = new StringBuilder("SELECT a FROM Message a WHERE a.");
        if (!value.equals("")) {

            switch (column) {
                case "edNumber", "edAuthor", "edReceiver", "directoryVersion" -> queryString.append(column + " = ?1");
                default -> {
                    value = "%" + value + "%";
                    queryString.append(column + " LIKE ?1");
                }
            }

            if (!deleted)
                queryString.append(" AND a.deleted = false");

            if (!dateFrom.equals("")) {
                queryString.append(" AND a." + columnDate +" BETWEEN ?2 AND ?3");
                query = entityManager.createQuery(queryString.toString())
                        .setParameter(1, value)
                        .setParameter(2, LocalDate.parse(dateFrom))
                        .setParameter(3, LocalDate.parse(dateBy));
            } else
                query = entityManager.createQuery(queryString.toString())
                        .setParameter(1, value);
        } else {
            if (!dateFrom.equals("")) {
                queryString.append(columnDate +" BETWEEN ?1 AND ?2");
                if (!deleted)
                    queryString.append(" AND a.deleted = false");

                query = entityManager.createQuery(queryString.toString())
                        .setParameter(1, LocalDate.parse(dateFrom))
                        .setParameter(2, LocalDate.parse(dateBy));
            } else {
                if (!deleted)
                    query = entityManager.createQuery("SELECT a FROM Message a WHERE a.deleted = false");
                else
                    query = entityManager.createQuery("SELECT a FROM Message a");
            }
        }

        List entries = query.getResultList();
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), entries.size());
        List<Message> pageContent = entries.subList(start, end);
        return new PageImpl<>(pageContent, pageable, entries.size());
    }
}
