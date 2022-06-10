package com.blog.formatter;

import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import org.springframework.stereotype.Component;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Component
public class DateFormatter implements Formatter<Date> {
    private SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");

    @Autowired
    public DateFormatter(SimpleDateFormat formatter) {
        this.formatter = formatter;
    }

    @Override
    public Date parse(String text, Locale locale) throws ParseException {
        Date date = formatter.parse(text);
        return date;
    }

    @Override
    public String print(Date object, Locale locale) {
        return null;
    }
}
