package com.nmkip.bankkata;

import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class ClockShould {

    @Test
    public void return_todays_date_in_dd_MM_yyyy_format() {
        Clock clock = new TestableClock();

        assertThat(clock.todayAsString()).isEqualTo("17/02/2019");
    }

    private class TestableClock extends Clock {
        @Override
        protected LocalDate today() {
            return LocalDate.of(2019, 2, 17);
        }
    }
}
