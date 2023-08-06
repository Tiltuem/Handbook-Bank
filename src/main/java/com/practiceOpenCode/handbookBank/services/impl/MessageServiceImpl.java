package com.practiceOpenCode.handbookBank.services.impl;

import com.practiceOpenCode.handbookBank.exception.DuplicateFileException;
import com.practiceOpenCode.handbookBank.exception.NotFoundFileXmlException;
import com.practiceOpenCode.handbookBank.models.Message;
import com.practiceOpenCode.handbookBank.models.codes.AccountRestrictionCode;
import com.practiceOpenCode.handbookBank.repositories.MessageRepository;
import com.practiceOpenCode.handbookBank.services.FileService;
import com.practiceOpenCode.handbookBank.services.MessageService;
import freemarker.core.Environment;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageRepository repository;
    @Autowired
    private FileService fileService;

    @Override
    public Page<Message> getAllMessages(Pageable pageable) {
        return repository.findAll(pageable);
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
            repository.save(newMessage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(MultipartFile file) {
        String path = "src/main/resources/storage/" + file.getOriginalFilename();
        File fileXml = new File(path);

        if(path.endsWith(".xml")) {
            checkFile(file.getOriginalFilename());
            try (OutputStream os = Files.newOutputStream(fileXml.toPath())) {
                os.write(file.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if(path.endsWith(".zip")) {
            try {
                file.transferTo(fileXml.getAbsoluteFile());
                fileXml = fileService.unpack(path);
                Files.deleteIfExists(Paths.get(path));
                checkFile(fileXml.getName());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new NotFoundFileXmlException("Ошибка: неверный формат файла.");
        }

        Message newMessage = fileService.unmarshall(fileXml);
        newMessage.setFileInfo(fileService.addFileInfo(fileXml));
        newMessage.getFileInfo().setMessage(newMessage);
        repository.save(newMessage);
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }

    @Override
    public Message getMessageById(long id) {
        return repository.getReferenceById(id);
    }

    private void checkFile(String name) {
        if (fileService.checkFileExist(name)) {
            throw new DuplicateFileException("Ошибка: файл уже существует");
        }
    }
}
