package com.practiceOpenCode.handbookBank.models.adapters;

import com.practiceOpenCode.handbookBank.exception.NoSuchCodeException;
import com.practiceOpenCode.handbookBank.models.codes.AccountRestrictionCode;
import com.practiceOpenCode.handbookBank.models.context.ApplicationContextHolder;
import com.practiceOpenCode.handbookBank.models.codes.ParticipantStatusCode;
import com.practiceOpenCode.handbookBank.repositories.codes.ParticipantStatusCodeRepository;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class ParticipantStatusCodeAdapter extends XmlAdapter<String, ParticipantStatusCode> {
    private final List<ParticipantStatusCode> participantStatusCodeList;
    private final ApplicationContext ctx;

    public ParticipantStatusCodeAdapter() {
        ctx = ApplicationContextHolder.getApplicationContext();
        participantStatusCodeList = ctx.getBean(ParticipantStatusCodeRepository.class).findAll();
    }

    @Override
    public ParticipantStatusCode unmarshal(String code) throws Exception {
        for (ParticipantStatusCode participantStatusCode : participantStatusCodeList) {
            if (participantStatusCode.getCode().equals(code)) {
                if(!participantStatusCode.getDeleted())  return participantStatusCode;
                else
                    throw new NoSuchCodeException("Ошибка: код '" + code + "' удалён.\nДля получения файла добавьте этот код в ограничения операций по счету");
            }
        }
        return new ParticipantStatusCode(code);
    }
    @Override
    public String marshal(ParticipantStatusCode participantStatusCode) throws Exception {
        return participantStatusCode.getCode();
    }
}