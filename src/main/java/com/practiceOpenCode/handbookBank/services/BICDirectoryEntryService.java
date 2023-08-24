package com.practiceOpenCode.handbookBank.services;

import com.practiceOpenCode.handbookBank.models.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface BICDirectoryEntryService {
    void updateById(long id, SWBICs swbiCs);
    void updateById(long id, Accounts account);
    Page<BICDirectoryEntry> getAllEntries(Pageable pageable);
    Page<BICDirectoryEntry> searchEntries(Pageable pageable, String value, Boolean showDeleted, String column, String dateFrom, String dateBy);
    void save (BICDirectoryEntry bicDirectoryEntry);
    void update(BICDirectoryEntry entry, String participantTypeCode, String serviceCsCode, String exchangeParticipantCode, String participantStatusCode, String changeTypeCode);
    void add(String bic, ParticipantInfo info, String participantTypeCode, String serviceCsCode, String exchangeParticipantCode, String participantStatusCode, String changeTypeCode);
    void deleteById(long id);
    BICDirectoryEntry getById(long id);
    void recoveryById(long id);
}

