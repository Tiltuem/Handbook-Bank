package com.practiceOpenCode.handbookBank.services.impl;

import com.practiceOpenCode.handbookBank.models.Swbics;
import com.practiceOpenCode.handbookBank.repositories.SwbicsRepository;
import com.practiceOpenCode.handbookBank.services.SwbicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SwbicsServiceImpl implements SwbicsService {
    @Autowired
    private SwbicsRepository repository;

    @Override
    public List<Swbics> getAllSwbics() {
        return repository.findAll();
    }

    @Override
    public void save(Swbics swbics) {
        repository.save(swbics);
    }

    @Override
    public void deleteViaId(long id) {
        repository.deleteById(id);
    }
}
