package com.practiceOpenCode.handbookBank.models.adapters;

import com.practiceOpenCode.handbookBank.exception.NoSuchCodeException;
import com.practiceOpenCode.handbookBank.models.codes.AccountRestrictionCode;
import com.practiceOpenCode.handbookBank.models.context.ApplicationContextHolder;
import com.practiceOpenCode.handbookBank.models.codes.ExchangeParticipantCode;
import com.practiceOpenCode.handbookBank.repositories.codes.ExchangeParticipantCodeRepository;
import javax.xml.bind.annotation.adapters.XmlAdapter;
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
            if (exchangeParticipantCode.getCode().equals(code)) {
                if(!exchangeParticipantCode.getDeleted())  return exchangeParticipantCode;
                else
                    throw new NoSuchCodeException("Ошибка: код '" + code + "' удалён.\nДля получения файла добавьте этот код в ограничения операций по счету");
            }
        }
        return new ExchangeParticipantCode(code);
    }

    @Override
    public String marshal(ExchangeParticipantCode exchangeParticipantCode) throws Exception {
        return exchangeParticipantCode.getCode();
    }
}