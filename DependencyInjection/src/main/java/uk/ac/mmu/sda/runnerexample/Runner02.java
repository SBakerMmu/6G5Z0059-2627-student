package uk.ac.mmu.sda.runnerexample;

import org.springframework.stereotype.Component;

@Component
class Runner02 implements org.springframework.boot.CommandLineRunner {


    private final CheckoutService checkoutService;

    Runner02(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @Override
    public void run(String... args)  {

        System.out.format("Hello from %s%n", this.getClass());
        System.out.format("Depends on %s%n", checkoutService);
        checkoutService.chargeCreditCard("1234-5678-9012-3456", 2028, 6, "123");
    }
}
