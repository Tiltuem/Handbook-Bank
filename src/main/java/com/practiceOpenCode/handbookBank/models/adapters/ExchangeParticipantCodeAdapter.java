package com.practiceOpenCode.handbookBank.models.adapters;

import com.practiceOpenCode.handbookBank.exception.NoSuchCodeException;
import com.practiceOpenCode.handbookBank.models.context.ApplicationContextHolder;
import com.practiceOpenCode.handbookBank.models.directories.ExchangeParticipantCode;
import com.practiceOpenCode.handbookBank.repositories.ExchangeParticipantCodeRepository;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class ExchangeParticipantCodeAdapter extends XmlAdapter<String, ExchangeParticipantCode> {
    private final List<ExchangeParticipantCode> exchangeParticipantCodeList;
    private final ApplicationContext ctx;

    public ExchangeParticipantCodeAdapter() {
        ctx = ApplicationContextHolder.getApplicationContext();
        exchangeParticipantCodeList = ctx.getBean(ExchangeParticipantCodeRepository.class).findAll();
    }

    @Override
    public ExchangeParticipantCode unmarshal(String code) throws Exception {
        for (ExchangeParticipantCode exchangeParticipantCode : exchangeParticipantCodeList) {
            if (exchangeParticipantCode.getCode().equals(code)) return exchangeParticipantCode;
        }
        throw new NoSuchCodeException(code + "code not found in directory.");
    }

    @Override
    public String marshal(ExchangeParticipantCode exchangeParticipantCode) throws Exception {
        return exchangeParticipantCode.getCode();
    }
}