package com.practiceOpenCode.handbookBank.models.adapters;

import com.practiceOpenCode.handbookBank.exception.NoSuchCodeException;
import com.practiceOpenCode.handbookBank.models.codes.AccountRestrictionCode;
import com.practiceOpenCode.handbookBank.models.context.ApplicationContextHolder;
import com.practiceOpenCode.handbookBank.models.codes.ChangeTypeCode;
import com.practiceOpenCode.handbookBank.repositories.codes.ChangeTypeCodeRepository;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class ChangeTypeCodeAdapter extends XmlAdapter<String, ChangeTypeCode> {
    private final List<ChangeTypeCode> changeTypeCodeList;

    public ChangeTypeCodeAdapter() {
        ApplicationContext ctx = ApplicationContextHolder.getApplicationContext();
        changeTypeCodeList = ctx.getBean(ChangeTypeCodeRepository.class).findAll();
    }

    @Override
    public ChangeTypeCode unmarshal(String code) throws Exception {
        for (ChangeTypeCode changeTypeCode : changeTypeCodeList) {
            if (changeTypeCode.getCode().equals(code)) {
                if(!changeTypeCode.getDeleted())  return changeTypeCode;
                else
                    throw new NoSuchCodeException("Ошибка: код '" + code + "' удалён.\nДля получения файла добавьте этот код в ограничения операций по счету");
            }
        }
        return new ChangeTypeCode(code);
    }

    @Override
    public String marshal(ChangeTypeCode changeTypeCode) throws Exception {
        return changeTypeCode.getCode();
    }
}