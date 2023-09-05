package com.practiceOpenCode.handbookBank.repositories.main;

import com.practiceOpenCode.handbookBank.models.main.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Long> {
    Accounts findById(long id);
}
