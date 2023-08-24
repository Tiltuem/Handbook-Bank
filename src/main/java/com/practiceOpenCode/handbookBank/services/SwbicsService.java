package com.practiceOpenCode.handbookBank.services;

import com.practiceOpenCode.handbookBank.models.Accounts;
import com.practiceOpenCode.handbookBank.models.SWBICs;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SWBICsService {

    List<SWBICs> getAllSWBICs();
    void save (SWBICs SWBICs);
    void deleteById(long id);
    SWBICs getById(long id);
    void recoveryById(long id);
}
