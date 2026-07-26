package dip;


import dip.provider.FixedYearMonthProvider;
import dip.validator.CreditCardValidator;

public class Example {
    public static void run() {
        FixedYearMonthProvider yearMonthProvider = new FixedYearMonthProvider(2000, 12);
        CreditCardValidator validator = new CreditCardValidator(yearMonthProvider);

        int year = 2025;
        int month = 1;
        System.out.format("Expiry %d/%d is valid %b%n", year, month, validator.isValidExpiry(year, month));




    }
}
