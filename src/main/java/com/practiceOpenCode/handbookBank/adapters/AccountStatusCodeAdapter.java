package com.practiceOpenCode.handbookBank.adapters;


import com.practiceOpenCode.handbookBank.exceptions.NoSuchCodeException;
import com.practiceOpenCode.handbookBank.models.codes.AccountStatusCode;
import com.practiceOpenCode.handbookBank.repositories.codes.AbstractCodeRepository;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.List;


public class AccountStatusCodeAdapter extends XmlAdapter<String, AccountStatusCode> {

    private final List<AccountStatusCode> accountStatusCodeList;


    public AccountStatusCodeAdapter(AbstractCodeRepository<AccountStatusCode> repository) {
        accountStatusCodeList = repository.findAll();
    }

    @Override
    public AccountStatusCode unmarshal(String code) throws Exception {
        for (AccountStatusCode accountStatusCode : accountStatusCodeList) {
            if (accountStatusCode.getCode().equals(code)) {
                if (!accountStatusCode.getDeleted()) {
                    return accountStatusCode;
                } else {
                    throw new NoSuchCodeException("Ошибка: код '" + code
                            + "' удалён.\nДля получения файла восстановите этот код в ограничения операций по счету");
                }
            }
        }
        throw new NoSuchCodeException("Ошибка: код '" + code
                + "' удалён.\nДля получения файла восстановите этот код в ограничения операций по счету");
    }

    @Override
    public String marshal(AccountStatusCode accountStatusCode) throws Exception {
        return accountStatusCode.getCode();
    }

}
