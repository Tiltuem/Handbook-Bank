package com.practiceOpenCode.handbookBank.models.adapters;

import com.practiceOpenCode.handbookBank.models.directories.ParticipantTypeCode;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public class ParticipantTypeCodeAdapter extends XmlAdapter<String, ParticipantTypeCode> {
    public ParticipantTypeCode unmarshal(String code) throws Exception {
        return new ParticipantTypeCode(code);
    }
    public String marshal(ParticipantTypeCode participantTypeCode) throws Exception {
        return participantTypeCode.getCode();
    }
}