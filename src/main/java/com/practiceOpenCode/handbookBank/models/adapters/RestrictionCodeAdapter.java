package com.practiceOpenCode.handbookBank.models.adapters;

import com.practiceOpenCode.handbookBank.models.directories.RestrictionCode;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public class RestrictionCodeAdapter extends XmlAdapter<String, RestrictionCode> {
    public RestrictionCode unmarshal(String code) throws Exception {
        return new RestrictionCode(code);
    }
    public String marshal(RestrictionCode restrictionCode) throws Exception {
        return restrictionCode.getCode();
    }
}