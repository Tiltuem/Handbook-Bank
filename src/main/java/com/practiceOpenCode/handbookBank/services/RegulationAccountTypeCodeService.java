package com.practiceOpenCode.handbookBank.services;

import com.practiceOpenCode.handbookBank.models.Message;
import com.practiceOpenCode.handbookBank.models.directories.RegulationAccountTypeCode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RegulationAccountTypeCodeService {
    List<RegulationAccountTypeCode> getAllCodes();
    void save (RegulationAccountTypeCode regulationAccountTypeCode);
    void deleteViaId(long id);
}
