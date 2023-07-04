package com.practiceOpenCode.handbookBank.models.adapters;

import com.practiceOpenCode.handbookBank.models.directories.AccountRestrictionCode;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public class AccountRestrictionCodeAdapter extends XmlAdapter<String, AccountRestrictionCode> {
    public AccountRestrictionCode unmarshal(String code) throws Exception {
        return new AccountRestrictionCode(code);
    }
    public String marshal(AccountRestrictionCode accountRestrictionCode) throws Exception {
        return accountRestrictionCode.getCode();
    }
}