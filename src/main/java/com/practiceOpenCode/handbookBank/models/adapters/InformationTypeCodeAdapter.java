package com.practiceOpenCode.handbookBank.models.adapters;

import com.practiceOpenCode.handbookBank.models.directories.InformationTypeCode;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public class InformationTypeCodeAdapter extends XmlAdapter<String, InformationTypeCode> {
    public InformationTypeCode unmarshal(String code) throws Exception {
        return new InformationTypeCode(code);
    }
    public String marshal(InformationTypeCode informationTypeCode) throws Exception {
        return informationTypeCode.getCode();
    }
}