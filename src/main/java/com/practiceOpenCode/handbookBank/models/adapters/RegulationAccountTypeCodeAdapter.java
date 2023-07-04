package com.practiceOpenCode.handbookBank.models.adapters;

import com.practiceOpenCode.handbookBank.models.directories.RegulationAccountTypeCode;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public class RegulationAccountTypeCodeAdapter extends XmlAdapter<String, RegulationAccountTypeCode> {
    public RegulationAccountTypeCode unmarshal(String code) throws Exception {
        return new RegulationAccountTypeCode(code);
    }
    public String marshal(RegulationAccountTypeCode regulationAccountTypeCode) throws Exception {
        return regulationAccountTypeCode.getCode();
    }
}