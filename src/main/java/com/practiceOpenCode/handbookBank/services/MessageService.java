package com.practiceOpenCode.handbookBank.services;

import com.practiceOpenCode.handbookBank.models.Message;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Service
public interface MessageService {
    List<Message> getAllMessage();
    void save (String date);
    void save (MultipartFile file);
    void deleteViaId(long id);
}
