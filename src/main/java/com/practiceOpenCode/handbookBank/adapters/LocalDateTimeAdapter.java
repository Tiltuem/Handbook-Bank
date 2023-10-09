package com.practiceOpenCode.handbookBank.adapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

    @Override
    public String marshal(LocalDateTime localDate) {
        return localDate.format(FORMATTER);
    }

    @Override
    public LocalDateTime unmarshal(String localDateString) throws ParseException {
        return LocalDateTime.parse(localDateString, FORMATTER);
    }

}
