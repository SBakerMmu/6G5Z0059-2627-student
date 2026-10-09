# Software Design and Architecture Week 1 Lab Solutions

Suggested answers to the Week 1 lab exercises.

## Coding Standard
> ☑ The main things to look for are
Class, field and method naming conventions,
sensible layout.

```java

class GoodStyleClass {

  int avalue = 0;
  int bvalue = 0;

  public GoodStyleClass() {
    avalue = 1;
    bvalue = 1;
  }

  public int add1(int x, int y) {
      if (x < 6) {
          return x + y;
      }
    return 0;
  }

  public int add2(int x, int y) {
    for (int i = 0; i < y; i++) {
      x += 1;
    }
    return x;
  }
}
```

## Name the parts of a class correctly.

```java

abstract class Student {

  //field declarations
  //static fields are sometimes called class variables.
  // The final keyword indicates the field has a constant value, i.e. once the class variable has been initialized the value cannot be changed
  private static final String UNIVERSITY = "Example University";

  //non-static fields can be called instance variables
  //The final keyword indicates the field has a constant value, i.e. once the instance variable has been initialized the value cannot be changed
  private final int studentID;
  //these non-static fields are initialised but not final
  private String name = "Unknown";
  private String course = "Undeclared";

  //Constructor
  public Student(int studentID) {
    this.studentID = studentID;
  }

  //Methods

  //get and set methods are sometimes called Properties, or Getters and Setters
  //By convention, they take the name of the field they get or set.
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getStudentID() {
    return studentID;
  }

  public String getCourse() {
    return course;
  }

  public void setCourse(String course) {
    this.course = course;
  }

  public static String getUniversity() {
    return UNIVERSITY;
  }

  //abstract methods have no implementation
  public abstract double calculateDegreeClassification();

  @Override
  public String toString() {
    return "Student{" +
        "name='" + name + '\'' +
        ", studentID=" + studentID +
        ", major='" + course + '\'' +
        '}';
  }
}
```

## Write a Java Class implementation.

```java
import java.util.Objects;
class DiceRoll implements Comparable<DiceRoll> {
    private final int value;

    DiceRoll(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        DiceRoll diceRoll = (DiceRoll) o;
        return value == diceRoll.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return String.format("DiceRoll %d", value);
    }

    @Override
    public int compareTo(DiceRoll other) {
        if(Objects.isNull(other))
        {
            throw new NullPointerException("other");
        }
        return Integer.compare(this.value, other.value);
    }
}
```
> ☑ Note that `compareTo(null)` should throw a NullPointerException even though `equals(null)` returns false.

The main point about implementing the TwoDiceRoll class is how it delegates work to the two DiceRoll instances.

```java
import java.util.Objects;
class TwoDiceRoll implements Comparable<TwoDiceRoll> {

    private final DiceRoll one;
    private final DiceRoll two;

    public TwoDiceRoll(DiceRoll one, DiceRoll two) {
        this.one = one;
        this.two = two;
    }

    public int getValue() {
        return one.getValue() + two.getValue();
    }

    //The equals implementation is made up of equals() applied to all non-static fields
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TwoDiceRoll that = (TwoDiceRoll) o;
        return this.one.equals(that.one) && this.two.equals(that.two);
    }

    //The hash  implementation is made up of Objects.hash() applied to all non-static fields
    @Override
    public int hashCode() {
        return Objects.hash(one.hashCode(), two.hashCode());
    }


    @Override
    public String toString() {
        return String.format("TwoDiceRoll %s %s", one, two);
    }

    @Override
    public int compareTo(TwoDiceRoll other) {
        if(Objects.isNull(other))
        {
            throw new NullPointerException("other");
        }
        return Integer.compare(this.getValue(), other.getValue());
    }
}

```

Any software design that makes use of classes will want to at various times

- compare instances for equality
- use instances as keys in hash-based collections such as HashMap or HashSet
- print instances for logging or debugging (overriding toString())
- compare instances for ordering (implementing Comparable)

Not all classes will need to do all of these things, but many will. Being able to do these things correctly is an important part of software design. Deciding what equality or comparison means for a particular class is a design decision that needs to be made carefully.

## Why does Java have both Comparable<T> and Comparator<T> interfaces?

A class that implements Comparable defines a single, natural ordering for its instances. Sometimes you might want to provide a different order.

You can write a Comparator class that defines an alternative ordering.

```java
class ReverseTwoDiceRollComparator implements Comparator<TwoDiceRoll> {
    @Override
    public int compare(TwoDiceRoll o1, TwoDiceRoll o2) {
        if(Objects.isNull(o1))
        {
            throw new NullPointerException("o1");
        }
        if(Objects.isNull(o2))
        {
            throw new NullPointerException("o2");
        }
        return o1.compareTo(o2) * -1;
    }
}
```
> ☑ Unlike Comparable, a comparator may optionally permit comparison of null arguments.

Example usage:

```java

DiceRoll one = new DiceRoll(1);
DiceRoll anotherOne = new DiceRoll(1);
DiceRoll six = new DiceRoll(6);
DiceRoll anotherSix = new DiceRoll(6);

System.out.format("%s%n", six);
System.out.format("== operator %b%n", six == six);
System.out.format("== operator %b%n", six == anotherSix);
System.out.format("equals %b%n", six.equals(anotherSix));
System.out.format("one hashcode %d%n", one.hashCode());
System.out.format("anotherOne hashcode %d%n", anotherOne.hashCode());
System.out.format("six hashcode %d%n", six.hashCode());
System.out.format("anotherSix hashcode %d%n", anotherSix.hashCode());


TwoDiceRoll doubleOne = new TwoDiceRoll(one, anotherOne);
TwoDiceRoll anotherDoubleOne = new TwoDiceRoll(one, anotherOne);

System.out.format("%s%n", doubleOne);
System.out.format("== operator %b%n", doubleOne == doubleOne);
System.out.format("== operator %b%n", doubleOne == anotherDoubleOne);
System.out.format("equals %b%n", doubleOne.equals(anotherDoubleOne));
System.out.format("doubleOne hashcode %d%n", doubleOne.hashCode());
System.out.format("anotherDoubleOne hashcode %d%n", anotherDoubleOne.hashCode());



TwoDiceRoll two = new TwoDiceRoll(one, one);
TwoDiceRoll seven = new TwoDiceRoll(one, six);
TwoDiceRoll twelve = new TwoDiceRoll(six, six);

System.out.format("Comparing %s to %s: %d%n", two, seven, two.compareTo(seven));
System.out.format("Comparing %s to %s: %d%n", seven, seven, seven.compareTo(seven));
System.out.format("Comparing %s to %s: %d%n", twelve, seven, twelve.compareTo(seven));

List<TwoDiceRoll> unsorted = new ArrayList<>();
unsorted.add(twelve);
unsorted.add(two);
unsorted.add(seven);

System.out.format("List before sort %n");
for (TwoDiceRoll roll : unsorted) {
    System.out.format("%s with value %d%n", roll, roll.getValue());
}

System.out.format("List after sort %n");
List<TwoDiceRoll> sorted = unsorted.stream().sorted().toList();
for (TwoDiceRoll roll : sorted) {
    System.out.format("%s with value %d%n", roll, roll.getValue());
}

System.out.format("Using reverse sorting comparator %n");
ReverseTwoDiceRollComparator comparator = new ReverseTwoDiceRollComparator();
List<TwoDiceRoll> reverseSorted = unsorted.stream().sorted(comparator).toList();
for (TwoDiceRoll roll : reverseSorted) {
    System.out.format("%s with value %d%n", roll, roll.getValue());
}
```

### Further reading

[Comparable<T> interface](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/lang/Comparable.html)

[Comparator<T> interface](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/Comparator.html)
