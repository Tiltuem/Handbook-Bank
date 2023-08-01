package com.practiceOpenCode.handbookBank.services;

import com.practiceOpenCode.handbookBank.models.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;


@Service
public interface MessageService {
    Page<Message> getAllMessages(Pageable pageable);
    void save (String date);
    void save (MultipartFile file);
    void deleteById(long id);
    Message getMessageById(long id);
}
