package uk.ac.mmu.sda.diexample;
interface AbstractCreditCardService {
    void takePayment(String cardNumber, int expiryYear, int expiryMonth, String cv2, double amount);
}

