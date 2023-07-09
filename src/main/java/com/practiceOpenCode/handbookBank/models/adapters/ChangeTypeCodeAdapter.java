package com.practiceOpenCode.handbookBank.models.adapters;

import com.practiceOpenCode.handbookBank.exception.NoSuchCodeException;
import com.practiceOpenCode.handbookBank.models.context.ApplicationContextHolder;
import com.practiceOpenCode.handbookBank.models.directories.ChangeTypeCode;
import com.practiceOpenCode.handbookBank.repositories.ChangeTypeCodeRepository;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class ChangeTypeCodeAdapter extends XmlAdapter<String, ChangeTypeCode> {
    private final List<ChangeTypeCode> changeTypeCodeList;
    private final ApplicationContext ctx;

    public ChangeTypeCodeAdapter() {
        ctx = ApplicationContextHolder.getApplicationContext();
        changeTypeCodeList = ctx.getBean(ChangeTypeCodeRepository.class).findAll();
    }

    @Override
    public ChangeTypeCode unmarshal(String code) throws Exception {
        for (ChangeTypeCode changeTypeCode : changeTypeCodeList) {
            if (changeTypeCode.getCode().equals(code)) return changeTypeCode;
        }
        throw new NoSuchCodeException(code + "code not found in directory.");
    }

    @Override
    public String marshal(ChangeTypeCode changeTypeCode) throws Exception {
        return changeTypeCode.getCode();
    }
}