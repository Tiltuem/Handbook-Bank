package com.practiceOpenCode.handbookBank.models.adapters;

import com.practiceOpenCode.handbookBank.exception.NoSuchCodeException;
import com.practiceOpenCode.handbookBank.models.codes.AccountRestrictionCode;
import com.practiceOpenCode.handbookBank.models.context.ApplicationContextHolder;
import com.practiceOpenCode.handbookBank.models.codes.RestrictionCode;
import com.practiceOpenCode.handbookBank.repositories.codes.RestrictionCodeRepository;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class RestrictionCodeAdapter extends XmlAdapter<String, RestrictionCode> {
    private final List<RestrictionCode> restrictionCodeList;
    private final ApplicationContext ctx;

    public RestrictionCodeAdapter() {
        ctx = ApplicationContextHolder.getApplicationContext();
        restrictionCodeList = ctx.getBean(RestrictionCodeRepository.class).findAll();
    }

    @Override
    public RestrictionCode unmarshal(String code) throws Exception {
        for (RestrictionCode restrictionCode : restrictionCodeList) {
            if (restrictionCode.getCode().equals(code)) {
                if(!restrictionCode.getDeleted())  return restrictionCode;
                else
                    throw new NoSuchCodeException("Ошибка: код '" + code + "' удалён.\nДля получения файла добавьте этот код в ограничения операций по счету");
            }
        }
        return new RestrictionCode(code);
    }

    @Override
    public String marshal(RestrictionCode restrictionCode) throws Exception {
        return restrictionCode.getCode();
    }
}