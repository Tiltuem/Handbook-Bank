package com.practiceOpenCode.handbookBank.services;

import com.practiceOpenCode.handbookBank.models.Message;
import com.practiceOpenCode.handbookBank.models.directories.ServiceCsCode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ServiceCsCodeService {
    List<ServiceCsCode> getAllCodes();
    void save (ServiceCsCode serviceCsCode);
    void deleteViaId(long id);
}
