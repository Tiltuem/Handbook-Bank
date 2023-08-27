package com.practiceOpenCode.handbookBank.repositories;

import com.practiceOpenCode.handbookBank.models.SWBICs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SWBICsRepository extends JpaRepository<SWBICs, Long> {
    SWBICs findById(long id);
}
