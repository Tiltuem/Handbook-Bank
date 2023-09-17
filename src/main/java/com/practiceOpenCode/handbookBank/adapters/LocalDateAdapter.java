package com.practiceOpenCode.handbookBank.adapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public String marshal(LocalDate localDate) {
        return localDate.format(formatter);
    }

    @Override
    public LocalDate unmarshal(String localDateString) throws ParseException {
        return LocalDate.parse(localDateString, formatter);
    }

}