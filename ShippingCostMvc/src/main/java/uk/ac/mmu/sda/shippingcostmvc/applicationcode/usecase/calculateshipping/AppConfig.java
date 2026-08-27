package uk.ac.mmu.sda.shippingcostmvc.applicationcode.usecase.calculateshipping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration("uk.ac.mmu.sda.shippingcostmvc.applicationcode.usecase.calculateshipping")
class AppConfig {
    @Bean("uk.ac.mmu.sda.shippingcostmvc.applicationcode.usecase.calculateshipping.Provided")
    @Scope("prototype")
    Provided create(Required required) {
        return new UseCase(required);
    }
}
