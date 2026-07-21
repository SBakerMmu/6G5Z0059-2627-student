package dipfactory.provider;

import dipfactory.validator.YearMonthProvider;

public class FixedYearMonthProvider implements YearMonthProvider {
    private final int year;
    private final int month;

    public FixedYearMonthProvider(int year, int month) {
        this.year = year;
        this.month = month;
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
