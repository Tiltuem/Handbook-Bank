package com.practiceOpenCode.handbookBank.adapters;

import com.practiceOpenCode.handbookBank.exceptions.NoSuchCodeException;
import com.practiceOpenCode.handbookBank.models.codes.ParticipantTypeCode;
import com.practiceOpenCode.handbookBank.repositories.codes.AbstractCodeRepository;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.List;

public class ParticipantTypeCodeAdapter extends XmlAdapter<String, ParticipantTypeCode> {
    private final List<ParticipantTypeCode> participantTypeCodeList;

    public ParticipantTypeCodeAdapter(AbstractCodeRepository<ParticipantTypeCode> repository) {
        participantTypeCodeList = repository.findAll();
    }

    @Override
    public ParticipantTypeCode unmarshal(String code) throws Exception {
        for (ParticipantTypeCode participantTypeCode : participantTypeCodeList) {
            if (participantTypeCode.getCode().equals(code)) {
                if (!participantTypeCode.getDeleted()) {
                    return participantTypeCode;
                } else {
                    throw new NoSuchCodeException("Ошибка: код '" + code
                            + "' удалён.\nДля получения файла восстановите этот код в ограничения операций по счету");
                }
            }
        }

        throw new NoSuchCodeException("Ошибка: код '" + code
                            + "' отсутствует.\nВосстановите код в ограничения операций по счету");
    }

    @Override
    public String marshal(ParticipantTypeCode participantTypeCode) throws Exception {
        return participantTypeCode.getCode();
    }
}