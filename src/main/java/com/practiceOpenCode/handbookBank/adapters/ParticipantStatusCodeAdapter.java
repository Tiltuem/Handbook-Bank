package com.practiceOpenCode.handbookBank.adapters;

import com.practiceOpenCode.handbookBank.exceptions.NoSuchCodeException;
import com.practiceOpenCode.handbookBank.models.codes.ParticipantStatusCode;
import com.practiceOpenCode.handbookBank.repositories.codes.AbstractCodeRepository;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.List;

public class ParticipantStatusCodeAdapter extends XmlAdapter<String, ParticipantStatusCode> {
    private final List<ParticipantStatusCode> participantStatusCodeList;

    public ParticipantStatusCodeAdapter(AbstractCodeRepository<ParticipantStatusCode> repository) {
        participantStatusCodeList = repository.findAll();
    }

    @Override
    public ParticipantStatusCode unmarshal(String code) throws Exception {
        for (ParticipantStatusCode participantStatusCode : participantStatusCodeList) {
            if (participantStatusCode.getCode().equals(code)) {
                if (!participantStatusCode.getDeleted()) {
                    return participantStatusCode;
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
    public String marshal(ParticipantStatusCode participantStatusCode) throws Exception {
        return participantStatusCode.getCode();
    }
}