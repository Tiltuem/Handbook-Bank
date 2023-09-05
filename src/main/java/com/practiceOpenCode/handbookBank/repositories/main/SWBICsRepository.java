package com.practiceOpenCode.handbookBank.repositories.main;

import com.practiceOpenCode.handbookBank.models.main.SWBICs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SWBICsRepository extends JpaRepository<SWBICs, Long> {
    SWBICs findById(long id);
}
