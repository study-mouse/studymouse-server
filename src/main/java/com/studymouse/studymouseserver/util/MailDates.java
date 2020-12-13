package com.studymouse.studymouseserver.util;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

/**
 * Created by jyami on 2020/07/18
 */

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum MailDates {

    ONE_DAY(1, ChronoUnit.DAYS, "1일전"),
    THREE_DAYS(3, ChronoUnit.DAYS, "3일전"),
    ONE_WEEK(1, ChronoUnit.WEEKS, "1주전");

    private int subtraction;
    private ChronoUnit chronoUnit;
    @Getter
    private String text;

    public LocalDateTime getStartTime(LocalDate now) {
        LocalDate date = now.minus(this.subtraction, this.chronoUnit);
        return LocalDateTime.of(date, LocalTime.of(0, 0, 0));
    }

    public LocalDateTime getEndTime(LocalDate now) {
        LocalDate date = now.minus(this.subtraction, this.chronoUnit);
        return LocalDateTime.of(date, LocalTime.of(23, 59, 59));
    }
}
