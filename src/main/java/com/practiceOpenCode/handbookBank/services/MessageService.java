package com.practiceOpenCode.handbookBank.services;

import com.practiceOpenCode.handbookBank.models.Message;
import com.practiceOpenCode.handbookBank.models.codes.AccountRestrictionCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public interface MessageService {
    Page<Message> getAllMessages(Pageable pageable);
    void save (String date);
    void save (MultipartFile file);
    void deleteViaId(long id);
    Message getMessageViaId(long id);
}
