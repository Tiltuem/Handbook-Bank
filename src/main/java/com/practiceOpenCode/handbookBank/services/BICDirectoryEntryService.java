package com.practiceOpenCode.handbookBank.services;

import com.practiceOpenCode.handbookBank.models.BICDirectoryEntry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BICDirectoryEntryService {
    Page<BICDirectoryEntry> getAllDirectory(Pageable pageable);
    void save (BICDirectoryEntry bicDirectoryEntry);
    void deleteViaId(long id);
}
