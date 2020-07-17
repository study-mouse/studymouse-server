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

    ONE_DAY(1, ChronoUnit.DAYS),
    THREE_DAYS(3, ChronoUnit.DAYS),
    ONE_WEEK(1, ChronoUnit.WEEKS);

    private int subtraction;
    private ChronoUnit chronoUnit;

    public LocalDateTime getStartTime(LocalDate now) {
        LocalDate date = now.minus(this.subtraction, this.chronoUnit);
        return LocalDateTime.of(date, LocalTime.of(0, 0, 0));
    }

    public LocalDateTime getEndTime(LocalDate now) {
        LocalDate date = now.minus(this.subtraction, this.chronoUnit);
        return LocalDateTime.of(date, LocalTime.of(23, 59, 59));
    }
}
