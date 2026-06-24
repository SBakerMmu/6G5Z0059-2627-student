# Software Design and Architecture Week08 Lab Worksheet

# Use the Spring Framework to implement Dependency Injection (DI)

In examples of Dependency Injection so far, we have manually constructed the objects and passed them into the constructors of the classes that need them. For example a `Basket` class requires a concrete implementation of the `AbstractCreditCardService` interface to be passed into its constructor.

```java
interface AbstractCreditCardService {
    void takePayment();
}
```
An implementation of the AbstractCreditCardService interface is a class called TestCreditCardService.

```java
class TestCreditCardService implements AbstractCreditCardService {
    @Override
    public void takePayment(){
        System.out.println("Test CreditCard Service taking payment...");
    }

    @Override
    public String toString(){
        return super.toString();
    }
}
```

The Basket class depends on the AbstractCreditCardService interface and requires a concrete realization of AbstractCreditCardService to be passed into its constructor.
```java
class Basket {
    private final AbstractCreditCardService abstractCreditCardService;

    Basket(AbstractCreditCardService abstractCreditCardService) {
        this.abstractCreditCardService = abstractCreditCardService;
    }

    void chargeCreditCard() {
        System.out.println("Basket charging credit card...");
        abstractCreditCardService.takePayment();
    }

    @Override
    public String toString() {
        return String.format("%s %s", super.toString(), abstractCreditCardService.toString());
    }
}
```
To make the system work, we need to manually construct a concrete instance of AbstractCreditCardService and pass it into the Basket constructor. This is called "manual dependency injection" and the code is configuring the Basket instance.

```java
public Basket createBasket() {
    AbstractCreditCardService abstractCreditCardService = new TestCreditCardService();
    return new Basket(abstractCreditCardService);
}
```

This manual configuration can become impractical in larger applications with many classes and dependencies. The Spring Framework provides a way to automate this process using Dependency Injection (DI) containers.

This is why DI Containers are also referred to as Inversion of Control or IoC Containers. Inversion of Control refers to the fact that control of creating and managing object dependencies moves from the application code to the container itself, inverting control from the application code to something else.

This lab will set up a basic Spring Boot application that uses Dependency Injection to manage object dependencies to practice how to configure and use a DI container.

## Set up a Spring Boot Application

We are going to create a simple Spring Boot application using the **Spring Initializr** web tool.

Go to the website https://start.spring.io create a starter project using the following settings

- **Project**: Maven
- **Language**: Java
- **Spring Boot**: Choose latest stable (at time of writing this was 4.0.3)
- **Project Metadata**
  - Group: uk.ac.mmu
  - Artifact: lab08
  - Name: lab08
  - Description: Week 08 Lab - Dependency Injection with Spring
  - Package name: uk.ac.mmu.lab08
  - Packaging: Jar
  - Configuration: Properties
  - Java: 25 (or the latest version available)
- **Dependencies**: No dependencies are required for this lab.

Click the "Generate" button to download a zip file containing the starter project. Unzip the file and open the project in IntelliJ (be careful to open the Project at the right level - the project should be opened from the directory containing the pom.xml file).

> ☠ Do not attempt to put Spring Boot projects into existing IntelliJ projects. Always create a new project for Spring Boot applications. This is because Spring Boot projects have a specific structure and configuration and uses a build system called **Maven** that will conflict with existing projects.

A successful build and run should display the Spring Boot startup messages in the console (something like)

> ⚠ As a Maven project, source files are placed into different directories to the IntelliJ projects.
>
> The `main` method will be located in `src\main\java\uk\ac\mmu\lab08\Lab08Application.java`


```text

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v4.0.3)

uk.ac.mmu.lab08.Lab08Application         : Starting Lab08Application using Java 25.0.1 with PID 98980
uk.ac.mmu.lab08.Lab08Application         : No active profile set, falling back to 1 default profile: "default"
uk.ac.mmu.lab08.Lab08Application         : Started Lab08Application in 1.503 seconds (process running for 2.114)
Process finished with exit code 0
```
This banner printing indicates that the Spring Boot application has started successfully. It means that `main()` method has called `SpringApplication.run(lab08Application.class, args);`

> ⚠ The details on your banner may vary depending on the date, version of Spring Boot, project settings and other factors

```java
	public static void main(String[] args) {
		SpringApplication.run(Lab08Application.class, args);
	}

```

Now create a class inside the `uk.ac.mmu.lab08` package

```java
package uk.ac.mmu.lab08;

import org.springframework.stereotype.Component;

@Component
class Runner01 implements org.springframework.boot.CommandLineRunner, org.springframework.core.Ordered {


    Runner01() {

    }


    @Override
    public void run(String... args) {
        System.out.format("Hello from %s%n", this.getClass());
    }

    @Override
    public int getOrder() {
        return org.springframework.core.Ordered.HIGHEST_PRECEDENCE;
    }
}
```
When you run the application again, you should see the message `Hello from class uk.ac.mmu.lab08.Runner01` printed in the console output.

Create another class called Runner02 in the same package using the same code as Runner01 and set the order to `Ordered.LOWEST_PRECEDENCE`. When you run the application again, you should see the message from Runner01 printed before the message from Runner02.

Swap the Precedence values in the two Runner classes and run the application again. You should see that the order of the messages has changed.

## Explanation of how this works

1. The normal program entry point `main()` starts the Spring Boot application.
2. The `@SpringBootApplication` annotation placed on the class containing the entry point method implicitly defines a base “search package”.
3. Using this annotation will cause the Spring application to for any classes annotated with `@Component` and `@Configuration` within the search package and its sub-packages.
4. The Runner classes are annotated with `@Component`, which makes them Spring-managed components, meaning that Spring will manage the creation of instances of these classes and manage their lifecycle.
5. The Runner classes also implement the `CommandLineRunner` interface, which is a special interface that indicates to  Spring Boot that these classes should be instantiated and their run methods should be called when the application starts.
6. The Runner classes also implement the `Ordered` interface to specify the order in which they should be instantiated and run.

## Injecting Dependencies as Beans

The term **Bean** in the context of Spring Framework refers to an object that is instantiated and managed by the Spring DI container.

Objects managed as Beans can be injected into other Beans using the Spring Dependency Injection.

Copy the `AbstractCreditCard` interface , `TestCreditCardService`, and `Basket` *exactly* as described at the start of this lab inside the `uk.ac.mmu.lab08` package.

Modify the application class to include the following Bean definition methods.

```java

@SpringBootApplication
public class Lab08Application {

    public static void main(String[] args) {
        SpringApplication.run(Lab08Application.class, args);
    }

    @Bean
    AbstractCreditCardService createTest() {
        return new TestCreditCardService();
    }

    @Bean
    Basket createBasket(AbstractCreditCardService abstractCreditCardService) {
        return new Basket(abstractCreditCardService);
    }

}

```

Modify the Runner01 class to inject an instance of `AbstractCreditCardService` into its constructor and call `takePayment()` in the `run()` method.

```java

import org.springframework.stereotype.Component;

@Component
class Runner01 implements org.springframework.boot.CommandLineRunner, org.springframework.core.Ordered {

    private final AbstractCreditCardService abstractCreditCardService;

    Runner01(AbstractCreditCardService abstractCreditCardService) {
        this.abstractCreditCardService = abstractCreditCardService;
    }

    @Override
    public void run(String... args)  {
        System.out.format("Hello from %s%n", this.getClass());
        System.out.format("Depends on %s%n", abstractCreditCardService);
        abstractCreditCardService.takePayment();
    }

    @Override
    public int getOrder() {
        return org.springframework.core.Ordered.HIGHEST_PRECEDENCE;
    }
}

```
You should see the message from Runner01 printed in the console output along with the message from the TestCreditCard service implementation.

```Text
Hello from class uk.ac.mmu.lab08.Runner01
Depends on uk.ac.mmu.lab08.TestCreditCardService@492fc69e
Test CreditCard Service taking payment...
```
This shows that the Spring DI container has successfully injected an instance of TestCreditCard into the Runner01 class.

> ⚠ the output from toString() will vary each time you run the application because it includes the object's memory address.
>
> Unless you override the `toString()`, the default implementation from the Object class will be used.
>
> The toString method for class Object returns a string consisting of the name of the class of which the object is an instance, the at-sign character `@', and the unsigned hexadecimal representation of the hash code of the object.
>
>  `public String toString() { return getClass().getName() + "@" + Integer.toHexString(hashCode());`
>
> Because we have not overridden the `hashCode()` method the default implementation from the Object class is used, which typically returns a value based on the object's memory address. This is why you will see different values to the value in the lab sheet, and different values each time you run the application.
>
> For our purposes, we can the default implementation of `hashCode()` to return an instance identifier for the object, so the toString() output shows both the class name and a unique identifier for the instance.

### Explanation of how this works

1. The `@SpringBootApplication` annotation placed on the class containing the entry point method implicitly defines a base “search package”.
2. Any methods annotated with `@Bean` within a class annotated with `@SpringBootApplication` are treated as Bean definitions by the Spring container.
3. A bean of type `AbstractCreditCardService` is registered in the DI container using a `@Bean` annotated method in lab08Application that creates and returns an instance of `TestCreditCardService` to supply the interface `AbstractCreditCardService`.
4. The Runner classes are annotated with `@Component`, which makes them Spring-managed components, meaning that Spring will manage the creation of instances of these classes and manage their lifecycle.
5. Component scanning registers Runner01 (the @Component). Spring sees its constructor requires `AbstractCreditCardService`.
6. The DI container looks for a bean of type `AbstractCreditCardService` in its registry. It sees that an instance of type `AbstractCreditCardService` is created by the `createTest()` method.
7. The DI container calls the constructor of Runner01, passing in an instance of TestCreditCardService.

This example demonstrates how to choose which concrete implementation of an interface you want to use by changing the Bean definition in the configuration class.
You can easily switch to a different implementation of interface `AbstractCreditCardService` by modifying the bean method to return a different implementation without changing the code in Runner01 or any other classes that depend on interface `AbstractCreditCardService`.

You can also use Spring to supply concrete dependencies.

Modify Runner02 to inject an instance of Basket into its constructor and call `chargeCreditCard()` in the `run()` method.

```Java
@Component
class Runner02 implements org.springframework.boot.CommandLineRunner, org.springframework.core.Ordered {

    private final Basket basket;

    Runner02(Basket b) {
        this.basket = b;
    }

    @Override
    public void run(String... args)  {
        System.out.format("Hello from %s%n", this.getClass());
        System.out.format("Depends on %s%n", basket);
        basket.chargeCreditCard();
    }

    @Override
    public int getOrder() {
        return org.springframework.core.Ordered.HIGHEST_PRECEDENCE + 1 ;
    }
}
```
When you run the application again, you should see something like

```Text
Hello from class uk.ac.mmu.lab08.Runner02
Depends on uk.ac.mmu.lab08.Basket@117632cf uk.ac.mmu.lab08.TestCreditCardService@492fc69e
Basket charging credit card...
Test CreditCard Service taking payment...
```
### Explanation of how this works

1. The `@SpringBootApplication` annotation placed on the class containing the entry point method implicitly defines a base “search package”.
2. Any methods annotated with `@Bean` within a class annotated with `@SpringBootApplication` are treated as Bean definitions by the Spring container.
3. A bean of type `Basket` is registered in the DI container using an `@Bean` annotated method that returns a `Basket` instance.
4. Component scanning registers that method for providing instances of `Basket` requires `AbstractCreditCardService`.
5. The DI container looks for a bean of type `AbstractCreditCardService` in its registry. It finds the bean can be created using the `createTest()` method.
6. The DI container calls the constructor of Runner02, creating and providing an instance of `Basket`, which itself has been created with an instance of `AbstractCreditCardService`.

The container has created the entire **object graph** for us, resolving all dependencies automatically.

Using the Dependency Injection pattern on large applications with many classes and dependencies, manually constructing the object graph can become impractical. Using a DI container like Spring automates this process.

To illustrate this, try adding another class that depends on `Basket` (for example an `Order` class), create another Runner class that depends on this new class, and so on.

## Use a Configuration class

So far we have defined our Beans in the main application class. However, as the number of Beans increases, it is common practice to separate the Bean definitions into one or more dedicated configuration classes.

A configuration class is a class annotated with `@Configuration`. This annotation indicates to Spring that the class contains Bean definitions and is picked up as part of the scanning process that happens when the Spring Boot application starts.

Add a class called `AppConfig` to the `uk.ac.mmu.lab08` package with the following code.

> ☠ Do not be tempted to call your configuration class `Configuration` as this will conflict with the `@Configuration` annotation.

```java
package uk.ac.mmu.lab08;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class AppConfig {
    @Bean()
    AbstractCreditCardService createTest() {
        return new TestCreditCardService();
    }

    @Bean
    Basket createBasket(AbstractCreditCardService abstractCreditCardService) {
        return new Basket(abstractCreditCardService);
    }
}
```
Remove the (now duplicate) Bean definitions from the main application class or else Spring will complain about duplicate Bean definitions.

When you run the application again, you should see the same output as before. The DI container is still able to find and use the Bean definitions in the configuration class.

## Explore Scope and Lifecycle of Beans

If you look at the output from the application, you will see that the same instance of the AbstractCreditCardService implementation is used in both Runner01 and Runner02. This is because, by default, Spring Beans are singleton scoped. This means that only one instance of each Bean is created and shared throughout the application.

> Hello from class uk.ac.mmu.lab08.Runner01
>
> Depends on uk.ac.mmu.lab08.**TestCreditCardService@492fc69e**
>
> Hello from class uk.ac.mmu.lab08.Runner02
>
> Depends on uk.ac.mmu.lab08.Basket@117632cf **uk.ac.mmu.lab08.TestCreditCardService@492fc69e**

If you want a new instance of a Bean to be created each time it is injected, you can change the scope of the Bean that provides the AbstractCreditCardService interface to prototype by adding the `@Scope("prototype")` annotation to the Bean definition method.

```Java
    @Bean()
    @Scope("prototype")
    AbstractCreditCardService createTest() {
      return new TestCreditCardService();
    }
```

When you run the application again, you should see that different instances are used in Runner01 and Runner02 (your instance identifiers will be different).

> Hello from class uk.ac.mmu.lab08.Runner01
>
> Depends on **uk.ac.mmu.lab08.TestCreditCardService@57dc9128**
>
> Test CreditCard Service taking payment...
>
> Hello from class uk.ac.mmu.lab08.Runner02
>
> Depends on uk.ac.mmu.lab08.Basket@24528a25 **uk.ac.mmu.lab08.TestCreditCardService@17ae98d7**

Change the scope back to singleton and run the application again to see the original behaviour.

```Java
    @Bean()
    @Scope("singleton")
    AbstractCreditCardService createTest() {
     return new TestCreditCardService();
    }
```

### Explanation of how this works

As well as managing the creation and injection of dependencies, DI containers also manage the lifetime of the objects they create.

In the example above we used the `@Scope` annotation to specify the lifetime of the beans.

The `@Scope("singleton")` annotation means that there will be only one instance of the class created by the DI Container, and that instance will be reused whenever an instance of that class is required.

The `@Scope("prototype")` annotation means that a new instance of the class will be created each time it is requested from the DI Container.

By specifying the singleton scope the DI Container will use a form of the singleton pattern to ensure that (in this case) the same instance will always be provided, no matter how many times the type was requested from the DI Container.

This is a different way of achieving a single instance compared to the technique of using a static final instance we discussed previously in the module (Singletons).

Therefore, when you are using a DI Container like Spring, you need to consider the scope of the Beans you are defining to ensure that they have the correct lifetime for your application (this is a design decision and will be based on statelessness and immutability).

> ⚠ In Spring's DI Container, the default scope is singleton. This means that if you do not specify a scope, the DI Container will provide a singleton. We would suggest always specifying the scope explicitly to make your intention clear. Other DI Containers have different defaults.

## Choose a specific implementation of an interface

The point of the Dependency Inversion Principle is to depend on abstractions (interfaces) rather than concrete implementations and to be able to switch between different implementations at runtime.

The Dependency Injection pattern allows us to do this by putting the decision about which implementation to use outside the class that depends on the interface, in this case we are going to let the DI container make the decision.

Create another implementation of `AbstractCreditCardService` called `RealCreditCardService` in the `uk.ac.mmu.lab08` package.

```java
class RealCreditCardService implements AbstractCreditCardService {
    @Override
    public void takePayment() {
        System.out.println("Real Credit Card service charging credit card...");
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

```
Update the AppConfig class

1. Add a second Bean definition method `createReal()` for interface `AbstractCreditCardService` that returns an instance of RealCreditCardService.
2. Annotate this second Bean definition method with a specific name using the `@Bean("real")` annotation.
3. Apply the `@Primary` annotation to `createTest()` indicate which implementation should be used by default when there are multiple candidates.
4. Apply the `@Qualifier` annotation to the requirement for the `AbstractCreditCardService` interface in the `createBasket()` method to specify that the `real` Bean method should be used when creating Basket.

```java
@Configuration
class AppConfig {
    @Scope("singleton")
    @Primary
    AbstractCreditCardService createTest() {
        return new TestCreditCardService();
    }

    @Bean("real")
    @Scope("singleton")
    AbstractCreditCardService createReal() {
        return new RealCreditCardService();
    }

    @Bean
    @Scope("prototype")
    Basket createBasket(@Qualifier("real") AbstractCreditCardService abstractCreditCardService) {
        return new Basket(abstractCreditCardService);
    }
}
```

When you run the application again, you should see that Runner01 is using TestCreditCardService (from the primary Bean) and Runner02 is using RealCreditCardService (from the qualified Bean).

> Hello from class uk.ac.mmu.lab08.Runner01
>
> Depends on uk.ac.mmu.lab08.TestCreditCardService@d71adc2
>
> Test CreditCard Service taking payment...
>
> Hello from class uk.ac.mmu.lab08.Runner02
>
> Depends on uk.ac.mmu.lab08.Basket@3add81c4 uk.ac.mmu.lab08.RealCreditCardService@1a1d3c1a
>
> Basket charging credit card...
>
> Real Credit Card service charging credit card...

### Explanation of how this works
If we have multiple implementations of an interface registered as Beans in the DI Container, we need to specify which implementation should be used when injecting the dependency.

The `@Primary` annotation indicates to the DI Container that this Bean should be used by default when there are multiple candidates for injection and no bean name is specified.

The `@Qualifier("beanName")` annotation specifies the name of the Bean that should be used for injection.

## Convert the OrderWithDip Lab to use the Spring DI container

A previous lab asked you to refactor the OrderWithoutDip class into a new class called OrderWithDip making use of the Dependency Inversion Principle.

If you completed this successfully OrderWithDip should depend on an interface you created.

Create another runner that is takes a dependency on the interface you created and configure the Spring DI container to inject the appropriate courier implementation into a Runner class, ready for use by an OrderWithDip instance.

You will need to copy over the code from the previous lab into this new Spring Boot application and add additional `@Bean` configuration.

## Summary

In this lab we have set up a basic Spring Boot application that uses Dependency Injection to manage object dependencies and illustrated 3 use cases for the Spring Dependency Injection container.

1. Where a class takes its dependencies via the constructor (rather than the class taking responsibility for making its own dependencies) Spring 'wires' up the dependencies at runtime for you.
2. Spring can manage the scope and lifetime of the objects it creates and implements the Singleton pattern for you (by default).
3. Spring can manage multiple implementations of an interface and allows you to choose which implementation at runtime using configuration.

## Use External Properties or Profiles to choose different implementations at runtime (Advanced)

We can also to choose different implementations of an interface based on external configuration properties or profiles. This allows us to configure the application to use different deployments without changing the code.

Examples would be:

- switching multiple implementations between different environments such as development, testing and production, for example using a mock service in development and a real service in production
- switching between different 3rd party services such as credit card providers or shipping providers in production
- switching between different database providers such as MySQL, PostgreSQL or Oracle depending on customer requirements
- switching between different logging frameworks

Extend the lab solution to use to choose between TestCreditCard and RealCreditCard based on external configuration properties or profiles or command line arguments.

See the Spring Boot documentation for more details:
The root of the documentation
- [Overview](https://docs.spring.io/spring-boot/index.html)

Specific sections that may help you
- [Externalized Configuration](https://docs.spring.io/spring-boot/reference/features/external-config.html)
- [Profiles](https://docs.spring.io/spring-boot/reference/features/profiles.html)

> ⚠ The Spring Boot documentation seems quite dense and difficult to navigate. You may find it easier to search the web for specific examples of how to achieve what you want to do, but make sure you refer to the official documentation to verify that the examples you find are correct and up to date.

## Spring Internals (Advanced)

You can register additional components with the Spring DI container to listen to lifecycle events and customize the behaviour of the DI container.

To listen to lifecycle events add this `@Component`  (not a Bean) to your application.

```Java
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
@Component
class EventListener implements ApplicationListener<ApplicationEvent> {
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.format("%s %s%n", event.getTimestamp(),event);
    }
}
```

This will print out all the lifecycle events that occur during the startup of the Spring Boot application.

To look at the ordering of Bean creation, you can create a BeanPostProcessor `@Component`.

The use case for an implementation of the BeanPostProcessor interface to provide  methods so that you can implement some custom logic after the Spring container finishes instantiating, configuring, and initializing a bean. Here we are just going to log messages when Beans are initialized so that you can see

1. All the Beans that are created by the Spring container
2. The order in which they are created

```Java
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
class BeanProcessor implements BeanPostProcessor, Ordered {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        System.out.format("postProcessBeforeInitialization called for beanName: %s of type %s%n", beanName, bean.getClass().getSimpleName());
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.format("postProcessAfterInitialization called for beanName: %s of type %s%n", beanName, bean.getClass().getSimpleName());
        return bean;
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
```
This is an example of the Spring DI container being extended to provide custom functionality. The Container is closed for modification but open for extension (follows the Open/Closed Principle).


## Using Spring Shell (Advanced)

This is very optional. In our examples so far, we have used the CommandLineRunner interface to run code when the application starts and this is fine for our examples and for supporting the assessment code.

However, Spring Boot also supports a more interactive approach using Spring Shell. This allows you to create a command-line interface (CLI) for your application where you can type commands to execute different functions. If you are comfortable with Spring Boot and want to explore further, you can look into using Spring Shell to create an interactive CLI.

See [Spring Shell Documentation](https://docs.spring.io/spring-shell/reference/index.html).
