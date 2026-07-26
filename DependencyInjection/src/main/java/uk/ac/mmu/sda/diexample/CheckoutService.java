package uk.ac.mmu.sda.diexample;
class CheckoutService {
    private final AbstractCreditCardService abstractCreditCardService;

    CheckoutService(AbstractCreditCardService abstractCreditCardService) {

        this.abstractCreditCardService = abstractCreditCardService;
    }

    double getTotal() {
        //Get sum total of goods, tax and delivery
        return 100.0d;
    }
    void chargeCreditCard(String cardNumber, int expiryYear, int expiryMonth, String cv2) {
        System.out.println("CheckoutService charging credit card...");
        abstractCreditCardService.takePayment(cardNumber, expiryYear, expiryMonth, cv2, getTotal());
    }
}
