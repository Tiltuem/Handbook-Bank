package com.practiceOpenCode.handbookBank.services;

import com.practiceOpenCode.handbookBank.models.Accounts;
import com.practiceOpenCode.handbookBank.models.Message;
import com.practiceOpenCode.handbookBank.models.RestrictionList;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RestrictionListService {
    List<RestrictionList> getAllRestrictionList();
    void save (RestrictionList restrictionList);
    void deleteById(long id);
    RestrictionList getById(long id);
    void recoveryById(long id);
}
