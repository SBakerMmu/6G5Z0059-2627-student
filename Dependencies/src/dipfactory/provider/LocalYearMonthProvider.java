package dipfactory.provider;

import dipfactory.validator.YearMonthProvider;

import java.time.LocalDate;

public class LocalYearMonthProvider implements YearMonthProvider {
    private final int year;
    private final int month;

    public LocalYearMonthProvider() {
        LocalDate currentDate = LocalDate.now();
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
