package com.practiceOpenCode.handbookBank.repositories;

import com.practiceOpenCode.handbookBank.models.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    Page<Message> findByDeleted(Pageable pageable, Boolean deleted);
    Message findById(long id);
    Page<Message> findAll(Pageable pageable);
}