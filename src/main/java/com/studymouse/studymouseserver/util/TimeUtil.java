package com.studymouse.studymouseserver.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Created by jyami on 2020/07/14
 */
public class TimeUtil {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static LocalDateTime getStartDate(String startDate) {
        try {
            return LocalDate.parse(startDate, DATE_TIME_FORMATTER).atStartOfDay();
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(String.format("잘못된 날짜 형식입니다: expected YYYY-MM-DD :actual %s", startDate));
        }
    }

    public static LocalDateTime getEndDate(String endDate) {
        try {
            return LocalDate.parse(endDate, DATE_TIME_FORMATTER).atTime(23, 59, 59);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(String.format("잘못된 날짜 형식입니다: expected YYYY-MM-DD :actual %s", endDate));
        }
    }

}
