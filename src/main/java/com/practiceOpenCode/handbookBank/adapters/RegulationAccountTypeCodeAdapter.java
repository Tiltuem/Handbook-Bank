package com.practiceOpenCode.handbookBank.adapters;

import com.practiceOpenCode.handbookBank.exceptions.NoSuchCodeException;
import com.practiceOpenCode.handbookBank.models.codes.RegulationAccountTypeCode;
import com.practiceOpenCode.handbookBank.repositories.codes.AbstractCodeRepository;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.List;

public class RegulationAccountTypeCodeAdapter extends XmlAdapter<String, RegulationAccountTypeCode> {
    private final List<RegulationAccountTypeCode> regulationAccountTypeCodeList;

    public RegulationAccountTypeCodeAdapter(AbstractCodeRepository<RegulationAccountTypeCode> repository) {
        regulationAccountTypeCodeList = repository.findAll();
    }

    @Override
    public RegulationAccountTypeCode unmarshal(String code) throws Exception {
        for (RegulationAccountTypeCode regulationAccountTypeCode : regulationAccountTypeCodeList) {
            if (regulationAccountTypeCode.getCode().equals(code)) {
                if (!regulationAccountTypeCode.getDeleted()) {
                    return regulationAccountTypeCode;
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
    public String marshal(RegulationAccountTypeCode regulationAccountTypeCode) throws Exception {
        return regulationAccountTypeCode.getCode();
    }
}