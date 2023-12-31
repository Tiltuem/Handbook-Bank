package com.practiceOpenCode.handbookBank.services.main.impl;

import com.practiceOpenCode.handbookBank.models.main.RestrictionList;
import com.practiceOpenCode.handbookBank.repositories.main.RestrictionListRepository;
import com.practiceOpenCode.handbookBank.services.main.RestrictionListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestrictionListServiceImpl implements RestrictionListService {
    private final RestrictionListRepository repository;

    @Override
    public List<RestrictionList> getAllRestrictionList() {
        return repository.findAll();
    }

    @Override
    public void save(RestrictionList restrictionList) {
        repository.save(restrictionList);
    }

    @Override
    public void update(RestrictionList newRestrictionList) {
        RestrictionList restrictionList = repository.findById(newRestrictionList.getId());
        restrictionList.setRestrictionCode(newRestrictionList.getRestrictionCode());
        restrictionList.setRestrictionDate(newRestrictionList.getRestrictionDate());

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
