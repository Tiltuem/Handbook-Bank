package com.practiceOpenCode.handbookBank.models.adapters;

import com.practiceOpenCode.handbookBank.exception.NoSuchCodeException;
import com.practiceOpenCode.handbookBank.models.codes.AccountRestrictionCode;
import com.practiceOpenCode.handbookBank.models.context.ApplicationContextHolder;
import com.practiceOpenCode.handbookBank.models.codes.RegulationAccountTypeCode;
import com.practiceOpenCode.handbookBank.repositories.codes.RegulationAccountTypeCodeRepository;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class RegulationAccountTypeCodeAdapter extends XmlAdapter<String, RegulationAccountTypeCode> {
    private final List<RegulationAccountTypeCode> regulationAccountTypeCodeList;
    private final ApplicationContext ctx;

    public RegulationAccountTypeCodeAdapter() {
        ctx = ApplicationContextHolder.getApplicationContext();
        regulationAccountTypeCodeList = ctx.getBean(RegulationAccountTypeCodeRepository.class).findAll();
    }

    @Override
    public RegulationAccountTypeCode unmarshal(String code) throws Exception {
        for (RegulationAccountTypeCode regulationAccountTypeCode : regulationAccountTypeCodeList) {
            if (regulationAccountTypeCode.getCode().equals(code)) {
                if(!regulationAccountTypeCode.getDeleted())  return regulationAccountTypeCode;
                else
                    throw new NoSuchCodeException("Ошибка: код '" + code + "' удалён.\nДля получения файла добавьте этот код в ограничения операций по счету");
            }
        }
        return new RegulationAccountTypeCode(code);
    }

    @Override
    public String marshal(RegulationAccountTypeCode regulationAccountTypeCode) throws Exception {
        return regulationAccountTypeCode.getCode();
    }
}