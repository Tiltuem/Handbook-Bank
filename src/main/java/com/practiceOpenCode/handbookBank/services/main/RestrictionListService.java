package com.practiceOpenCode.handbookBank.services.main;

import com.practiceOpenCode.handbookBank.models.main.RestrictionList;
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
