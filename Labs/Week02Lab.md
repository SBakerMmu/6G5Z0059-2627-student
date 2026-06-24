# Software Design and Architecture Week 02 Lab Worksheet

Week 2 labs are about some key object-oriented techniques – interface implementation and abstract classes.

If you complete the labs, use the time to work on your assignment code and get formative feedback from the tutors.

# Implement an Interface
IntelliJ has a quick and simple way of creating a new Java project that we can use for many of the labs.
Intelli-J File menu -> `New > Project…`

Provide a project name, chose a location and ensure that you have ticked the Add sample code box.

> ⚠ Some settings will be probably different on your installation, the JDK version should be the highest available on your machine

This should generate a runnable program.
```java
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
```

which when run outputs
```java
Hello, World!
Process finished with exit code 0
```
We are going to implement a simulation of a dice shaker.

![A Diceshaker](images/week02_diceshaker.png)

Add a DiceShaker interface to your project
```java
public interface DiceShaker {
  int next();
}
```

### Create an implementation that shakes a single die.
```java
public class RandomSingleDiceShaker implements DiceShaker {

  private static final Random random = new Random();

  @Override
  public int next() {
    //nextInt Returns a pseudorandom value between 0 (inclusive) and the specified value (exclusive), so we need to add one to get a range of 1 to 6.
    return random.nextInt(6) + 1;
  }
}
```
Test the implementation (note the type of the reference is the interface type, not the implementation type).
```java
public static void main(String[] args)
{
    DiceShaker shaker = new RandomSingleDiceShaker();
    System.out.format("Shake %d%n", shaker.next());
}
```

## Create an implementation of the DiceShaker interface that shakes 2 dice.

In the code example above, we are simulating a single roll of a six-sided die.
Create another class that simulates rolling two six-sided die.

> ⚠ Note this is not as simple as making the range 2-12, 2 dice have a different frequency distribution of rolls (7 being the most common).

Write some code to switch between the two implementations based on some user input into the main() method.
> ⚠ The Scanner class in the java.util is often used as a simple way of scanning user input from the keyboard

## Create an implementation of the DiceShaker interface that delivers a fixed set of shakes.

For the assignment code you will find it useful to implement a version of a DiceShaker that returns a fixed set of “shakes”.

```java
public class FixedSingleDiceShaker implements DiceShaker {

  private final int[] shakes = new int[]{
      1, 2, 3, 4, 5, 6
  };

  private int index;

  @Override
  public int next() {
    return shakes[index++];
  }
}
```
There is a design problem with this implementation, because unlike using a random number generator which has an infinite sequence, our implementation has a limited sequence of values.

We can solve the design problem by adding another method to the interface which says if there is another value available.

```Java
public interface DiceShaker {
    boolean hasNext(); //is there another value available
    int next();
}
```
For the Fixed case, then the implementation of hasNext checks the current index

```Java
public class FixedSingleDiceShaker implements DiceShaker {

  private final int[] shakes = new int[]{
      1, 2, 3, 4, 5, 6
  };

  private int index;

  @Override
  public boolean hasNext() {
    return index < shakes.length;
  }

  @Override
  public int next() {
    return shakes[index++];
  }
}
```
We can now use a while loop with fixed or random implementations - the fixed implementation will exit the loop when the available values are exhausted, the random implementation will run forever.

```Java
while (shaker.hasNext()) {
  int shake = shaker.next();
}
```
# Implement a DiceShaker using Abstract Classes and Abstract Methods

We can use an `abstract` base class as the abstract type, and provide different concrete implementations using inheritance (subclassing).

## Create an abstract superclass.
```java
public abstract class AbstractDiceShaker {

  private static final Random random = new Random();

  protected int shakeSingleDie() {
    //generate number between 1 and 6
    return random.nextInt(6) + 1;
  }
  public abstract boolean hasNext();
  public abstract int next();

}
```
## Create concrete implementations for 1 and 2 die (as before).
```java
public class ConcreteSingleDiceShaker extends AbstractDiceShaker {
  @Override
  public boolean hasNext() {
    return true;
  }

  @Override
  public int next() {
    return shakeSingleDie();
  }
}

public class ConcreteDoubleDiceShaker extends AbstractDiceShaker {
  @Override
  public boolean hasNext() {
    return true;
  }

  @Override
  public int next() {
    return shakeSingleDie() + shakeSingleDie();
  }
}
```
Test your implementation in the main method (as before), note the type of the reference is the abstract class type, not the implementation type.
```java
AbstractDiceShaker abstractShaker = new ConcreteSingleDiceShaker();
```
Now implement a version that provides a fixed set of ‘shakes’.

**Question: What problem can you see using the abstract base class to implement a fixed set of ‘shakes’.**

**Question: What are the differences between implementing an interface and extending an abstract class.**
Which one would you choose?


## Hints and Tips

An **abstract** class is a class that is incomplete (abstract means it is lacking one or more fields, or implementation of one or more methods) and requires a subclass to extend the abstract class to implement the missing methods.

This is why you can declare abstract classes, but not actually instantiate one - because an abstract has an incomplete implementation.

A method declaration lacking an implementation is abstract. In Java, you can declare methods abstract to force subclasses to implement methods with a specific signature. Abstract methods just consist of their method specification (name, parameter types and return types).

A class with a complete implementation is called a **concrete** class. You can only **instantiate** (create objects) concrete classes.

An interface is an abstract type (abstract meaning that it is lacking implementation of one or more methods).

Interfaces are abstract because they do not have fields (only constants allowed) and declare one or more abstract methods (methods without an implementation body). Note that the abstract keyword is unnecessary because interfaces are *implicitly* abstract.

Being abstract, interfaces cannot be instantiated. Instead, the methods of the interface must be **implemented** by a class using the implements keyword. A class must provide the complete set of methods required by the interface, but each class can have its own implementation. This makes an interface a **specification** to be fulfilled by classes that implement the interface.

Interfaces are key to writing extensible and flexible code because:

- one interface can be implemented by many different and unrelated classes, each of which has a unique implementation of the interface specification.

- the choice of which concrete type supplies the interface implementation can be made at runtime, allowing us to vary behaviour based on some runtime condition (such as user input, choice of operating system, production or test mode...)

# Create a version of a Fixed diceShaker that implements the `Iterable<Integer>` interface (Advanced)

The interface we defined for DiceShaking
```Java
public interface DiceShaker {
    boolean hasNext(); //is there another value available
    int next();
}
```
is very similar to the `Iterator<T>` interface in the Java standard library.

```Java
public interface Iterator<T> {
    boolean hasNext();
    T next();
}
```
It is unusual to use an Iterator<T> directly. Instead, we usually implement the `Iterable<T>` interface on a class that holds the collection of elements.

The `Iterable<T>` interface is part of the Java Collections Framework and is used to represent a collection of objects that can be iterated over. The interface defines a single method, `iterator()`, which returns an `Iterator<T>` object that can be used to traverse the elements of the collection.

Classes that implement the `Iterable<T>` interface can be used in enhanced for-loops (also known as "for-each" loops).

Write an implementation of Iterator<Integer> that simulates a Fixed dice shaker. Note that you will need to use the `Integer` reference type rather the `int` primitive type.

Then write a class that implements Iterable<Integer> that uses your Iterator<Integer> implementation.

Create a console application example that demonstrates your implementation by using an enhanced for-loop to iterate over the fixed set of dice shakes.

# Create a Package
> ☑ Code written for the assignment should be written using packages and make proper use of the private, protected and public access declarations.

Use the Intelli-J File menu -> New > Project… to create a new sample project with a main method as before

## Create a new Package

In the src directory, create a new package. Call it `mypackage`

Right-click on the package and create a new class called Product within the package.

```java
package mypackage;
class Product {
    double discount = 0d;
    double fullPrice;
    double sellingPrice;

    Product(double fullPrice)
    {
        this(fullPrice, 0d);
    }
    Product(double fullPrice, double discount)
    {
        this.fullPrice = fullPrice;
        this.discount = discount;
        setSellingPrice();
    }
    void setDiscount(double discount) {
        //check pre- and post-conditions
        this.discount = discount;
        setSellingPrice();
    }
    void setFullPrice(double fullPrice) {
        //check pre- and post-conditions
        this.fullPrice = fullPrice;
        setSellingPrice();
    }
    double getSellingPrice() {
        return sellingPrice;
    }
    void setSellingPrice() {
        sellingPrice = fullPrice - discount;
    }
}

```

In the main method in the default package, create the following client code

```java
public class Main {
    public static void main(String[] args) {
        Product product = new Product(100.0d);
        product.setDiscount(20d);
        //Outputs Selling Price 80.000000
        System.out.format("Selling Price %f%n", product.getSellingPrice());
    }
}
```

Now add the package import access control declarations (public or private) to the Product class to make this code work. The declarations should provide the minimum amount of accessibility necessary to make the client code in the main() method work.

## Hints and Tips

When you create a class in Java you want to hide as much of its
implementation as possible. This is so that we can change the
implementation without breaking our client code because client code has
used a method or accessed a field we now want to change.

Java provides mechanisms for access control, to prevent the users of a
package or class from accessing unnecessary details of the
implementation of that package or class. If access is permitted, then
the accessed entity is said to be accessible (Oracle, 2024 Ch 6.6).

When you declare a new class in Java, you can indicate the level of
access permitted to its members (fields and methods). Java provides four
levels of **access specifiers**. Three of the levels must be explicitly
specified:

**private** meaning that the member or constructor is only accessible
within the declaring class.

**protected** meaning that the member or constructor is only accessible
to subclasses of the declaring class

**public** meaning that the member or constructor is publicly
accessible.

If no access specifier is used then the member is implicitly **package
private**.

We can summarise the effect of access specifiers on members and
constructors. The table shows if a member or constructor is accessible.

|                                 | public | protected | default (package private) | private |
|---------------------------------|--------|-----------|---------------------------|---------|
| Same class                      | Yes    | Yes       | Yes                       | Yes     |
| Class in same package           | Yes    | Yes       | Yes                       | No      |
| Subclass in same package        | Yes    | Yes       | Yes                       | No      |
| Class in different package      | Yes    | No        | No                        | No      |
| Subclass in different package   | Yes    | Yes       | No                        | No      |


Experiment with the different access specifiers to understand their effects. The IDE or compiler will give you feedback on if a member or constructor is accessible.

# Encapsulation and Information Hiding

Most discussions about object orientation talk about **encapsulation**.

Encapsulation at the class level is the hiding of the implementation (fields, private methods) behind a public application programming interface (API). The internal implementation is not visible outside the class. The state of an object can only read or updated via the operations the public API.

A good design of a single class has a long-lived public application programming interface. The clients of that API do not need knowledge of or access to the internal fields and private methods, which means that the internal implementation can change providing the API still behaves as the client expects.

In Java, the concept of encapsulation scales up to the package. The public API offered by the package is the set of all public members of all public classes within the package, but the internal implementation of the package is hidden by using package private classes and (package) private members.

Encapsulation is one form of **information hiding** - a general principle in software engineering of creating any API (application programming interface) that hides design decisions and reveals as little as possible of the inner workings. Hiding implementation information in general allows us to work with something without having to know about all its internal details and it should be possible to refactor (change for the better) the implementation without a need for the client code to change. Hiding implementation details behind an interface is one of the fundamental ideas of software engineering that helps us cope with complexity and change.

# Implement Inherited Interfaces (Advanced)
Interfaces can be inherited. For example:
```java
interface InterfaceA {
    String someMethod();
}
interface InterfaceB extends InterfaceA {
    String someOtherMethod();
}
```

Write an implementation class that implements `InterfaceB`.

Classes can also implement multiple interfaces.

```java
interface MyInterface
{
    String someMethod(String paramName1, int paramName2);
}
interface MyOtherInterface
{
    String someOtherMethod(String paramName1, int paramName2);
}

```
Write an implementation class that implements BOTH `MyInterface` and `MyOtherInterface`.

**Question: Why would you use interface inheritance and why might you write a class that implements multiple interfaces ?**

## Hints and Tips

Use Intelli-J features to create the boilerplate code for you.

A simple output to the console should suffice as a way of demonstrating the implementation of a method.

```java
System.out.printf("Hello from %s%n", myImplementation.someMethod());
//or if you prefer System.out.format
System.out.format("Hello from %s%n", myImplementation.someOtherMethod());
```

See https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/Formatter.html#syntax for details on the %s and %n format specifiers in format strings

> ☑ Having a good knowledge of string formatting in Java saves a lot of time when creating complex strings.

# Experiment with Typed References (Advanced)

> ☑ This lab exercise is intended to test your understanding how Java interfaces and abstract classes work  - important because these are the language mechanics we need for writing flexible, extensible and maintainable code - one of the main goals of software design and software engineering.

Implement the following code in your test program and confirm that each reference can only “see” certain operations depending on the type. What do you observe?

```java
interface MyInterface {
    void interfaceMethodA();
}
```

```java
//D implements MyInterface
public class D implements MyInterface {
    @Override
    public void interfaceMethodA() {
        System.out.printf("Hello from D.interfaceMethodA%n");
    }

    public void methodA() {
        System.out.printf("Hello from D.methodA%n");
    }
}

```
```java
//E is a subtype of D and MyInterface
public class E extends D {
    @Override
    public void methodA() {
        System.out.printf("Hello from E.methodA%n");
    }

    public void methodB() {
        System.out.printf("Hello from E.methodB%n");
    }

    public void methodC() {
        System.out.printf("Hello from E.methodC%n");
    }
}

```
The code to use the implementations of D and E

```java
//Operations available to the variable of type MyInterface are just interfaceMethodA()
MyInterface myInterface = new E();
myInterface.interfaceMethodA(); //prints Hello from D.interfaceMethodA

//Operations available to the variable of type D are interfaceMethodA() and methodA()
D d = new E();
d.interfaceMethodA(); //Hello from D.interfaceMethodA
d.methodA(); //Hello from E.methodA

//Operations available to the variable of type E interfaceMethodA(), methodA() and methodB();
E e = new E();
e.interfaceMethodA(); //Hello from D.interfaceMethodA
e.methodA(); //Hello from E.methodA
e.methodB(); //Hello from E.methodB
```
## Hints and Tips
We have seen that when we are writing a Java class, methods can be either abstract (without an implementation) or concrete (with an implementation). If we are writing a Java interface, all its methods are abstract.

We can discuss design in terms of **operations**, without having to specify if the operation is abstract or concrete.

We can say that clients of an object request **operations**. Getting or setting field values, and invoking methods are all operations.

If the operation is abstract because it is defined as an abstract class method or within a Java interface, It's up to the Java runtime to map the requested operation to the correct method of a concrete class.

The type of the variable determines the set of operations available to the client. The set of operations available to the client are the public operations defined by the type and all its supertypes.
To put this in more theoretical terms,

- E is a subtype of MyInterface because MyInterface has a subset of the operations of E.

- E is a subtype of D because D has a subset of the operations of E.

- E is a subtype of itself because E has a subset of the operations of E (the same set of operations).

Therefore

- You can create and assign an instance of E to a variable of type MyInterface (MyInterface has a subset of the operations of E, E is a subtype of MyInterface).

- You can create and assign an instance of E to a variable of type D (D has a subset of the operations of E, E is a subtype of D).

- You can create and assign an instance of E to a variable of type E (E has a subset of the operations of E, E is a subtype of itself)

- You *can't* create and assign an instance of D to a variable of type E because E does not have a subset of the operations of D, and D is not a subtype of E.

In Java all reference types are subtypes of Object. This is why you can assign an instance of any Reference Type to Object because Object has a subset of the operations of any reference type.

> ☑ Understanding how Java interfaces and abstract classes work is important because these are the language mechanics we need for writing flexible, extensible and maintainable code - one of the main goals of software design and software engineering.

# Understand Command Operations and Query Operations (Advanced)

Operations can be **commands** (an operation which changes the values of one or more instance variables, the state of the object) or **queries** (an operation that returns a value but does not change the state of an object).

Whilst it may seem an academic distinction to make, a true query does not have **side effects** because it doesn't change the state of the object - sometimes referred to as a **read only** operation, which means that a client can call a query knowing it won't change the state of the object. Knowing that an operation is read only is actually very useful, because you know you can call the operation as many times as you like, and it will not change the state of the object.

In Java is there is no language mechanism to say if an operation is a command or a query. By convention in Java the method `int getX()` method would return the value of the field x as an integer. Anyone calling that method would be very surprised if `int getX()` had changed the object state. You should follow that convention.

⚠ Be aware that you could call the same query twice in different parts of the program and there is no guarantee it would get the same value returned, because a command (a state changing operation) could have been requested between the two query operations.

☑ Where possible write your code so that operations that return values are **queries** and all **command** (state altering) operations are written as void methods. If you do write a command (state altering method) that returns a value, ensure you document that it is state altering.


