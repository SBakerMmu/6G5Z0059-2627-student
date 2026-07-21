package dipfactory.validator;

public class CreditCardValidator {

    private final YearMonthProviderFactory yearMonthProviderFactory;

    public CreditCardValidator(YearMonthProviderFactory yearMonthProviderFactory) {
        this.yearMonthProviderFactory = yearMonthProviderFactory;
    }

    public boolean isValidExpiry(int expiryYear, int expiryMonth) {
        YearMonthProvider yearMonthProvider = yearMonthProviderFactory.create();
        return isExpiryFutureYear(yearMonthProvider, expiryYear) || isExpiryValidInYear(yearMonthProvider, expiryYear, expiryMonth);
    }

    private boolean isExpiryFutureYear(YearMonthProvider yearMonthProvider, int expiryYear) {
        return expiryYear > yearMonthProvider.getYear();
    }

    private boolean isExpiryValidInYear(YearMonthProvider yearMonthProvider, int expiryYear, int expiryMonth) {
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
