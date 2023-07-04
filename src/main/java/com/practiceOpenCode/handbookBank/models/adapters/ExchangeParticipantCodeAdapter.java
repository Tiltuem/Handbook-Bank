package com.practiceOpenCode.handbookBank.models.adapters;

import com.practiceOpenCode.handbookBank.models.directories.ExchangeParticipantCode;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public class ExchangeParticipantCodeAdapter extends XmlAdapter<String, ExchangeParticipantCode> {
    public ExchangeParticipantCode unmarshal(String code) throws Exception {
        return new ExchangeParticipantCode(code);
    }
    public String marshal(ExchangeParticipantCode exchangeParticipantCode) throws Exception {
        return exchangeParticipantCode.getCode();
    }
}