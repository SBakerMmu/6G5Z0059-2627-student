package uk.ac.mmu.sda.diexample;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

public class DiExampleApplication {

    public static void main(String[] args) {
        try (ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(DiExampleApplication.class)) {
            CheckoutService checkoutService = ctx.getBean(CheckoutService.class);
            checkoutService.chargeCreditCard("1234-5678-9012-3456", 2028, 6, "123");
        }
    }

    @Bean
    @Scope("singleton")
    AbstractCreditCardService creditCardService() {
        return new RealCreditCardService();
    }

    @Bean
    @Scope("prototype") CheckoutService checkoutService(AbstractCreditCardService creditCardService) {
        return new CheckoutService(creditCardService);
    }
}
