package nodip;


import nodip.validator.CreditCardValidator;

public class Example {
    public static void run() {
        CreditCardValidator validator = new CreditCardValidator();

        int year = 2025;
        int month = 1;
        System.out.format("Expiry %d/%d is valid %b%n", year, month, validator.isValidExpiry(year, month));




    }
}
