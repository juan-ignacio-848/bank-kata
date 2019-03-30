package com.nmkip.bankkata;

import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ClockShould {

    private static final String TODAY = "28/03/2019";

    @Test
    public void format_today_date_as_DD_MM_YYYY() {
        Clock clock = new TestableClock();

        assertThat(clock.todayAsString(), is(TODAY));
    }

    private class TestableClock extends Clock {

        @Override
        protected LocalDate today() {
            return LocalDate.of(2019, 3, 28);
        }
    }
}