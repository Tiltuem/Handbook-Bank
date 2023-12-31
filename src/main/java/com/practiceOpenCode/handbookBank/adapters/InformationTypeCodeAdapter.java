package com.practiceOpenCode.handbookBank.adapters;

import com.practiceOpenCode.handbookBank.exceptions.NoSuchCodeException;
import com.practiceOpenCode.handbookBank.models.codes.InformationTypeCode;
import com.practiceOpenCode.handbookBank.repositories.codes.AbstractCodeRepository;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.List;

public class InformationTypeCodeAdapter extends XmlAdapter<String, InformationTypeCode> {
    private final List<InformationTypeCode> informationTypeCodeList;

    public InformationTypeCodeAdapter(AbstractCodeRepository<InformationTypeCode> repository) {
        informationTypeCodeList = repository.findAll();
    }

    @Override
    public InformationTypeCode unmarshal(String code) throws Exception {
        for (InformationTypeCode informationTypeCode : informationTypeCodeList) {
            if (informationTypeCode.getCode().equals(code)) {
                if (!informationTypeCode.getDeleted()) {
                    return informationTypeCode;
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
    public String marshal(InformationTypeCode informationTypeCode) throws Exception {
        return informationTypeCode.getCode();
    }
}