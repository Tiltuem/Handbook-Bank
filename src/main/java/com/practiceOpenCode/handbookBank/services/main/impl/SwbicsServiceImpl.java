package com.practiceOpenCode.handbookBank.services.main.impl;

import com.practiceOpenCode.handbookBank.models.main.Swbics;
import com.practiceOpenCode.handbookBank.repositories.main.SwbicsRepository;
import com.practiceOpenCode.handbookBank.services.main.SwbicsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SwbicsServiceImpl implements SwbicsService {
    private final SwbicsRepository repository;

    @Override
    public List<Swbics> getAllSwbics() {
        return repository.findAll();
    }

    @Override
    public void save(Swbics swbics) {
        repository.save(swbics);
    }

    @Override
    public void update(Swbics newSwbics) {
        Swbics swbics = repository.findById(newSwbics.getId());
        swbics.setSwbic(newSwbics.getSwbic());
        swbics.setDefaultSWBIC(newSwbics.isDefaultSWBIC());

        repository.save(swbics);
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }

    @Override
    public Swbics getById(long id) {
        return repository.findById(id);
    }

    @Override
    public void recoveryById(long id) {
        Swbics swbics = repository.findById(id);
        swbics.setDeleted(false);

        repository.save(swbics);
    }
}
