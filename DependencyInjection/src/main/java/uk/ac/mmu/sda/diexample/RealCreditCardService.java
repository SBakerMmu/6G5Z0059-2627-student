package uk.ac.mmu.sda.diexample;

class RealCreditCardService implements AbstractCreditCardService {

    public void takePayment(String cardNumber, int expiryYear, int expiryMonth, String cv2, double amount) {
        System.out.format("Real Credit Card service charging credit card %s for £%.2f%n", cardNumber, amount);
    }

}
