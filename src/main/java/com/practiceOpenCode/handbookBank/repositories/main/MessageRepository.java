package com.practiceOpenCode.handbookBank.repositories.main;

import com.practiceOpenCode.handbookBank.models.main.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    Page<Message> findByDeleted(Pageable pageable, Boolean deleted);
    Message findById(long id);
    Page<Message> findAll(Pageable pageable);
}