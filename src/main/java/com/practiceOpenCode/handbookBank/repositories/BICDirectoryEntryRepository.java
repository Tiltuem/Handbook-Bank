package com.practiceOpenCode.handbookBank.repositories;

import com.practiceOpenCode.handbookBank.models.Accounts;
import com.practiceOpenCode.handbookBank.models.BICDirectoryEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BICDirectoryEntryRepository extends JpaRepository<BICDirectoryEntry, Long> {
}
