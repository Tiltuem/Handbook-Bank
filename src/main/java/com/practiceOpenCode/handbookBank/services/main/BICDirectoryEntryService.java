package com.practiceOpenCode.handbookBank.services.main;

import com.practiceOpenCode.handbookBank.models.main.Accounts;
import com.practiceOpenCode.handbookBank.models.main.BICDirectoryEntry;
import com.practiceOpenCode.handbookBank.models.main.ParticipantInfo;
import com.practiceOpenCode.handbookBank.models.main.Swbics;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BICDirectoryEntryService {
    void updateById(long id, Swbics swbics);

    void updateById(long id, Accounts account);

    Page<BICDirectoryEntry> getAllEntries(Pageable pageable);

    Page<BICDirectoryEntry> searchEntries(Pageable pageable, String value, Boolean deleted,
                                          String column, String dateFrom, String dateBy);

    void save(BICDirectoryEntry bicDirectoryEntry);

    void update(BICDirectoryEntry entry, ParticipantInfo info, String participantTypeCode, String serviceCsCode,
                String exchangeParticipantCode, String participantStatusCode, String changeTypeCode);

    void add(BICDirectoryEntry entry, ParticipantInfo info, String participantTypeCode, String serviceCsCode,
             String exchangeParticipantCode, String participantStatusCode, String changeTypeCode);

    void deleteById(long id);

    BICDirectoryEntry getById(long id);

    void recoveryById(long id);
}

