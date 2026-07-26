package dipfactory.provider;

import dipfactory.validator.YearMonthProvider;
import dipfactory.validator.YearMonthProviderFactory;

import java.time.LocalDate;

public class LocalYearMonthProviderFactory implements YearMonthProviderFactory {

    @Override
    public YearMonthProvider create() {
        return new LocalYearMonthProvider();
    }
}
