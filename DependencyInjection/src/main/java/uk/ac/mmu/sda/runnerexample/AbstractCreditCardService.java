package uk.ac.mmu.sda.runnerexample;
interface AbstractCreditCardService {
    void takePayment(String cardNumber, int expiryYear, int expiryMonth, String cv2, double amount);
}
