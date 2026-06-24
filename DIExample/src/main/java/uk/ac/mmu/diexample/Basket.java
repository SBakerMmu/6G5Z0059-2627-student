package uk.ac.mmu.diexample;

class Basket {

    private final AbstractCreditCardService creditCardService;

    public Basket(AbstractCreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    public void chargeCreditCard(String cardNumber, int expiryYear, int expiryMonth) {
        creditCardService.takePayment(100.0d, cardNumber, expiryYear, expiryMonth);
    }
}
