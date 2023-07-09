package com.practiceOpenCode.handbookBank.services.impl;

import com.practiceOpenCode.handbookBank.models.Message;
import com.practiceOpenCode.handbookBank.repositories.MessageRepository;
import com.practiceOpenCode.handbookBank.services.FileService;
import com.practiceOpenCode.handbookBank.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageRepository repository;
    @Autowired
    private FileService fileService;

    @Override
    public List<Message> getAllMessage() {
        return repository.findAll();
    }

    @Override
    public void save(String date) {
        try {
            String nameFileZip = fileService.download(date);
            File fileXml = fileService.unpack(nameFileZip);
            Message newMessage = fileService.unmarshall(fileXml);
            newMessage.setFileInfo(fileService.addFileInfo(fileXml));
            repository.save(newMessage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(MultipartFile file) {
        File fileXml = new File("src/main/resources/storage/" + file.getOriginalFilename());
        try (OutputStream os = Files.newOutputStream(fileXml.toPath())) {
            os.write(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Message newMessage = fileService.unmarshall(fileXml);
        newMessage.setFileInfo(fileService.addFileInfo(fileXml));
        repository.save(newMessage);
    }

    @Override
    public void deleteViaId(long id) {
        repository.deleteById(id);
    }
}
