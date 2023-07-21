package com.practiceOpenCode.handbookBank.services.impl;

import com.practiceOpenCode.handbookBank.models.BICDirectoryEntry;
import com.practiceOpenCode.handbookBank.repositories.BICDirectoryEntryRepository;
import com.practiceOpenCode.handbookBank.services.BICDirectoryEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BICDirectoryEntryServiceImpl implements BICDirectoryEntryService {
    @Autowired
    private BICDirectoryEntryRepository repository;

    @Override
    public List<BICDirectoryEntry> getAllDirectory() {
        return repository.findAll();
    }

    @Override
    public void save(BICDirectoryEntry bicDirectoryEntry) {
        repository.save(bicDirectoryEntry);
    }

    @Override
    public void deleteViaId(long id) {
        repository.deleteById(id);
    }
}
