package com.practiceOpenCode.handbookBank.adapters;

import com.practiceOpenCode.handbookBank.exceptions.NoSuchCodeException;
import com.practiceOpenCode.handbookBank.models.codes.RestrictionCode;
import com.practiceOpenCode.handbookBank.repositories.codes.AbstractCodeRepository;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.List;

public class RestrictionCodeAdapter extends XmlAdapter<String, RestrictionCode> {
    private final List<RestrictionCode> restrictionCodeList;

    public RestrictionCodeAdapter(AbstractCodeRepository<RestrictionCode> repository) {
        restrictionCodeList = repository.findAll();
    }

    @Override
    public RestrictionCode unmarshal(String code) throws Exception {
        for (RestrictionCode restrictionCode : restrictionCodeList) {
            if (restrictionCode.getCode().equals(code)) {
                if(!restrictionCode.getDeleted())  return restrictionCode;
                else
                    throw new NoSuchCodeException("Ошибка: код '" + code + "' удалён.\nДля получения файла восстановите этот код в ограничения операций по счету");
            }
        }
        throw new NoSuchCodeException("Ошибка: код '" + code + "' отсутствует.\nДля получения файла добавьте этот код в ограничения операций по счету");
    }

    @Override
    public String marshal(RestrictionCode restrictionCode) throws Exception {
        return restrictionCode.getCode();
    }
}