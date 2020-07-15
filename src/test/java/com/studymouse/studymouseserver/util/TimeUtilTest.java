package com.studymouse.studymouseserver.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by jyami on 2020/07/14
 */
class TimeUtilTest {

    @Test
    public void getStartDate() {
        LocalDateTime startDate = TimeUtil.getStartDate("2020-07-14");
        assertAll(
                () -> assertThat(startDate.getYear()).isEqualTo(2020),
                () -> assertThat(startDate.getMonth()).isEqualTo(Month.JULY),
                () -> assertThat(startDate.getDayOfMonth()).isEqualTo(14),
                () -> assertThat(startDate.getHour()).isEqualTo(0),
                () -> assertThat(startDate.getMinute()).isEqualTo(0),
                () -> assertThat(startDate.getSecond()).isEqualTo(0)
        );
    }

    @Test
    public void getEndDate() {
        LocalDateTime startDate = TimeUtil.getEndDate("2020-07-14");
        assertAll(
                () -> assertThat(startDate.getYear()).isEqualTo(2020),
                () -> assertThat(startDate.getMonth()).isEqualTo(Month.JULY),
                () -> assertThat(startDate.getDayOfMonth()).isEqualTo(14),
                () -> assertThat(startDate.getHour()).isEqualTo(23),
                () -> assertThat(startDate.getMinute()).isEqualTo(59),
                () -> assertThat(startDate.getSecond()).isEqualTo(59)
        );
    }

    @Test
    public void checkException() {
        assertThrows(IllegalArgumentException.class, () -> {
            LocalDateTime startDate = TimeUtil.getEndDate("2020-07-145");
        });
    }

}