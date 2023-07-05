package com.practiceOpenCode.handbookBank.services.impl;

import com.practiceOpenCode.handbookBank.models.Message;
import com.practiceOpenCode.handbookBank.repositories.MessageRepository;
import com.practiceOpenCode.handbookBank.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private  MessageRepository repository;

    @Override
    public List<Message> getAllMessage() {
        return repository.findAll();
    }

    @Override
    public void save(Message message) {
        repository.save(message);
    }

    @Override
    public void deleteViaId(long id) {
        repository.deleteById(id);
    }
}
