package com.practiceOpenCode.handbookBank.repositories.main;

import com.practiceOpenCode.handbookBank.models.main.RestrictionList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestrictionListRepository extends JpaRepository<RestrictionList, Long> {
    RestrictionList findById(long id);
}
