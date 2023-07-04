package com.practiceOpenCode.handbookBank.models.adapters;

import com.practiceOpenCode.handbookBank.models.directories.CreationReasonCode;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public class CreationReasonCodeAdapter extends XmlAdapter<String, CreationReasonCode> {
    public CreationReasonCode unmarshal(String code) throws Exception {
        return new CreationReasonCode(code);
    }
    public String marshal(CreationReasonCode creationReasonCode) throws Exception {
        return creationReasonCode.getCode();
    }
}
