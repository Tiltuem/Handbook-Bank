package com.practiceOpenCode.handbookBank.adapters;

import com.practiceOpenCode.handbookBank.exceptions.NoSuchCodeException;
import com.practiceOpenCode.handbookBank.models.codes.ChangeTypeCode;
import com.practiceOpenCode.handbookBank.repositories.codes.AbstractCodeRepository;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.List;

public class ChangeTypeCodeAdapter extends XmlAdapter<String, ChangeTypeCode> {
    private final List<ChangeTypeCode> changeTypeCodeList;

    public ChangeTypeCodeAdapter(AbstractCodeRepository<ChangeTypeCode> repository) {
        changeTypeCodeList = repository.findAll();
    }

    @Override
    public ChangeTypeCode unmarshal(String code) throws Exception {
        for (ChangeTypeCode changeTypeCode : changeTypeCodeList) {
            if (changeTypeCode.getCode().equals(code)) {
                if (!changeTypeCode.getDeleted()) {
                    return changeTypeCode;
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
    public String marshal(ChangeTypeCode changeTypeCode) throws Exception {
        return changeTypeCode.getCode();
    }
}