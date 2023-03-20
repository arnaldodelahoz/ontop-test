package com.ontop.web.shared;

import com.ontop.application.exceptions.FormatDateException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
public class DateUtil {
    public static final Integer START_DATE = 1;
    public static final Integer END_DATE = 2;

    public static LocalDateTime formatDate(String date, Integer type){
        if(date == null && type.equals(START_DATE)){
            return LocalDateTime.of(2000, 1, 1,0,0); // fecha por defecto
        }
        if(date ==null && type.equals(END_DATE)){
            return LocalDateTime.now().withHour(23).withMinute(59); // fecha actual por defecto
        }
        try {
            return LocalDateTime.of(LocalDate.parse(date), LocalTime.of(0,0));
        } catch (DateTimeParseException e) {
            throw new FormatDateException("Invalid date format, insert a valid format yyyy-MM-dd, 2021-05-18");
        }

    }
}
