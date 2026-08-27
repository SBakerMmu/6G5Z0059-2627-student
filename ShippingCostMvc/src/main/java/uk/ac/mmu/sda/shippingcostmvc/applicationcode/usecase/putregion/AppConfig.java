package uk.ac.mmu.sda.shippingcostmvc.applicationcode.usecase.putregion;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration("uk.ac.mmu.sda.shippingcostmvc.applicationcode.usecase.putregion")
class AppConfig {
    @Bean("uk.ac.mmu.sda.shippingcostmvc.applicationcode.usecase.putregion.Provided")
    @Scope("prototype")
    Provided create(Required required) {
        return new UseCase(required);
    }
}
