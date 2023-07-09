package com.practiceOpenCode.handbookBank.models.adapters;

import com.practiceOpenCode.handbookBank.exception.NoSuchCodeException;
import com.practiceOpenCode.handbookBank.models.context.ApplicationContextHolder;
import com.practiceOpenCode.handbookBank.models.directories.AccountRestrictionCode;
import com.practiceOpenCode.handbookBank.repositories.AccountRestrictionCodeRepository;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import org.springframework.context.ApplicationContext;

import java.util.List;


public class AccountRestrictionCodeAdapter extends XmlAdapter<String, AccountRestrictionCode> {
    private final List<AccountRestrictionCode> accountRestrictionCodeList;
    private final ApplicationContext ctx;

    public AccountRestrictionCodeAdapter() {
        ctx = ApplicationContextHolder.getApplicationContext();
        accountRestrictionCodeList = ctx.getBean(AccountRestrictionCodeRepository.class).findAll();
    }

    @Override
    public AccountRestrictionCode unmarshal(String code) throws Exception {
        for (AccountRestrictionCode accountRestrictionCode : accountRestrictionCodeList) {
            if (accountRestrictionCode.getCode().equals(code)) return accountRestrictionCode;
        }
        throw new NoSuchCodeException(code + "code not found in directory.");
    }

    @Override
    public String marshal(AccountRestrictionCode accountRestrictionCode) throws Exception {
        return accountRestrictionCode.getCode();
    }
}