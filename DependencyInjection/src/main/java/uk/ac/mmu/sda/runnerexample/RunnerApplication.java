package uk.ac.mmu.sda.runnerexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RunnerApplication {

    static void main(String[] args) {
        SpringApplication.run(RunnerApplication.class, args);
    }

    @Bean()
    AbstractCreditCardService createCardService() {
        return new RealCreditCardService();
    }

    @Bean
    CheckoutService createCheckoutService(AbstractCreditCardService abstractCreditCardService) {
        return new CheckoutService(abstractCreditCardService);
    }

    @Bean
    org.springframework.boot.CommandLineRunner commandLineRunner(CheckoutService checkoutService) {
        return new Runner01(checkoutService);
    }
}

