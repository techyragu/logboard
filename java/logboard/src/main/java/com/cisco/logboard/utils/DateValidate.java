package com.cisco.logboard.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateValidate implements DateValidator {
    private DateTimeFormatter dateFormatter;
     
    public DateValidate(DateTimeFormatter dateFormatter) {
        this.dateFormatter = dateFormatter;
    }
 
    @Override
    public boolean isValid(String dateStr) {
        try {
            LocalDateTime.parse(dateStr, this.dateFormatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
}
