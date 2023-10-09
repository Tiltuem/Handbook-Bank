package com.practiceOpenCode.handbookBank.repositories.main;


import com.practiceOpenCode.handbookBank.models.main.BICDirectoryEntry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BICDirectoryEntryRepository extends JpaRepository<BICDirectoryEntry, Long> {
    Page<BICDirectoryEntry> findAll(Pageable pageable);

    BICDirectoryEntry findById(long id);

    Page<BICDirectoryEntry> findByDeleted(Pageable pageable, Boolean deleted);
}
