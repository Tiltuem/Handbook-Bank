package com.practiceOpenCode.handbookBank.repositories;

import com.practiceOpenCode.handbookBank.models.ParticipantInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantInfoRepository extends JpaRepository<ParticipantInfo, Long> {
}
