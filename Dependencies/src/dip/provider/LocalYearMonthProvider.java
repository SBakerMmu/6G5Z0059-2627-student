package dip.provider;

import dip.validator.YearMonthProvider;

import java.time.LocalDate;

public class LocalYearMonthProvider implements YearMonthProvider {
    private final int year;
    private final int month;

    public LocalYearMonthProvider() {
        LocalDate currentDate = java.time.LocalDate.now();
        this.year = currentDate.getYear();
        this.month = currentDate.getMonthValue();
    }

    @Override
    public int getYear() {
        return year;
    }

    @Override
    public int getMonth() {
        return month;
    }
}
