package com.practiceOpenCode.handbookBank.services;

import com.practiceOpenCode.handbookBank.models.Message;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MessageService {
    List<Message> getAllMessage();
    void save (Message message);
    void deleteViaId(long id);
}
