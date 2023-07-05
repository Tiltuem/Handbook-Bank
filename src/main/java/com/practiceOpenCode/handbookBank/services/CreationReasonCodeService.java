package com.practiceOpenCode.handbookBank.services;

import com.practiceOpenCode.handbookBank.models.Message;
import com.practiceOpenCode.handbookBank.models.directories.CreationReasonCode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CreationReasonCodeService {
    List<CreationReasonCode> getAllCodes();
    void save (CreationReasonCode creationReasonCode);
    void deleteViaId(long id);
}
