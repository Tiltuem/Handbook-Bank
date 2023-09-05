package com.practiceOpenCode.handbookBank.models.adapters;

import com.practiceOpenCode.handbookBank.exception.NoSuchCodeException;
import com.practiceOpenCode.handbookBank.models.codes.AccountRestrictionCode;
import com.practiceOpenCode.handbookBank.models.context.ApplicationContextHolder;
import com.practiceOpenCode.handbookBank.models.codes.CreationReasonCode;
import com.practiceOpenCode.handbookBank.repositories.codes.CreationReasonCodeRepository;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class CreationReasonCodeAdapter extends XmlAdapter<String, CreationReasonCode> {
    private final List<CreationReasonCode> creationReasonCodeList;

    public CreationReasonCodeAdapter() {
        ApplicationContext ctx = ApplicationContextHolder.getApplicationContext();
        creationReasonCodeList = ctx.getBean(CreationReasonCodeRepository.class).findAll();
    }

    @Override
    public CreationReasonCode unmarshal(String code) throws Exception {
        for (CreationReasonCode creationReasonCode : creationReasonCodeList) {
            if (creationReasonCode.getCode().equals(code)) {
                if(!creationReasonCode.getDeleted())  return creationReasonCode;
                else
                    throw new NoSuchCodeException("Ошибка: код '" + code + "' удалён.\nДля получения файла добавьте этот код в ограничения операций по счету");
            }
        }
        return new CreationReasonCode(code);
    }

    @Override
    public String marshal(CreationReasonCode creationReasonCode) throws Exception {
        return creationReasonCode.getCode();
    }
}
