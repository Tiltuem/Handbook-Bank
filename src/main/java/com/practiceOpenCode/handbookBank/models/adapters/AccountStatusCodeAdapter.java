package com.practiceOpenCode.handbookBank.models.adapters;


import com.practiceOpenCode.handbookBank.exception.NoSuchCodeException;
import com.practiceOpenCode.handbookBank.models.context.ApplicationContextHolder;
import com.practiceOpenCode.handbookBank.models.codes.AccountStatusCode;
import com.practiceOpenCode.handbookBank.repositories.codes.AccountStatusCodeRepository;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import org.springframework.context.ApplicationContext;

import java.util.List;

public class AccountStatusCodeAdapter extends XmlAdapter<String, AccountStatusCode> {
    private final List<AccountStatusCode> accountStatusCodeList;
    private final ApplicationContext ctx;

    public AccountStatusCodeAdapter() {
        ctx = ApplicationContextHolder.getApplicationContext();
        accountStatusCodeList = ctx.getBean(AccountStatusCodeRepository.class).findAll();
    }

    @Override
    public AccountStatusCode unmarshal(String code) throws Exception {
        for (AccountStatusCode accountStatusCode : accountStatusCodeList) {
            if (accountStatusCode.getCode().equals(code)) return accountStatusCode;
        }
        throw new NoSuchCodeException(code + "code not found in directory.");
    }

    @Override
    public String marshal(AccountStatusCode accountStatusCode) throws Exception {
        return accountStatusCode.getCode();
    }
}
