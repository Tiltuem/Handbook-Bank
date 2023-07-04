package com.practiceOpenCode.handbookBank.models.adapters;

import com.practiceOpenCode.handbookBank.models.directories.ChangeTypeCode;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public class ChangeTypeCodeAdapter extends XmlAdapter<String, ChangeTypeCode> {
    public ChangeTypeCode unmarshal(String code) throws Exception {
        return new ChangeTypeCode(code);
    }
    public String marshal(ChangeTypeCode changeTypeCode) throws Exception {
        return changeTypeCode.getCode();
    }
}