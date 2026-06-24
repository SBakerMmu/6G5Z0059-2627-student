# Software Design and Architecture - Week 07 Lab Solutions


## Using the State Pattern to manage Authentication status.

State Machines are a design technique that models behaviour as a series of **states** and **transitions** between those states. Each state represents a stage in a lifecycle and determines how the system responds to events (also called triggers) when it is in a given state, and the transitions define how the system moves between different states based on events or conditions (guard conditions).

The problem with writing state machine code in a procedural way using switch/case or if/then statements is that there is a combinatorial explosion between the events and the states - there will be switch statement for each event, and each switch statement will need as many branches as there are states. Adding a new event requires adding a new switch statements, adding a new state requires extending every switch statement by adding a new branch. The same issues apply if you replace switch with if. You should recognise that long conditional statements are not a good solution for extensibility or maintainability

A better way of coding this is to use the **State Pattern**. The State pattern creates a specific class for each State. Each State class can then be varied independently to handle the requirements of each state.

First define an interface called `State` that will be implemented by all the State classes and an interface called `Context` to be implemented by the State Machine.

```java
interface State {
    void login(Context context);
    void logout(Context context);
}
```

```java
interface Context {
    void setState(State state);
}
```
Create two class that represent each state in the state machine

```java
class LoggedInState  implements State {

    @Override
    public void login(Context context) {
        //already logged in
    }

    @Override
    public void logout(Context context) {
        context.setState(new LoggedOutState());
    }

    @Override
    public String toString() {
        return "Logged In";
    }
}

class LoggedOutState implements State {
    @Override
    public void login(Context context) {
        context.setState(new LoggedInState());
    }

    @Override
    public void logout(Context context) {
        //already logged out
    }

    @Override
    public String toString() {
        return "Logged Out";
    }
}
```

All the logic associated within the state now lives in a concrete state class.

The context provides the public API to the state machine - its methods represents the possible triggers - which in this case is login and logouts.

The context class also has a variable to hold the current state (called `status`) and implements the `setState` method which is called by the state classes to set the current state of the state machine.

```java
class AuthenticationContext implements Context{

    private State status = new LoggedOutState();

    public void login()
    {
        status.login(this);
    }

    public void logout()
    {
        status.logout(this);
    }

    @Override
    public void setState(State state) {
        status = state;
    }

    public String getStatus() {
        return status.toString();
    }
}
```

## Auto Logout
The auto logout version has a down count (you could implement it as an incrementing counter) in the LoggedIn state.

The State interface needs another trigger for the tick.

```java
interface State {
    void login(Context context);
    void logout(Context context);
    void tick(Context context);
}
```

The LoggedIn state contains the counter. Each tick event is counted, and when the count is done, we switch to the LoggedOut state.

```java
class LoggedInState  implements State {

    private static final int MAX_TICKS = 3;
    private int count;

    public LoggedInState() {
        this.count = MAX_TICKS;
    }

    @Override
    public void login(Context context) {
        //already logged in
    }

    @Override
    public void logout(Context context) {
        context.setState(new LoggedOutState());
    }

    @Override
    public void tick(Context context) {
        if(--count == 0)
        {
            logout(context);
        }
    }

    @Override
    public String toString() {
        return "Logged In";
    }
}

class LoggedOutState implements State {
    @Override
    public void login(Context context) {
        context.setState(new LoggedInState());
    }

    @Override
    public void logout(Context context) {
        //already logged out
    }

    @Override
    public void tick(Context context) {

    }

    @Override
    public String toString() {
        return "Logged Out";
    }
}

class AuthenticationContext implements Context {

    private State status = new LoggedOutState();

    public void login()
    {
        status.login(this);
    }

    public void logout()
    {
        status.logout(this);
    }

    public void tick()
    {
        status.tick(this);
    }

    @Override
    public void setState(State state) {
        status = state;
    }

    public String getStatus() {
        return status.toString();
    }
}
```
## Auto Logout with Reset

The final example resets the count everytime the `login()` method is called on the AuthenticationContext. Calling `login()` causes a new instance of the `LoggedInState` class, initialised with MAX_TICKS to be assigned to the state variable.

You will note that in this implementation, the state classes take and hold a reference to the Context in their constructors, rather than having the Context passed in through each method. Either way is valid, but in this implementation the state classes can call the `setState` method on the context class without needing to have the context passed in as a parameter to each method and is arguably tidier.

```java
interface State {
    void login();
    void logout();
    void tick();
}

class LoggedInState implements State {

    private static final int MAX_TICKS = 3;
    private final Context context;
    private int count = MAX_TICKS;

    public LoggedInState(Context context) {
        this.context = context;
    }

    @Override
    public void login() {
        //already logged in, set the state to a new instance of the LoggedInState which be initialized with count set to MAX_TICKS
        context.setState(new LoggedInState(context));
    }

    @Override
    public void logout() {

        context.setState( new LoggedOutState(context));
    }

    @Override
    public void tick() {
        if (--count == 0) {
            logout();
        }
    }

    @Override
    public String toString() {
        return "Logged In";
    }
}

class LoggedOutState implements State {
    private final Context context;

    public LoggedOutState(Context context) {
        this.context = context;
    }

    @Override
    public void login() {
        context.setState(new LoggedInState(context));
    }

    @Override
    public void logout() {
        //already logged out
    }

    @Override
    public void tick() {

    }

    @Override
    public String toString() {
        return "Logged Out";
    }
}

class AuthenticationContext implements Context {

    private State status = new LoggedOutState(this);

    public void tick() {
        this.status.tick();
    }

    public void login() {
        this.status.login();
    }

    public void logout() {
        this.status.logout();
    }

    @Override
    public String toString() {
        return getStatus();
    }

    String getStatus() {
        return this.status.toString();
    }

    @Override
    public void setState(State state) {
        this.status = state;
    }
}

```
## Use Nested Classes to encapsulate the State machine within the Context
Java allows you to define classes and interfaces within another class. Such a class is called a **nested** class.

```java
class OuterClass {
    ...
    class NestedClass {
        ...
    }
}
```
There are two types of nested classes : non-static and static. Non-static nested classes are called **inner classes** and inner classes can access the private members of their enclosing classes.

Nested classes that are declared static are called **static nested classes**. Static nested classes cannot access private members of their enclosing classes.

This code fragment shows how to declare an inner and a static nested class within an outer class.

```java
class OuterClass {
    ...
    class InnerClass {
        ...
    }
    static class StaticNestedClass {
        ...
    }
}
```
You can also declare interfaces within classes. These are called **nested interfaces**. Nested interfaces are implicitly static.

This implementation of the state pattern uses private inner classes and private nested interfaces to keep the state pattern implementation entirely private to the `AuthenticationContext`. The use of non-static inner classes means that the State implementations can set variables with the context class directly. On the other hand, I cannot reuse my state classes in another context - I can only reuse the state machine using the `AuthenticationContext` class.

### Using Nesting as part of class design
Using nesting is a way of grouping classes that are intended to only be used in one place, in this example grouping the state machine implementation classes within a context class.

By making all the nested interfaces and nested classes private, the implementation is completely hidden, and we no longer need the public 'setState' method on the context class which increases encapsulation.

The Autoreset example implemented using Nested classes

```java
class AuthenticationContext {

    private State status = new LoggedOutState();

    public void tick() {
        this.status.tick();
    }

    public void login() {
        this.status.login();
    }

    public void logout() {
        this.status.logout();
    }

    @Override
    public String toString() {
        return getStatus();
    }

    String getStatus() {
        return this.status.toString();
    }

    //nested private interface declarations and nested private concrete state classes
    //As these are nested, they have access to the context's private members, so we can directly set the status field without needing to pass the context as a parameter to the state methods

    private interface State {
        void login();

        void logout();

        void tick();
    }

    private class LoggedInState implements State {

        private static final int MAX_TICKS = 3;
        private int count;

        public LoggedInState() {
            this.count = MAX_TICKS;
        }

        @Override
        public void login() {
            //already logged in
            status = new LoggedInState();
        }

        @Override
        public void logout() {
            status = new LoggedOutState();
        }

        @Override
        public void tick() {
            if (--count == 0) {
                logout();
            }
        }

        @Override
        public String toString() {
            return "Logged In";
        }
    }

    private class LoggedOutState implements State {
        @Override
        public void login() {

            status = new LoggedInState();
        }

        @Override
        public void logout() {
            //already logged out
        }

        @Override
        public void tick() {

        }

        @Override
        public String toString() {
            return "Logged Out";
        }
    }
}
```
Use of nested classes is a way of grouping classes that are only used in one place, and controlling their visibility. In this example the state pattern implementation is completely hidden within the context class.

The downside of using nested classes is that the concrete state classes cannot be tested independently or reused in another context because they are private to the context class.

# Apply the Dependency Inversion Principle (DIP)

We have 2 different CourierGateway implementations that we want to use to ship parcels - a Default and an Alternative.

```java
public class DefaultCourierGateway{
    public void send(double weightInGrams, int deliveryDays) {
        // Implementation for shipping a parcel via the default courier
        System.out.format("Shipping parcel %fg via Default Courier in %d days%n", weightInGrams, deliveryDays);
    }
}
```

```java
public enum AlternativeCourierOption {
    PRIORITY_OVERNIGHT, STANDARD, ECONOMY
}

public class AlternativeCourierGateway {
    public void shipPackage(double weightInKg, AlternativeCourierOption option) {
        // Implementation for shipping a parcel via AlternativeCourier
        System.out.format("Shipping parcel %fkg to via Alternative Courier %s option%n", weightInKg, option.name());
    }
}
```

Examine the `OrderWithoutDip` class and design a Java interface that represents a contract for shipping a parcel from the point of view of the Order class. This interface should define a method for shipping a parcel, with parameters that are independent of the specific Gateway implementations.

The interface should look something like this:

```java
public enum DeliveryOption {
    NEXT_DAY, //Deliver the next day
    STANDARD, //2-3 days
    BUDGET, //5 days or more
}

public interface CourierGateway {
    void ship(double weightInLbs, DeliveryOption option);
}
```
This expresses the requirement that the Order class has for shipping a parcel, without reference to any specific Gateway implementation.
Note the use of the `DeliveryOption` enum makes that enum part of the interface.

You then create two **Adapter** classes, one for each Gateway implementation. Each class will implement the interface you defined and will internally use the respective Gateway implementation to perform the shipping operation. The Adapter adapts the Gateway to your CourierGateway interface.

```java
public class DefaultCourierGatewayAdapter implements CourierGateway {

    private final DefaultCourierGateway gateway = new DefaultCourierGateway();

    public DefaultCourierGatewayAdapter() {
    }

    @Override
    public void ship(double weightInLbs, DeliveryOption option) {
        //  Default Courier uses grams as weight unit and days as delivery time unit
        double weightInGrams = convertLbsToGrams(weightInLbs);
        int deliveryTimeInDays = switch (option) {
            case NEXT_DAY -> 1;
            case STANDARD -> 3;
            case BUDGET -> 5;
        };
        gateway.send(weightInGrams, deliveryTimeInDays);
    }

    private static double convertLbsToGrams(double lbs) {
        return lbs * 0.453592d * 1000d;
    }
}

```

```java
public class AlternativeCourierGatewayAdapter implements CourierGateway {

    private final AlternativeCourierGateway gateway = new AlternativeCourierGateway();

    public AlternativeCourierGatewayAdapter() {
    }

    @Override
    public void ship(double weightInLbs, DeliveryOption option) {
        double weightInKgs = convertLbsToKg(weightInLbs);
        AlternativeCourierOption service = switch (option) {
            case NEXT_DAY -> AlternativeCourierOption.PRIORITY_OVERNIGHT;
            case STANDARD -> AlternativeCourierOption.STANDARD;
            case BUDGET -> AlternativeCourierOption.ECONOMY;
        };
        gateway.shipPackage(weightInKgs, service);
    }

    private static double convertLbsToKg(double lbs) {
        return lbs * 0.453592d;
    }
}
```
Some example code that uses the `OrderWithDip` class:

```java
double parcel1 = 2.5; // lbs
double parcel2 = 10.0; // lbs
// Use the DefaultCourier
OrderWithDip orderWithDip1= new OrderWithDip(parcel1, DeliveryOption.NEXT_DAY, new DefaultCourierGatewayAdapter());
orderWithDip1.ship();

// Use the AlternativeCourier
OrderWithDip orderWithDip2 = new OrderWithDip(parcel2, DeliveryOption.NEXT_DAY, new AlternativeCourierGatewayAdapter());
orderWithDip2.ship();
```

```plain text
Shipping parcel 1133.980000g via Default Courier in 1 days
Shipping parcel 4.535920kg to via Alternative Courier PRIORITY_OVERNIGHT option
```

Note how the decision about which Gateway implementation to use is now made outside the `OrderWithDip` class.
It becomes the responsibility of some part of the calling code to decide which implementation to use and pass that into the `OrderWithDip` class.

### DRY Principle Refactor
You may have noticed that both Adapter classes have a method to convert from lbs. You should refactor this into a utility class as otherwise the knowledge of how to convert (the conversion constant, the precision and rounding) is in two places which is a DRY principle violation.

It is OK for the adapter classes to depend directly on a utility class as that class is unlikely to change often (the conversion factor from lbs to kgs is a standard). However, if you think that elements of the conversion algorithm were going to change (the precision and rounding for example) then you could use the Strategy pattern to encapsulate the different conversion algorithm and have the adapter classes depend on an abstraction for the conversion strategy.


# Compare the application of the Dependency Inversion Principle (DIP) to the Strategy pattern. (Advanced)

The application of the Dependency Inversion Principle (DIP) requires us to **inject** dependencies into a class rather than having the class create its own dependencies. We use the **Dependency Injection** pattern allows us to implement the **Dependency Inversion Principle (DIP)**.

In our solution above the `OrderWithDip` class has a concrete implementation of the CourierGateway interface injected into it via its constructor.

The Strategy pattern defines a family of algorithms, encapsulates each one, and makes them interchangeable. The Strategy pattern lets the algorithm vary independently of clients that use it.

The Strategy pattern is often realized using the Dependency Injection pattern. In this case the client class (the context) depends on an abstraction (the strategy interface) rather than a concrete implementation of the algorithm. The specific strategy implementation is injected into the client class at runtime.

However, you can also apply the Strategy pattern without using Dependency Injection by having the client class select a strategy implementation (rather than being given it by injection). In this case the difference is where the decision is made, inside the client class or outside the client class. For example, a class could decide to choose a different implementation of a sorting algorithm based on number of objects to be sorted.

### Strategy Pattern
- Strategy pattern is used when abstracting away algorithms for making calculations or decisions that would be part of the business logic of the application.
- The application of the Strategy pattern is typically local to a single context class to solve the specific problem of managing variations of a single, well-defined question.

### Dependency Inversion Principle and Dependency Injection Pattern
- Dependency Inversion is generally  referred to as the process abstracting away dependencies on infrastructure type code (code that deals with databases, files and external services).
- It is an architectural principle applied to an entire application to govern how components are created, wired together, and managed, using the Dependency Injection pattern.
- Dependencies are injected once at construction and do not change through the duration of a specific application instance. Changing the concrete dependencies would typically require some code or configuration change and starting a new runtime instance of the application.
- The mechanism for dependency injection is often (but not always) a **Dependency Injection (DI) container** (sometimes called an **Inversion of Control** container ). Java examples are Spring or Google Guice.
- This lab uses **manual dependency injection** where the dependencies are created and passed into the class by hand in the code. This is the simplest form of dependency injection and is often used in small applications or in examples to illustrate the principle. It is also a good way to understand how dependency injection works before using a DI container.
- We will work with an industry-standard DI Container in the next lab.
