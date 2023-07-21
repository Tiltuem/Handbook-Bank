package com.practiceOpenCode.handbookBank.models.adapters;

import com.practiceOpenCode.handbookBank.exception.NoSuchCodeException;
import com.practiceOpenCode.handbookBank.models.context.ApplicationContextHolder;
import com.practiceOpenCode.handbookBank.models.codes.ParticipantTypeCode;
import com.practiceOpenCode.handbookBank.repositories.codes.ParticipantTypeCodeRepository;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class ParticipantTypeCodeAdapter extends XmlAdapter<String, ParticipantTypeCode> {
    private final List<ParticipantTypeCode> participantTypeCodeList;
    private final ApplicationContext ctx;

    public ParticipantTypeCodeAdapter() {
        ctx = ApplicationContextHolder.getApplicationContext();
        participantTypeCodeList = ctx.getBean(ParticipantTypeCodeRepository.class).findAll();
    }

    @Override
    public ParticipantTypeCode unmarshal(String code) throws Exception {
        for (ParticipantTypeCode participantTypeCode : participantTypeCodeList) {
            if (participantTypeCode.getCode().equals(code)) return participantTypeCode;
        }
        throw new NoSuchCodeException(code + "code not found in directory.");
    }

    @Override
    public String marshal(ParticipantTypeCode participantTypeCode) throws Exception {
        return participantTypeCode.getCode();
    }
}