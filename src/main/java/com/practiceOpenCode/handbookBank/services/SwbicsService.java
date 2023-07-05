package com.practiceOpenCode.handbookBank.services;

import com.practiceOpenCode.handbookBank.models.Message;
import com.practiceOpenCode.handbookBank.models.Swbics;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SwbicsService {
    List<Swbics> getAllSwbics();
    void save (Swbics swbics);
    void deleteViaId(long id);
}
