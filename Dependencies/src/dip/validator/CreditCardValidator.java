package dip.validator;

public class CreditCardValidator {

    private final YearMonthProvider  yearMonthProvider;

    public CreditCardValidator(YearMonthProvider yearMonthProvider) {
        this.yearMonthProvider = yearMonthProvider;
    }

    public boolean isValidExpiry(int expiryYear, int expiryMonth) {
        return isExpiryFutureYear(expiryYear) || isExpiryValidInYear(expiryYear, expiryMonth);
    }

    private boolean isExpiryFutureYear(int expiryYear) {
        return expiryYear > yearMonthProvider.getYear();
    }

    private boolean isExpiryValidInYear(int expiryYear, int expiryMonth) {
        return (expiryYear == yearMonthProvider.getYear()) && (expiryMonth <= yearMonthProvider.getMonth());
    }

    public boolean isValidCardNumber(String cardNumber) {
        // Implement card number validation logic
        return true; // Placeholder implementation
    }

    public boolean isValidCVV2Code(String cvv2Code) {
        // Implement CVV2 code validation logic
        return true; // Placeholder implementation
    }

}
