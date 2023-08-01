package com.practiceOpenCode.handbookBank.repositories;

import com.practiceOpenCode.handbookBank.models.FileInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<FileInfo, Long> {
    Page<FileInfo> findAll(Pageable pageable);
    FileInfo findById(long id);
    FileInfo findByName(String name);
}
