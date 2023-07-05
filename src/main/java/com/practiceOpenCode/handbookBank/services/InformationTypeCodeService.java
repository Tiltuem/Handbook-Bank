package com.practiceOpenCode.handbookBank.services;

import com.practiceOpenCode.handbookBank.models.Message;
import com.practiceOpenCode.handbookBank.models.directories.InformationTypeCode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InformationTypeCodeService {
    List<InformationTypeCode> getAllCodes();
    void save (InformationTypeCode informationTypeCode);
    void deleteViaId(long id);
}
