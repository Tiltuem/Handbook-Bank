package com.practiceOpenCode.handbookBank.models.adapters;

import com.practiceOpenCode.handbookBank.exception.NoSuchCodeException;
import com.practiceOpenCode.handbookBank.models.context.ApplicationContextHolder;
import com.practiceOpenCode.handbookBank.models.codes.AccountRestrictionCode;
import com.practiceOpenCode.handbookBank.repositories.codes.AbstractCodeRepository;
import com.practiceOpenCode.handbookBank.repositories.codes.AccountRestrictionCodeRepository;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.springframework.context.ApplicationContext;


import java.util.List;


public class AccountRestrictionCodeAdapter extends XmlAdapter<String, AccountRestrictionCode> {
    private final List<AccountRestrictionCode> accountRestrictionCodeList;

    private final ApplicationContext ctx;

    AbstractCodeRepository<AccountRestrictionCode> repository;


    public AccountRestrictionCodeAdapter() {
        ctx = ApplicationContextHolder.getApplicationContext();

        accountRestrictionCodeList = ctx.getBean(AccountRestrictionCodeRepository.class).findAll();
    }

    @Override
    public AccountRestrictionCode unmarshal(String code) throws Exception {
        for (AccountRestrictionCode accountRestrictionCode : accountRestrictionCodeList) {
            if (accountRestrictionCode.getCode().equals(code)) {
                if(!accountRestrictionCode.getDeleted())  return accountRestrictionCode;
                else
                    throw new NoSuchCodeException("Ошибка: код '" + code + "' удалён.\nДля получения файла добавьте этот код в ограничения операций по счету");
            }
        }
        return new AccountRestrictionCode(code);
    }


    @Override
    public String marshal(AccountRestrictionCode accountRestrictionCode) throws Exception {
        return accountRestrictionCode.getCode();
    }
}