package com.practiceOpenCode.handbookBank.services;

import com.practiceOpenCode.handbookBank.models.BICDirectoryEntry;
import com.practiceOpenCode.handbookBank.models.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public interface MessageService {
    Page<Message> getAllMessages(Pageable pageable);
    Page<Message> searchMessages(Pageable pageable, String value, Boolean showDeleted, String column, String columnDate, String dateFrom, String dateBy);
    void save (String date);
    void save (MultipartFile file);
    void deleteById(long id);
    Message getMessageById(long id);
    void recoveryById(long id);
}
