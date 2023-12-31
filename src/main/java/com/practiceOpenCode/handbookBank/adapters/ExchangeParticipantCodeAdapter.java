package com.practiceOpenCode.handbookBank.adapters;

import com.practiceOpenCode.handbookBank.exceptions.NoSuchCodeException;
import com.practiceOpenCode.handbookBank.models.codes.ExchangeParticipantCode;
import com.practiceOpenCode.handbookBank.repositories.codes.AbstractCodeRepository;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.List;

public class ExchangeParticipantCodeAdapter extends XmlAdapter<String, ExchangeParticipantCode> {
    private final List<ExchangeParticipantCode> exchangeParticipantCodeList;

    public ExchangeParticipantCodeAdapter(AbstractCodeRepository<ExchangeParticipantCode> repository) {
        exchangeParticipantCodeList = repository.findAll();
    }

    @Override
    public ExchangeParticipantCode unmarshal(String code) throws Exception {
        for (ExchangeParticipantCode exchangeParticipantCode : exchangeParticipantCodeList) {
            if (exchangeParticipantCode.getCode().equals(code)) {
                if (!exchangeParticipantCode.getDeleted()) {
                    return exchangeParticipantCode;
                } else {
                    throw new NoSuchCodeException("Ошибка: код '" + code
                            + "' удалён.\nДля получения файла восстановите этот код в ограничения операций по счету");
                }
            }
        }

        throw new NoSuchCodeException("Ошибка: код '" + code
                            + "' отсутствует.\nДля получения файла добавьте этот код в ограничения операций по счету");
    }

    @Override
    public String marshal(ExchangeParticipantCode exchangeParticipantCode) throws Exception {
        return exchangeParticipantCode.getCode();
    }
}