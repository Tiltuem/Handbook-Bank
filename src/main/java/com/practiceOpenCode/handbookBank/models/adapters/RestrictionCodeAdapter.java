package com.practiceOpenCode.handbookBank.models.adapters;

import com.practiceOpenCode.handbookBank.exception.NoSuchCodeException;
import com.practiceOpenCode.handbookBank.models.context.ApplicationContextHolder;
import com.practiceOpenCode.handbookBank.models.directories.RestrictionCode;
import com.practiceOpenCode.handbookBank.repositories.RestrictionCodeRepository;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;
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
            if (restrictionCode.getCode().equals(code)) return restrictionCode;
        }
        throw new NoSuchCodeException(code + "code not found in directory.");
    }

    @Override
    public String marshal(RestrictionCode restrictionCode) throws Exception {
        return restrictionCode.getCode();
    }
}