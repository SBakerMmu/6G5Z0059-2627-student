package dipfactory.provider;

import dipfactory.validator.YearMonthProvider;
import dipfactory.validator.YearMonthProviderFactory;

public class FixedYearMonthProviderFactory implements YearMonthProviderFactory {
    private final int year;
    private final int month;

    public FixedYearMonthProviderFactory(int year, int month) {
        this.year = year;
        this.month = month;
    }


    @Override
    public YearMonthProvider create() {
        return new FixedYearMonthProvider(year, month);
    }
}
