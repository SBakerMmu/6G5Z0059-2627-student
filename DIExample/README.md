# DI Example


We are using a command line application to demonstrate the use of the Spring DI Container.

```Java
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
```

1. The static `main` method is the normal entry point for a Java application.
2. We create an instance of the Spring DI Container - the `AnnotationConfigApplicationContext` class using the try-with-resources pattern to ensure that the container closes gracefully.
3. Next we need to configure the Spring DI Container to tell it how create instances of classes. We do this by writing methods annotated with `@Bean` that create and return the instances we want. The `@Bean` annotation tells Spring that the method will return an instance of a class that should be managed by the Spring container. In this example we have two `@Bean` methods - one that creates and returns an instance of the `AbstractCreditCardService` interface (the concrete implementation of the abstract interface being `ConcreteCreditCardService`), and another that creates and returns an instance of the `Basket` class.
4. When it is created, the AnnotationConfigApplicationContext scans for `@Bean` methods and uses them to configure the DI Container. We have told it which class(es) to scan in by passing the `DiExampleApplication.class` to the constructor of the `AnnotationConfigApplicationContext`.
5. We ask the container to create an instance of the `Basket` class for us using `getBean()`.
6. The container uses its internal registry to find a @Bean method returning a Basket instance.
7. The container found that the `Basket` creation method required an `AbstractCreditCardService` as a parameter, so it looked for a bean of that type first, which resulted call the @Bean method that resolved (returned an instance of) the `AbstractCreditCardService` interface. The resolved interface was given to the constructor of the `Basket` class.
8. The container returned the fully constructed `Basket` instance to us.
