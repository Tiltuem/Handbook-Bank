package com.practiceOpenCode.handbookBank.repositories;

import com.practiceOpenCode.handbookBank.models.ParticipantInfo;
import com.practiceOpenCode.handbookBank.models.RestrictionList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestrictionListRepository extends JpaRepository<RestrictionList, Long> {
}
