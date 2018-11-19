package com.nmkip.bankkata;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Clock {

    private final DateTimeFormatter DD_MM_YYYY = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public String todayAsString() {
        LocalDate today = today();
        return today.format(DD_MM_YYYY);
    }

    protected LocalDate today() {
        return LocalDate.now();
    }
}
