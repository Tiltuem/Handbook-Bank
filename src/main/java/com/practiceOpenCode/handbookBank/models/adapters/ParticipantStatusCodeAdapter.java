package com.practiceOpenCode.handbookBank.models.adapters;

import com.practiceOpenCode.handbookBank.models.directories.ParticipantStatusCode;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public class ParticipantStatusCodeAdapter extends XmlAdapter<String, ParticipantStatusCode> {
    public ParticipantStatusCode unmarshal(String code) throws Exception {
        return new ParticipantStatusCode(code);
    }
    public String marshal(ParticipantStatusCode participantStatusCode) throws Exception {
        return participantStatusCode.getCode();
    }
}