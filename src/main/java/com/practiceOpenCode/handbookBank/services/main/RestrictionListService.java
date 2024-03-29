package com.practiceOpenCode.handbookBank.services.main;

import com.practiceOpenCode.handbookBank.models.main.RestrictionList;

import java.util.List;

public interface RestrictionListService {
    List<RestrictionList> getAllRestrictionList();

    void save(RestrictionList restrictionList);

    void update(RestrictionList restrictionList);

    void deleteById(long id);

    RestrictionList getById(long id);

    void recoveryById(long id);
}
