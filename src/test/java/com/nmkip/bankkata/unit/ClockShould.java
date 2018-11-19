package com.nmkip.bankkata.unit;

import com.nmkip.bankkata.Clock;
import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class ClockShould {

    @Test
    public void return_todays_date_in_dd_MM_yyyy_format() {
        Clock clock = new TestableClock();
        String date = clock.todayAsString();
        assertThat(date).isEqualTo("25/12/2018");
    }

    private class TestableClock extends Clock {

        @Override
        protected LocalDate today() {
            return LocalDate.of(2018, 12, 25);
        }
    }
}