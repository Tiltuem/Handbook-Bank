package com.practiceOpenCode.handbookBank.repositories;

import com.practiceOpenCode.handbookBank.models.AccountRestrictionList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRestrictionListRepository extends JpaRepository<AccountRestrictionList, Long> {
}
