package com.practiceOpenCode.handbookBank.services;

import com.practiceOpenCode.handbookBank.models.Message;
import com.practiceOpenCode.handbookBank.models.directories.RestrictionCode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RestrictionCodeService {
    List<RestrictionCode> getAllCodes();
    void save (RestrictionCode restrictionCode);
    void deleteViaId(long id);
}
