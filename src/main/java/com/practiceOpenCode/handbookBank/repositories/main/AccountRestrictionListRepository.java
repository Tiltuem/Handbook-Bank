package com.practiceOpenCode.handbookBank.repositories.main;

import com.practiceOpenCode.handbookBank.models.main.AccountRestrictionList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRestrictionListRepository extends JpaRepository<AccountRestrictionList, Long> {
}
