package com.practiceOpenCode.handbookBank.services;

import com.practiceOpenCode.handbookBank.models.Message;
import com.practiceOpenCode.handbookBank.models.RestrictionList;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RestrictionListService {
    List<RestrictionList> getAllRestrictionList();
    void save (RestrictionList restrictionList);
    void deleteViaId(long id);
}
