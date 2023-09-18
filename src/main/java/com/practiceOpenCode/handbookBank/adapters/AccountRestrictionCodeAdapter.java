package com.practiceOpenCode.handbookBank.adapters;

import com.practiceOpenCode.handbookBank.exceptions.NoSuchCodeException;
import com.practiceOpenCode.handbookBank.models.codes.AccountRestrictionCode;
import com.practiceOpenCode.handbookBank.repositories.codes.AbstractCodeRepository;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.List;


public class AccountRestrictionCodeAdapter extends XmlAdapter<String, AccountRestrictionCode> {
    private final List<AccountRestrictionCode> accountRestrictionCodeList;

    public AccountRestrictionCodeAdapter(AbstractCodeRepository<AccountRestrictionCode> repository) {
        accountRestrictionCodeList = repository.findAll();
    }

    @Override
    public AccountRestrictionCode unmarshal(String code) throws Exception {
        for (AccountRestrictionCode accountRestrictionCode : accountRestrictionCodeList) {
            if (accountRestrictionCode.getCode().equals(code)) {
                if (!accountRestrictionCode.getDeleted()) return accountRestrictionCode;
                else
                    throw new NoSuchCodeException("Ошибка: код '" + code + "' удалён.\nДля получения файла восстановите этот код в ограничения операций по счету");
            }
        }
        throw new NoSuchCodeException("Ошибка: код '" + code + "' отсутствует.\nДля получения файла добавьте этот код в ограничения операций по счету");
    }


    @Override
    public String marshal(AccountRestrictionCode accountRestrictionCode) throws Exception {
        return accountRestrictionCode.getCode();
    }
}
