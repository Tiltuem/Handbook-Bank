package com.practiceOpenCode.handbookBank.models.adapters;

import com.practiceOpenCode.handbookBank.models.directories.AccountStatusCode;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public class AccountStatusCodeAdapter extends XmlAdapter<String, AccountStatusCode> {
    public AccountStatusCode unmarshal(String code) throws Exception {
        return new AccountStatusCode(code);
    }
    public String marshal(AccountStatusCode accountStatusCode) throws Exception {
        return accountStatusCode.getCode();
    }
}
