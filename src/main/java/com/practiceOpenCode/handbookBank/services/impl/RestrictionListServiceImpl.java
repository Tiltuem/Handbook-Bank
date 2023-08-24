package com.practiceOpenCode.handbookBank.services.impl;

import com.practiceOpenCode.handbookBank.models.RestrictionList;
import com.practiceOpenCode.handbookBank.repositories.RestrictionListRepository;
import com.practiceOpenCode.handbookBank.services.RestrictionListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestrictionListServiceImpl implements RestrictionListService {
    @Autowired
    private RestrictionListRepository repository;

    @Override
    public List<RestrictionList> getAllRestrictionList() {
        return repository.findAll();
    }

    @Override
    public void save(RestrictionList restrictionList) {
        repository.save(restrictionList);

    }
    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }

    @Override
    public RestrictionList getById(long id) {
        return repository.findById(id);
    }

    @Override
    public void recoveryById(long id) {
        RestrictionList restrictionList = repository.findById(id);
        restrictionList.setDeleted(false);
        repository.save(restrictionList);
    }

}
