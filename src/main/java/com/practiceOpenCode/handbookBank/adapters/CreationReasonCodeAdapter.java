package com.practiceOpenCode.handbookBank.adapters;

import com.practiceOpenCode.handbookBank.exceptions.NoSuchCodeException;
import com.practiceOpenCode.handbookBank.models.codes.CreationReasonCode;
import com.practiceOpenCode.handbookBank.repositories.codes.AbstractCodeRepository;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.List;

public class CreationReasonCodeAdapter extends XmlAdapter<String, CreationReasonCode> {
    private final List<CreationReasonCode> creationReasonCodeList;

    public CreationReasonCodeAdapter(AbstractCodeRepository<CreationReasonCode> repository) {
        creationReasonCodeList = repository.findAll();
    }

    @Override
    public CreationReasonCode unmarshal(String code) throws Exception {
        for (CreationReasonCode creationReasonCode : creationReasonCodeList) {
            if (creationReasonCode.getCode().equals(code)) {
                if (!creationReasonCode.getDeleted()) {
                    return creationReasonCode;
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
    public String marshal(CreationReasonCode creationReasonCode) throws Exception {
        return creationReasonCode.getCode();
    }
}
