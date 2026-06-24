# Software Design and Architecture Week08 Lab Solutions

# Convert the OrderWithDIP lab code  to use the Spring DI container

Given the interface and adapter classes created in the DIP solution above, example Runner and Configuration classes for a Spring application using Dependency Injection could look like this:

```java
@Component
class Runner03 implements org.springframework.boot.CommandLineRunner, Ordered {

    private final CourierGateway gateway;

    Runner03(CourierGateway gateway)
    {
        this.gateway = gateway;
    }

    @Override
    public void run(String... args)  {
        System.out.format("Hello from %s%n", this.getClass());

        double parcel1 = 2.5; // lbs
        double parcel2 = 10.0; // lbs

        // Use the provided CourierGateway
        OrderWithDip orderWithDip1= new OrderWithDip(parcel1, DeliveryOption.NEXT_DAY, gateway);
        orderWithDip1.ship();

        // Use the AlternativeCourier
        OrderWithDip orderWithDip2 = new OrderWithDip(parcel2, DeliveryOption.BUDGET, gateway);
        orderWithDip2.ship();
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
```
You would then need a Spring `@Configuration` class to define which implementation of the `CourierGateway` interface to use. For example, to use the DefaultCourier implementation:

```java
@Configuration
class OrderConfig {
    @Bean
    CourierGateway createCourierGateway() {
        return new DefaultCourier();
    }
}
```
or the AlternativeCourier implementation:
```java
@Configuration
class OrderConfig {
    @Bean
    CourierGateway createCourierGateway() {
        return new AlternativeCourier();
    }
}
```

# Use External Properties or Profiles to choose different implementations at runtime (Advanced)

An example is using Spring Profiles to choose between a default configuration and a production configuration.

If no profile is specified, the **default profile** is used, as shown by the log output

```Text
    : No active profile set, falling back to 1 default profile: "default"
```

Mark the original AppConfig class with the `@Profile("default")` annotation to indicate that it should be used when the default profile is active.

```java
@Configuration
@Profile("default")
class AppConfig {
}
```

Create another configuration class for the production profile, marked with the `@Profile("production")` annotation.

```java
@Configuration
@Profile("production")
class ProductionAppConfig {

}
```
In the `application.properties` file (found in the `resources` directory   you can specify the active profile by adding the following line:

```properties
spring.profiles.active=production
```
When you run the application with this configuration, Spring should:

- Display the log message indicating that the "production" profile is active. `: The following 1 profile is active: "production"`
- Use the `ProductionAppConfig` class to configure the beans instead of the default `AppConfig` class.
