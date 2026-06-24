package uk.ac.mmu.diexample;

public class ConcreteCreditCardService implements AbstractCreditCardService {
    @Override
    public void takePayment(double amount, String cardNumber, int expiryYear, int expiryMonth) {
        // Simulate taking a payment (for demonstration purposes)
        System.out.println("Payment of £" + amount + " taken from card " + cardNumber + ", expiring " + expiryMonth + "/" + expiryYear);
    }
}

