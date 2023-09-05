package com.practiceOpenCode.handbookBank.repositories.main;

import com.practiceOpenCode.handbookBank.models.main.ParticipantInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantInfoRepository extends JpaRepository<ParticipantInfo, Long> {
    Page<ParticipantInfo> findAll(Pageable pageable);
    ParticipantInfo findById(long id);
}
