package com.practiceOpenCode.handbookBank.services.impl;

import com.practiceOpenCode.handbookBank.models.BICDirectoryEntry;
import com.practiceOpenCode.handbookBank.repositories.BICDirectoryEntryRepository;
import com.practiceOpenCode.handbookBank.services.BICDirectoryEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BICDirectoryEntryServiceImpl implements BICDirectoryEntryService {
    @Autowired
    private BICDirectoryEntryRepository repository;


    @Override
    public Page<BICDirectoryEntry> getAllDirectory(Pageable pageable) {
        return repository.findAll(pageable);
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
