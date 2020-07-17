package com.studymouse.studymouseserver.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by jyami on 2020/07/18
 */
class MailDatesTest {

    @Test
    void getMailDate() {
        LocalDate nowDate = LocalDate.of(2020, Month.JULY, 1);

        assertAll(
                () -> assertThat(MailDates.ONE_DAY.getStartTime(nowDate).getMonth()).isEqualTo(Month.JUNE),
                () -> assertThat(MailDates.ONE_DAY.getStartTime(nowDate).getDayOfMonth()).isEqualTo(30),

                () -> assertThat(MailDates.THREE_DAYS.getStartTime(nowDate).getMonth()).isEqualTo(Month.JUNE),
                () -> assertThat(MailDates.THREE_DAYS.getStartTime(nowDate).getDayOfMonth()).isEqualTo(28),

                () -> assertThat(MailDates.ONE_WEEK.getStartTime(nowDate).getMonth()).isEqualTo(Month.JUNE),
                () -> assertThat(MailDates.ONE_WEEK.getStartTime(nowDate).getDayOfMonth()).isEqualTo(24)
        );
    }

}