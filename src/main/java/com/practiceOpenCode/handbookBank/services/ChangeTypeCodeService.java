package com.practiceOpenCode.handbookBank.services;

import com.practiceOpenCode.handbookBank.models.Message;
import com.practiceOpenCode.handbookBank.models.directories.ChangeTypeCode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ChangeTypeCodeService {
    List<ChangeTypeCode> getAllCodes();
    void save (ChangeTypeCode changeTypeCode);
    void deleteViaId(long id);
}
