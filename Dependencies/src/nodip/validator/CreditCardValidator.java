package nodip.validator;

import java.time.LocalDate;

public class CreditCardValidator {

    public boolean isValidExpiry(int expiryYear, int expiryMonth) {
        LocalDate currentDate = LocalDate.now();
        return isExpiryFutureYear(currentDate, expiryYear) || isExpiryValidInYear(currentDate, expiryYear, expiryMonth);
    }

    private boolean isExpiryFutureYear(LocalDate currentDate, int expiryYear) {
        return expiryYear > currentDate.getYear();
    }

    private boolean isExpiryValidInYear(LocalDate currentDate, int expiryYear, int expiryMonth) {
        return (expiryYear == currentDate.getYear()) && (expiryMonth <= currentDate.getMonthValue());
    }

    public boolean isValidCardNumber(String cardNumber) {
        //perform LUHN check
        // Placeholder implementation
        return cardNumber != null && !cardNumber.isBlank();
    }

    public boolean isValidCVV2Code(String cvv2Code) {
        // Implement CVV2 code validation logic
        return true; // Placeholder implementation
    }
}
