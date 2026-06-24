# Software Design and Architecture Week10 Lab Solutions

# Use Case implementation

Each use case implementation is provided in its own package nested under the `applicationcode` package. Each package contains the necessary classes to implement one use case and has public `provided` and `required` interfaces.


## Database Initialization Use Case

One of the changes we need to make to the original design is to move the database initialization into one of the driving adapters.

The database initialization code uses the `putregion` use case to populate the database with region data.

In the package `uk.ac.mmu.lab10.applicationcode.usecase.putregion` we define the public `Provided` and `Required` interfaces

```java
package uk.ac.mmu.lab10.applicationcode.usecase.putregion;

public interface Provided {
    void put(Set<Region> regions);
}

public interface Required {
    void put(Set<Region> regions);
}

public class Region {
    private final String code;
    private final String name;
    private final double minCharge;
    private final double costPerKg;
    private final Set<Country> countries = new HashSet<>();

    public Region(String code, String name, double minCharge, double costPerKg) {
        //validation code here
        this.code = code;
        this.name = name;
        this.minCharge = minCharge;
        this.costPerKg = costPerKg;
    }
    //rest of implementation here
}
```

The use case implementation is trivial as it just passes the data to the `Required` interface
```java
class UseCase implements Provided {
private final Required required;

    UseCase(Required required) {
        this.required = required;
    }

    @Override
    public void put(Set<Region> regions) {
        required.put(regions);
    }
}
```
In the package uk.ac.mmu.lab10.infrastructure.driven we implement the `Required` interface to connect the use case to the data source (in this case a simple in-memory database):

```java
package uk.ac.mmu.lab10.infrastructure.driven;
import uk.ac.mmu.lab10.applicationcode.usecase.putregion.Country;
import uk.ac.mmu.lab10.applicationcode.usecase.putregion.Region;
import uk.ac.mmu.lab10.applicationcode.usecase.putregion.Required;

import java.util.Set;

public class PutRegionDatabaseAdapter implements Required
{

    private final ShippingCostDatabase database;

    public PutRegionDatabaseAdapter(ShippingCostDatabase database) {
        this.database = database;
    }

    @Override
    public void put(Set<Region> regions) {
        for (Region region : regions) {
            put(region);
        }
    }
    private void put(Region region) {
        database.regionMap.put(region.getCode(), region.getName());
        database.costPerKgMap.put(region.getCode(), region.getCostPerKg());
        database.minChargeMap.put(region.getCode(), region.getMinCharge());

        for (Country country : region.getCountries()) {
            put(region, country);
        }
    }

    private void put(Region region, Country country) {
        database.countryRegionMap.put(country.getCode(), region.getCode());
        database.countryMap.put(country.getCode(), country.getName());
    }
}
```
With the use case and adapter implemented we can now create a driving adapter in the `uk.ac.mmu.lab10.infrastructure.driving` package to collect some data pass to the Put Region use case to initialize the database when the application starts.

This is done by creating a Spring `@Component` that implements the Spring Boot CommandLineRunner and Ordering interfaces and setting the order to the highest precedence so it runs before any other runners.

```java
package uk.ac.mmu.lab10.infrastructure.driving;

import org.springframework.stereotype.Component;
import uk.ac.mmu.lab10.applicationcode.usecase.putregion.Country;
import uk.ac.mmu.lab10.applicationcode.usecase.putregion.Region;
import uk.ac.mmu.lab10.applicationcode.usecase.putregion.Provided;

import java.util.List;
import java.util.Set;

@Component
class DatabaseInitializer implements org.springframework.boot.CommandLineRunner, org.springframework.core.Ordered {

    private final Provided provided;

    DatabaseInitializer(Provided provided) {
        this.provided = provided;
    }

    @Override
    public void run(String... args) {

        System.out.format("%s initializing database%n", this.getClass());

        Region uk = new Region("UK", "United Kingdom", 0.0, 0.0);
        uk.addCountry(new Country("GB", "United Kingdom"));

        Region eur = new Region("EUR", "Europe", 0.0, 1.25);
        eur.addCountries(List.of(
                new Country("AL", "Albania"),
                new Country("AD", "Andorra"),
                new Country("AT", "Austria"),
                new Country("BY", "Belarus"),
                new Country("BE", "Belgium"),
                new Country("BA", "Bosnia and Herzegovina"),
                new Country("BG", "Bulgaria"),
                new Country("HR", "Croatia"),
                new Country("CY", "Cyprus"),
                new Country("CZ", "Czech Republic"),
                new Country("DK", "Denmark"),
                new Country("EE", "Estonia"),
                new Country("FI", "Finland"),
                new Country("FR", "France"),
                new Country("DE", "Germany"),
                new Country("GR", "Greece"),
                new Country("HU", "Hungary"),
                new Country("IS", "Iceland"),
                new Country("IE", "Ireland"),
                new Country("IT", "Italy"),
                new Country("LV", "Latvia"),
                new Country("LI", "Liechtenstein"),
                new Country("LT", "Lithuania"),
                new Country("LU", "Luxembourg"),
                new Country("MT", "Malta"),
                new Country("MD", "Moldova"),
                new Country("MC", "Monaco"),
                new Country("ME", "Montenegro"),
                new Country("NL", "Netherlands"),
                new Country("MK", "North Macedonia"),
                new Country("NO", "Norway"),
                new Country("PL", "Poland"),
                new Country("PT", "Portugal"),
                new Country("RO", "Romania"),
                new Country("RU", "Russia"),
                new Country("SM", "San Marino"),
                new Country("RS", "Serbia"),
                new Country("SK", "Slovakia"),
                new Country("SI", "Slovenia"),
                new Country("ES", "Spain"),
                new Country("SE", "Sweden"),
                new Country("CH", "Switzerland"),
                new Country("UA", "Ukraine"),
                new Country("VA", "Vatican City")
        ));

        Region row = new Region("ROW", "Rest of World", 10.0, 5.5);
        row.addCountries(List.of(
                new Country("US", "United States"),
                new Country("CA", "Canada"),
                new Country("AU", "Australia"),
                new Country("NZ", "New Zealand"),
                new Country("CN", "China"),
                new Country("JP", "Japan"),
                new Country("IN", "India"),
                new Country("BR", "Brazil"),
                new Country("ZA", "South Africa"),
                new Country("MX", "Mexico")
        ));

        //execute the use case
        provided.put(Set.of(uk, eur, row));

    }

    @Override
    public int getOrder() {
        return org.springframework.core.Ordered.HIGHEST_PRECEDENCE;
    }
}
```
> ⚠ Note that the value of `HIGHEST_PRECEDENCE` is actually the lowest possible integer value  -2147483648, so runners with higher order values run later.


## List available countries use case

In the package `uk.ac.mmu.lab10.applicationcode.usecase.listavailablecountries`

The public interfaces:

```java
package uk.ac.mmu.lab10.applicationcode.usecase.listavailablecountries;

public interface Provided {
    Set<String> list();
}

public interface Required {
    Set<String> getRegionCodes();

    Set<String> getCountryCodes();
}
```
The use case implementation:

```java
// UseCase.java
package uk.ac.mmu.lab10.applicationcode.usecase.listavailablecountries;

import java.util.Set;

class UseCase implements Provided {
    private final Required required;

    UseCase(Required required) {
        this.required = required;
    }

    @Override
    public Set<String> list() {
        return Set.copyOf(required.getCountryCodes());
    }
}
```

In the package `uk.ac.mmu.lab10.infrastructure.driven` we provide an implementation of the `Required` interface to connect the use case to the data source (in this case a simple in-memory database):

```java
package uk.ac.mmu.lab10.infrastructure.driven;

import uk.ac.mmu.lab10.applicationcode.usecase.listavailablecountries.Required;

public class ListAvailableCountriesDatabaseAdapter implements Required {
    private final ShippingCostDatabase database;

    ListAvailableCountriesDatabaseAdapter(ShippingCostDatabase database) {
        this.database = database;
    }

    @Override
    public Set<String> getRegionCodes() {
        return Set.copyOf(database.countryRegionMap.values());
    }

    @Override
    public Set<String> getCountryCodes() {
        return Set.copyOf(database.countryRegionMap.keySet());
    }

}
```
## Calculate Shipping use case

The same organisation is used for all use cases. For example the calculate shipping use case from the package `uk.ac.mmu.lab10.applicationcode.usecase.calculateshipping`

The public interfaces:

```java
// Provided
package uk.ac.mmu.lab10.applicationcode.usecase.calculateshipping;

public interface Provided {
    double calculate(String countryCode, double weight);
}

public interface Required {
    String getRegionCode(String countryCode);

    ShippingCost getShippingCostForRegion(String regionCode);
}

public class ShippingCost {
    private final double minCharge;
    private final double costPerKg;

    public ShippingCost(double minCharge, double costPerKg) {
        this.minCharge = minCharge;
        this.costPerKg = costPerKg;
    }

    public double getMinCharge() {
        return minCharge;
    }

    public double getCostPerKg() {
        return costPerKg;
    }
}
```
The use case implementation:

```Java
package uk.ac.mmu.lab10.applicationcode.usecase.calculateshipping;

class UseCase implements Provided {
    private final Required required;

    UseCase(Required required) {
        this.required = required;
    }

    @Override
    public double calculate(String countryCode, double weight) {
        //implementation here
    }
}
```
In the package `uk.ac.mmu.lab10.infrastructure.driven` we provide an implementation of the `Required` interface to connect the use case to the data source (in this case a simple in-memory database):

```Java
package uk.ac.mmu.lab10.infrastructure.driven;

import uk.ac.mmu.lab10.applicationcode.usecase.calculateshipping.Required;
import uk.ac.mmu.lab10.applicationcode.usecase.calculateshipping.ShippingCost;

class CalculateShippingDatabaseAdapter implements Required {
    private final ShippingCostDatabase database;

    CalculateShippingDatabaseAdapter(ShippingCostDatabase database) {
        this.database = database;
    }

    @Override
    public String getRegionCode(String countryCode) {
        return database.countryRegionMap.get(countryCode);
    }

    @Override
    public ShippingCost getShippingCostForRegion(String regionCode) {
        return new ShippingCost(database.minChargeMap.get(regionCode), database.costPerKgMap.get(regionCode));
    }

}
```
## Shipping Cost CLI Adapter

We create a driving adapter in the `uk.ac.mmu.lab10.infrastructure.driving` package that uses both the list available countries and the shipping cost calculation use cases.

This is done by creating a Spring `@Component` that implements the Spring Boot CommandLineRunner and Ordering interfaces and setting the order to run after the database initialization runner.

> ⚠ Note that use of the fully qualified names for the use case interfaces as their nonqualified names would clash otherwise.
>
> Also note that the Scanner instance is injected via the constructor so that a single instance can be shared with other runners if necessary.

```java

@Component
public class ShippingCostCliAdapter implements org.springframework.boot.CommandLineRunner, org.springframework.core.Ordered {
    private final Scanner scanner;
    private final uk.ac.mmu.lab10.applicationcode.usecase.listavailablecountries.Provided listAvailableCountries;
    private final uk.ac.mmu.lab10.applicationcode.usecase.calculateshipping.Provided calculateShipping;


    public ShippingCostCliAdapter(Scanner scanner, uk.ac.mmu.lab10.applicationcode.usecase.listavailablecountries.Provided listAvailableCountries, uk.ac.mmu.lab10.applicationcode.usecase.calculateshipping.Provided calculateShipping) {
        this.scanner = scanner;
        this.listAvailableCountries = listAvailableCountries;
        this.calculateShipping = calculateShipping;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.format("Calculate Shipping using calculate method:%n");
        Set<String> availableCountries = listAvailableCountries.list();
        System.out.format("Select a country to ship to (%s): ", availableCountries.stream().collect(Collectors.joining(",")));
        String country = scanner.next();
        System.out.print("Enter the weight of the package in kg: ");
        double weight = scanner.nextDouble();
        System.out.format("Shipping cost to %s: %f%n", country, calculateShipping.calculate(country, weight));
    }

    @Override
    public int getOrder() {
        return org.springframework.core.Ordered.HIGHEST_PRECEDENCE + 1;
    }
}
```

## Spring Configuration

The Spring Configuration class to wire up all the components together will need to use the fully qualified names for the types to avoid name clashes.

An application global @Configuration class should look something like this; using fully qualified names for all the types and giving each @Bean a unique name to avoid any name clashes.

```java
package uk.ac.mmu.lab10;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.Scanner;

@Configuration("globalAppConfig")
class AppConfig {

    @Bean("calculateShipping")
    @Scope("prototype")
    uk.ac.mmu.lab10.applicationcode.usecase.calculateshipping.Provided createCalculateShipping(uk.ac.mmu.lab10.applicationcode.usecase.calculateshipping.Required required) {
        return new uk.ac.mmu.lab10.applicationcode.usecase.calculateshipping.UseCase(required);
    }

    @Bean("listAvailableCountries")
    @Scope("prototype")
    uk.ac.mmu.lab10.applicationcode.usecase.listavailablecountries.Provided createListAvailableCountries(uk.ac.mmu.lab10.applicationcode.usecase.listavailablecountries.Required required) {
        return new uk.ac.mmu.lab10.applicationcode.usecase.listavailablecountries.UseCase(required);
    }

    @Bean("putRegion")
    @Scope("prototype")
    uk.ac.mmu.lab10.applicationcode.usecase.putregion.Provided create(uk.ac.mmu.lab10.applicationcode.usecase.putregion.Required required) {
        return new uk.ac.mmu.lab10.applicationcode.usecase.putregion.UseCase(required);
    }

    @Bean("shippingCostDatabase")
    @Scope("singleton")
    uk.ac.mmu.lab10.infrastructure.driven.ShippingCostDatabase createShippingCostDatabase() {
        return new uk.ac.mmu.lab10.infrastructure.driven.ShippingCostDatabase();
    }

    @Bean("putRegionDatabaseAdapter")
    @Scope("prototype")
    uk.ac.mmu.lab10.infrastructure.driven.PutRegionDatabaseAdapter createPutRegionDatabaseAdapter(uk.ac.mmu.lab10.infrastructure.driven.ShippingCostDatabase database) {
        return new uk.ac.mmu.lab10.infrastructure.driven.PutRegionDatabaseAdapter(database);
    }

    @Bean("calculateShippingDatabaseAdapter")
    @Scope("prototype")
    uk.ac.mmu.lab10.infrastructure.driven.CalculateShippingDatabaseAdapter createCalculateShippingDatabaseAdapter(uk.ac.mmu.lab10.infrastructure.driven.ShippingCostDatabase database) {
        return new uk.ac.mmu.lab10.infrastructure.driven.CalculateShippingDatabaseAdapter(database);
    }


    @Bean("listAvailableCountriesDatabaseAdapter")
    @Scope("prototype")
    uk.ac.mmu.lab10.infrastructure.driven.ListAvailableCountriesDatabaseAdapter listAvailableCountriesDatabaseAdapter(uk.ac.mmu.lab10.infrastructure.driven.ShippingCostDatabase database) {
        return new uk.ac.mmu.lab10.infrastructure.driven.ListAvailableCountriesDatabaseAdapter(database);
    }

    @Bean("scanner")
    @Scope("singleton")
    Scanner createScanner() {
        return new Scanner(System.in);
    }
}
```

# Use package-specific configuration classes

Managing all the beans in a single configuration class can become unwieldy as the application grows, and we have to use fully qualified names to avoid name clashes.

A better approach is to create package-specific configuration classes that manage the beans for each package, for example the configuration class for package `uk.ac.mmu.lab10.applicationcode.usecase.calculateshipping`

```java
package uk.ac.mmu.lab10.applicationcode.usecase.calculateshipping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration("calculateShippingConfiguration")
class AppConfig {
    @Bean("calculateShipping")
    @Scope("prototype")
    Provided create(Required required) {
        return new UseCase(required);
    }
}
```
### How this works
The application is annotated with `@SpringBootApplication` which is a *meta-annotation* that includes `@Configuration`, `@EnableAutoConfiguration` and `@ComponentScan`.

The `@ComponentScan` annotation tells Spring to scan the package of the class (and all sub-packages) for Spring components (classes annotated with `@Component`, `@Service`, `@Repository`, `@Controller`, etc.) and register them in the application context.

When we start a Spring application we provide a class to the `SpringApplication.run()` method. Passing `Lab1001Application.class` to `SpringApplication.run()` tells Spring Boot where to start component scanning and configuration. It uses this class's package as the base package using recursive descent to find `@Component`, `@Configuration` and other Spring-managed beans.

```Java
@SpringBootApplication
public class Lab1001Application {

	public static void main(String[] args) {
		SpringApplication.run(Lab1001Application.class, args);
	}

}
```
This is how the many configuration classes in the sub-packages of `uk.ac.mmu.lab10` are discovered and combined.

If we create use component classes of the same name, or multiple bean methods with the same name, Spring will not know which one is which, so we need to disambiguate them using a textual name.

We provide unique names for the configuration classes and bean methods by providing a name as string to the `@Configuration` and `@Bean` annotations respectively.

Once you have package specific configuration classes like this, you can remove all the public constructors from your classes (the interface will remain public) because the configuration class will be in the same package and can therefore access package-private constructors. Spring Boot will discover the configuration class automatically via component scanning and read them using reflection, so there is no need for public constructors on the `@Configuration` classes either.

## Is having public classes or interfaces with the same name in different packages a good idea?

The `ShippingCostCleanArchitecture` example code contains multiple classes and interfaces with the same name in different packages, for example there are multiple interfaces named `Provided` and `Required`in different use case packages.

There are pros and cons of putting classes or interfaces with the same name into different packages and then having to use fully qualified names to disambiguate them as opposed to giving each class a unique name regardless of the package it is in.

If you use common names for each class or interface, Java `import` statements can be used to avoid having to use fully qualified names in most places, which can improve readability. However, there will be places in the code where fully qualified names are required and that makes things harder to read.

On the other hand if you try to create unique names for each class or interface, you are probably going to end up including an informal namespace name in the class name and can end up with very long names that are hard to read and understand.

There is no right answer to this, it depends on the project and the team working on it.

A small project can use unique names for each class or interface without too much trouble, but a large project with many similar classes and interfaces can benefit from using common names in different packages to reduce name length and improve readability.

The key point is to be consistent throughout the codebase and to choose an approach that works well for the project.

## Should my interfaces always contain the words "Provided" and "Required"?

No, this is a teaching example and the use of the words "Provided" and "Required" in the interface names is to help illustrate the role of each interface.

In a real-world project you would probably give the interfaces descriptive names based on their use case. For example, instead of `uk.ac.mmu.lab10.applicationcode.usecase.calculateshipping.Provided` you might call the interface `uk.ac.mmu.lab10.applicationcode.usecase.calculateshipping.ShippingCostCalculation` or similar.

Coming up with good intention revealing names is hard, there is an old joke in software development that there are only two hard problems in software engineering: naming things, cache invalidation and off-by-one errors. Fortunately modern renaming tools in IDEs make it easier to rename which allows us to iterate on the name as our software evolves.

## Are package-specific Spring configuration classes a good idea?

Java access control at the package level is a technique for enforcing encapsulation of details within packages. The package only exposes its public API (it's provided and required interfaces and any classes used by the interfaces) and keeps all implementation details hidden within the package, including which concrete classes are used to implement the interfaces.

Using package-specific Spring configuration classes allows us to keep the constructors of the concrete classes package-private and keeps the configuration class close to the code it is configuring.

The issue with this approach with this is that it leaks an infrastructure technology (Spring annotations) into the application code package which is something we didn't want to do. The application code is now coupled to Spring Framework at compile time.

This may or may not be acceptable depending on the project - really we are trying to separate the concerns between application code and infrastructure code and the fact that we have a Spring configuration class inside the package is OK, as its only responsibility is to provide configuration instructions to the Spring container. The key point is that Spring annotations are not being applied directly to any other classes inside the application code package.

An alternative approach would be to keep all the configuration classes as part of the software product and hide the construction of the actual concrete class behind a factory method or an abstract factory pattern. This would keep the application code free of any Spring Framework dependencies, but would add more complexity to the code.


# Create a version of the Calculate Shipping Use Case using the Request-Response Model pattern

Assuming the package name is `uk.ac.mmu.lab10.applicationcode.usecase.calculateshippingrequestresponse` the provided interface would look like this:
```Java
public interface Provided {
    Response handle(Request request);
}
```
The request class becomes responsible for validating the input parameters:
```Java
public class Request {
    private final String countryCode;
    private final double weight;

    public Request(String countryCode, double weight) {

        if (countryCode == null || countryCode.isBlank()) {
            throw new IllegalArgumentException("countryCode must not be null or blank");
        }
        if (weight <= 0) {
            throw new IllegalArgumentException("weight must be greater than 0");
        }
        this.countryCode = countryCode;
        this.weight = weight;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CalculateShippingRequest.class.getSimpleName() + "[", "]")
                .add("countryCode='" + countryCode + "'")
                .add("weight=" + weight)
                .toString();
    }

}
```

The response class encapsulates the output parameters:
```Java
public class Response {

    private final String countryCode;
    private final double weight;
    private final String regionCode;
    private final double cost;


    //Package private  constructor that uses the request object
    Response(CalculateShippingRequest request, String regionCode, double cost) {
        this(request.getCountryCode(), request.getWeight(), regionCode, cost);
    }

    public Response(String countryCode, double weight, String regionCode, double cost) {
        this.countryCode = countryCode;
        this.weight = weight;
        this.regionCode = regionCode;
        this.cost = cost;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public double getWeight() {
        return weight;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CalculateShippingResponse.class.getSimpleName() + "[", "]")
                .add("countryCode='" + countryCode + "'")
                .add("weight=" + weight)
                .add("regionCode='" + regionCode + "'")
                .add("cost=" + cost)
                .toString();
    }
}
```
The use case implementation is now simplified because the request object handles validation and the use case can assume that the input parameters are valid.
```Java
public class UseCase implements Provided {
    private final Required required;

    public UseCase(Required required) {
        this.required = required;
    }

    @Override
    public Response handle(Request request) {

        String regionCode = required.getRegionCode(request.countryCode());

        if (regionCode == null || regionCode.isEmpty()) {
            throw new IllegalArgumentException("No Region code found for country: " + request.countryCode());
        }

        ShippingCost shippingCost = required.getShippingCostForRegion(regionCode);

        if (shippingCost == null) {
            throw new IllegalArgumentException("No shipping cost found for region: " + regionCode);
        }

        Region region = Region.valueOf(regionCode);

        ShippingRegion shippingRegion = ShippingRegionFactory.create(region, shippingCost.getMinCharge(), shippingCost.getCostPerKg());

        double cost = shippingRegion.calculate(request.weight());

        return new Response(
                request,
                regionCode,
                cost
        );
    }
}
```

The CLI adapter is another implementation of `org.springframework.boot.CommandLineRunner` and  `org.springframework.core.Ordered`

```Java

@Component
public class ShippingCostRequestResponseCliAdapter implements org.springframework.boot.CommandLineRunner, org.springframework.core.Ordered {

    private final Scanner scanner;
    private final uk.ac.mmu.lab10.applicationcode.usecase.listavailablecountries.Provided listAvailableCountries;
    private final uk.ac.mmu.lab10.applicationcode.usecase.calculateshippingrequestresponse.Provided calculateShipping;

    //Here we need to fully qualify the usecase interfaces to disambiguate the Provided interfaces
    public ShippingCostRequestResponseCliAdapter(Scanner scanner, uk.ac.mmu.lab10.applicationcode.usecase.listavailablecountries.Provided listAvailableCountries, uk.ac.mmu.lab10.applicationcode.usecase.calculateshippingrequestresponse.Provided calculateShipping) {
        this.scanner = scanner;
        this.listAvailableCountries = listAvailableCountries;
        this.calculateShipping = calculateShipping;
    }

    //implementation of Run method


    @Override
    public int getOrder() {
        return org.springframework.core.Ordered.HIGHEST_PRECEDENCE + 2;
    }

}
```

# What are the advantages and disadvantages of the Request-Response Model pattern compared to using multiple parameters on a Use Case interfaces ?

The Request-Response Model pattern has some advantages:

- Encapsulates input parameters into a single object, making it easier to manage and extend.
- Parameter (not business) validation logic is moved out of the use case implementation into the request object
- Response objects can have multiple output parameters.
- Implementing a toString() method on request and response objects can make logging and debugging easier (although be careful about including sensitive data in the toString, either omit or use redaction).
- Method signatures can be standardised (for example `handle(RequestType request): ResponseType`) using the type of the request objects to disambiguate different use cases.
- There is an argument that the request and response pattern more closely resembles how HTTP interfaces work, a limited number of verbs with different request and response bodies. This makes the request and response pattern more suitable for use cases that will be exposed via HTTP APIs.
- You can write mapping classes to convert between different representations of the request and response objects (for example mapping from JSON to request object and from response object to JSON).

Some disadvantages:

- More "boilerplate" (extra classes for many small use cases increases code volume) and more pressure on the GC to manage more short-lived objects.
- Quite verbose for trivial operations, for example wrapping single-primitive inputs/outputs the Request-Response pattern.
- Risk of reusing request/response objects across different use cases where they don't really belong (although the package structure used here helps to mitigate this by making the reuse more obvious).

On balance, we prefer using the Request-Response Model pattern with the separated use cases present in this version of Clean Architecture, but you could argue that for very simple use cases with just one or two primitive input/output parameters the normal method call pattern is simpler and more straightforward.

### Use of Java `record` for Request and Response classes

We have implemented our Request and Response classes as normal Java classes. An alternative approach would be to use Java `record` types. The main usage of record types is as an immutable data carrier between different parts of a program, and as such is well suited to implementing Request and Response objects.

- A `record` type defines a simple aggregate of values.
- The Java compiler automatically creates a class with `private final` fields for each component of the record, along with a public constructor, accessor methods, and implementations of `equals()`, `hashCode()`, and `toString()` methods.

You can validate values at the time you create a record by defining a **compact constructor** which is form of constructor declaration only available in a record declaration.

After the last statement in compact constructor, all component fields of the record class are implicitly initialized to the values of the corresponding formal parameters.

For example, the `Request` class could be implemented as a record like this:

```java
public record Request(String countryCode, double weight) {
    public Request {
        if (countryCode == null || countryCode.isBlank()) {
            throw new IllegalArgumentException("countryCode must not be null or blank");
        }
        if (weight <= 0) {
            throw new IllegalArgumentException("weight must be greater than 0");
        }
    }
}
```
Using records reduces boilerplate code over using normal classes reducing the overhead of using the request-response pattern.


# Wrap a logging Decorator around the Request-Response version of the shipping cost calculation Use Case

In this exercise we asked for a simple logging Decorator to be created that wraps around the Request-Response version of the shipping cost calculation use case. Another practical example would be to create a Decorator that performs **per-use case** authorisation checks before allowing the use case to be executed.

The decorator is simple:

```java
import uk.ac.mmu.lab10.applicationcode.usecase.calculateshippingrequestresponse.Request;
import uk.ac.mmu.lab10.applicationcode.usecase.calculateshippingrequestresponse.Response;
import uk.ac.mmu.lab10.applicationcode.usecase.calculateshippingrequestresponse.Provided;

public class CalculateShippingRequestResponseUseCaseDecorator implements Provided {

    private double totalCost;
    private final Provided decoratee;

    public CalculateShippingRequestResponseUseCaseDecorator(Provided decoratee) {
        this.decoratee = decoratee;
    }


    @Override
    public Response handle(Request request) {
        System.out.format("Request%s%n", request);
        Response response = decoratee.handle(request);
        System.out.format("Response%s%n", response);
        return response;
    }
}
```

The problem lies in the Spring configuration. We need to tell Spring to use the Decorator instead of the original use case implementation.

```java
  @Bean("calculateShippingRequestResponseUseCaseDecorator")
    @Scope("prototype")
    @Primary
    Provided createCalculateShippingRequestResponseUseCaseDecorator(@Qualifier("calculateShippingRequestResponse") Provided provided) {
        return new CalculateShippingRequestResponseUseCaseDecorator(provided);
    }
```

- The `@Qualifier` annotation tells Spring which bean to inject into the decorator constructor.
- The `@Primary` annotation tells Spring to use this bean when injecting the `uk.ac.mmu.lab10.applicationcode.usecase.calculateshippingrequestresponse.Provided` type elsewhere in the application.

