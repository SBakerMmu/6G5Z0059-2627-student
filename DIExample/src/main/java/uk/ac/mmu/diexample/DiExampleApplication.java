package uk.ac.mmu.diexample;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

public class DiExampleApplication {

    public static void main(String[] args) {

        try (ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(DiExampleApplication.class)) {
            Basket basket = ctx.getBean(Basket.class);
            basket.chargeCreditCard("1234-5678-9012-3456", 2028, 6);
        }
    }

    @Bean
    @Scope("singleton")
    AbstractCreditCardService creditCardService() {
        return new ConcreteCreditCardService();
    }

    @Bean
    @Scope("prototype")
    Basket basket(AbstractCreditCardService creditCardService) {

        return new Basket(creditCardService);
    }
}
