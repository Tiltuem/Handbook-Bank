package com.practiceOpenCode.handbookBank.services;

import com.practiceOpenCode.handbookBank.models.BICDirectoryEntry;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BICDirectoryEntryService {
    List<BICDirectoryEntry> getAllDirectory();
    void save (BICDirectoryEntry bicDirectoryEntry);
    void deleteViaId(long id);
}
