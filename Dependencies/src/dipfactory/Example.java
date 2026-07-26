package dipfactory;


import dipfactory.provider.FixedYearMonthProvider;
import dipfactory.provider.FixedYearMonthProviderFactory;
import dipfactory.validator.CreditCardValidator;

public class Example {
    public static void run() {
        FixedYearMonthProviderFactory   yearMonthProviderFactory = new FixedYearMonthProviderFactory(2000, 12);
        CreditCardValidator validator = new CreditCardValidator(yearMonthProviderFactory);

        int year = 2025;
        int month = 1;
        System.out.format("Expiry %d/%d is valid %b%n", year, month, validator.isValidExpiry(year, month));




    }
}
