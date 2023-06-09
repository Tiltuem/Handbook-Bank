package com.practiceOpenCode.handbookBank.models.adapters;

import com.practiceOpenCode.handbookBank.exception.NoSuchCodeException;
import com.practiceOpenCode.handbookBank.models.context.ApplicationContextHolder;
import com.practiceOpenCode.handbookBank.models.directories.InformationTypeCode;
import com.practiceOpenCode.handbookBank.repositories.InformationTypeCodeRepository;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class InformationTypeCodeAdapter extends XmlAdapter<String, InformationTypeCode> {
    private final List<InformationTypeCode> informationTypeCodeList;
    private final ApplicationContext ctx;

    public InformationTypeCodeAdapter() {
        ctx = ApplicationContextHolder.getApplicationContext();
        informationTypeCodeList = ctx.getBean(InformationTypeCodeRepository.class).findAll();
    }

    @Override
    public InformationTypeCode unmarshal(String code) throws Exception {
        for (InformationTypeCode informationTypeCode : informationTypeCodeList) {
            if (informationTypeCode.getCode().equals(code)) return informationTypeCode;
        }
        throw new NoSuchCodeException(code + "code not found in directory.");
    }

    @Override
    public String marshal(InformationTypeCode informationTypeCode) throws Exception {
        return informationTypeCode.getCode();
    }
}