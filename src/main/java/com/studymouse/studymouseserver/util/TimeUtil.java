package com.studymouse.studymouseserver.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by jyami on 2020/07/14
 */
public class TimeUtil {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static LocalDateTime getStartDate(String startDate){
        return LocalDate.parse(startDate, DATE_TIME_FORMATTER).atStartOfDay();
    }

    public static LocalDateTime getEndDate(String endDate){
        return LocalDate.parse(endDate, DATE_TIME_FORMATTER).atTime(23, 59, 59);
    }

}
