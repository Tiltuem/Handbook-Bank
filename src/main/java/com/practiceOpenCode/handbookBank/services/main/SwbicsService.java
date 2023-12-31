package com.practiceOpenCode.handbookBank.services.main;

import com.practiceOpenCode.handbookBank.models.main.Swbics;

import java.util.List;

public interface SwbicsService {
    List<Swbics> getAllSwbics();

    void save(Swbics swbics);

    void update(Swbics swbics);

    void deleteById(long id);

    Swbics getById(long id);

    void recoveryById(long id);
}
