package com.practiceOpenCode.handbookBank.models.adapters;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {

    private static final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public String marshal(LocalDateTime localDate) {
        return localDate.format(formatter);
    }

    @Override
    public LocalDateTime unmarshal(String localDateString) throws ParseException {
        return LocalDateTime.parse(localDateString, formatter);
    }

}
