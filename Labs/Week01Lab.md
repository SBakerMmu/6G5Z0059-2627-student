# Software Design and Architecture Week01 Lab Worksheet

There are multiple activities each week, and you will probably not get everything done in the timetabled lab sessions; therefore, it is highly recommended that you complete the labs in your own time each week to avoid falling behind.

Completing the labs will get you ready for writing the assignment code.

**Advanced** Labs are optional, but completing the Advanced Labs will introduce you to more advanced techniques and improve your design skills.

# Setting up your Development Environment

This lab is all about getting your development environment set up for the rest of the module and preparing to write your assignment code.

## Install the IntelliJ Integrated Development Environment (IDE)

In industry, VS Code, Microsoft Visual Studio and JetBrains IntelliJ are the most widely used integrated development environments (IDEs).
You have used Eclipse and VS Code in other modules, this will give you exposure to IntelliJ.

### IntelliJ on University Machines

If you are using a University PC, then IntelliJ Community Edition is already installed. Not all PCs have this though - you will need a PC with specialist Science and Engineering software installed. The PCs in the Dalton building and lab rooms are specialist Science and Engineering PCs and should have IntelliJ installed.

The PC must be running Windows to access IntelliJ.

For other locations with the software installed see https://www.mmu.ac.uk/library/using-the-library/pcs-laptops-and-printing.

### IntelliJ on Personal Machines

If you have a personal laptop or home machine (Windows or macOS), then download and install from [JetBrains IntelliJ IDEA](https://www.jetbrains.com/idea/download/).

Download the installer and follow the installation instructions from the page.

This will install a version of IntelliJ on your personal machine that includes a 30-day trial of the Ultimate edition (a paid for product). After 30 days, it will revert to the free Community Edition. There is no advantage to using the Ultimate edition for this module, so you can just use the Community Edition.

Alternatively, you can download and install the JetBrains Toolbox App from [JetBrains Toolbox App](https://www.jetbrains.com/toolbox-app/) and use it to install and manage IntelliJ IDEA instead. This has the advantage that it will notify you of updates to IntelliJ and allow you to manage multiple JetBrains products from one application. You will probably need to register for a free JetBrains account to use the Toolbox App.

If you are happy to do so then you can also apply for a Student Pack license. See [JetBrains Student Pack](https://www.jetbrains.com/community/education/#students) for more details. This will give you access to some of the paid for products for free while you are a student, and a discount after graduation. It is not necessary to have a Student Pack license to complete this module - just the standard IntelliJ IDE is all that is needed.

If you have an old copy ensure you uninstall it or upgrade it, and make sure you have at least version 2025.x or later.

## Learn to use IntelliJ

Go to the [Getting Started](https://www.jetbrains.com/help/idea/getting-started.html) page to access the online tutorials to get you using IntelliJ productively.
> ☑ Following online tutorials to learn a new development tool is a valuable Software Engineering skill – it allows you to get started on projects by yourself which is a something employers will appreciate.

Follow these online tutorials to help you get using IntelliJ.

- Create your first Java application.
- Learn IDE features.
- Write the source code.
- Configure your project.

### Locate your development project files correctly

It is very important that you store your development project files in the correct location on your machine. When creating sample projects, ensure that you locate your project files correctly - where you do this depends on if you are using a University PC or a personal machine.

### Creating projects on University Machines

A University PC automatically maps network drives to PC Drives.

- O: is mapped to One Drive
- W: is a networked drive somewhere on the University campus

These are not suitable for development work:
- Lots of small and temporary files, frequently accessed.
- Issues with Git, IntelliJ builds, performance issues (especially when working on larger projects)

**Use local C: drive instead**

Create projects under `C:\users\<MMUId>\` where <MMUId> is your student id.

> ☠ University PCs will delete your user folder on the C: drive periodically. Therefore, ensure that you either copy your files to OneDrive or W: drive regularly at the end of the session, or better still,
use Git to push your code to your GitHub repository regularly to avoid losing work. Using Git is the recommended approach.

### Creating projects on Personal Machines (Windows)

The same problems with One Drive or Google Drive or other networked drive apply to personal machines as well, so avoid creating projects in network drive folders.

When creating projects on your personal machine, create them on somewhere on local C: drive. As it is your machine, you have a lot more flexibility on where you put your projects. Depending on your operating system and configuration, then there may be a default location.

If you are unsure, then create projects under `C:\users\<user>\` where <user> is your Windows user name.

> ☑ If you are an advanced Windows user, you might consider creating a dedicated folder for your development projects, for example `C:\_dev\` or similar. You can also create a 'DevDrive' which is a separate partition on your hard drive dedicated to development work. See (Set up a Dev Drive on Windows 11)[https://learn.microsoft.com/en-us/windows/dev-drive/]. It is not necessary to do this, but it can help keep your development work separate from your personal files and has a small performance benefit.

### Creating projects on Personal Machines (macOS)

The same problems with One Drive or Google Drive or other networked drive apply to macOS as well, so avoid creating projects in network drive folders.

Again, you have more control of where to create projects, but if you are unsure, then create projects under `~/` directory. The tilde (~) is shorthand for your home directory in operating systems like macOS.

### Installing the Java Development Kit (JDK)

We recommend using the Download JDK option in IntelliJ when creating a new project. This will download and install the specific version of the JDK for you. See the lecture notes for an explanation of the JDK and which version to use.

We will be using JDK 25 for this module and for now we are using the IntelliJ build system.

## Get a GitHub account and use Git
> ☑ The use of Git to manage source code is a fundamental skill. The 2021 Stack Overflow Survey suggested that over 93% of developers used Git for managing source code, and that percentage has grown even further since then as legacy projects switch to Git. Use of Git is a key skill for software engineers at any level and you should mention Git competency on your CV.

### Get a GitHub Account

Go to https://github.com/ and sign up for a free account. You may already have done this, in which case log in.

### Create a Repository

> ☑ See the lecture notes for instructions on creating a repository.

In your GitHub account, create a new repository for your code. Name it `sda` (or similar), make it private, add a README file, and choose to add a .gitignore file for Java.

### Github Desktop

> ☑ See the lecture notes for instructions on creating using Desktop

We will use GitHub Desktop to manage our Git repositories.

Github Desktop is pre-installed on University PCs with the Science and Engineering software. For personal machines, download and install GitHub Desktop from [GitHub Desktop](https://desktop.github.com/).

Clone the `sda` repository you created above to your local machine using GitHub Desktop to

- C:\users\<MMUId>\sda (University PC)
- C:\users\<UserName>\sda (Personal Windows machine)
- ~/sda/ (macOS personal machine)

Create a new IntelliJ project under this SDA folder. You can then use GitHub Desktop to commit and push your code changes to your GitHub repository. Check you can see the changes

> ☑ See the lecture notes for instructions on committing and pushing code changes using GitHub Desktop.

Check you changes have appeared in the GitHub web interface.

Make a change to the README file in the GitHub web interface and commit the change.

Pull the changes down to your local machine using GitHub Desktop.

Now delete the local `sda` folder and clone it again using GitHub Desktop to ensure you can pull the code down again.

# Learning Git, GitHub and GitHub Desktop (Advanced)

> ☑ Being proficient in Git and GitHub is an essential skill for software developers. You will need to learn how to use Git and GitHub effectively to manage your code throughout this module and in your future modules.

This will take some time so you will need to complete this in your own time outside of labs. There are many online resources to learn Git and GitHub.

Some ones you could try
- [GitHub Skills](https://skills.github.com/).
- https://github.com/skills/introduction-to-github
- https://docs.github.com/en/desktop/overview/getting-started-with-github-desktop

As this is an individual module, there is no need to learn branching and merging for this module, but you should be aware of these features for future modules and professional practice.

The key features you need to learn for doing individual work are
- Cloning a repository
- Committing changes to the local repository
- Pushing changes to GitHub
- Pulling changes from GitHub


# Implement a Coding Standard

> ☑ A coding standard is a set of rules about code style and naming. Having a consistent coding style makes your code easier to understand and navigate, both for yourself and for others.
>
> This significantly reduces the time and effort required for debugging, refactoring, and future enhancements.

Read the [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html).

This is the standard we will be using for the Software Design and Architecture module—it is widely adopted in industry.

Pay particular attention to the sections on Source File Structure, Naming, and Programming Practice.

### Apply the Google Coding Style

Rework this code to apply the Google Java Style Guide.

```java
class badStyleClass {

    public int ADD1(int x, int y)
    {
        if (x < 6) return x + y;
        return 0;
    }

    public badStyleClass(){AVALUE = 1; bvalue = 1;}

    public int Add2(int x, int y)
    {
        int MYVariable=y;
        for(int i=0;i<MYVariable;i++){
            x += 1;
        }return x;
    }

    int AVALUE, bvalue = 0;
}

```

# Learn Markdown

> ☑ Writing documents in Markdown has become common in software projects because it offers just enough formatting to be useful but requires almost no overhead to write. Markdown files are just text files, so they work well with source code and development tools like Git.

We will be using Markdown in the assignment. We can write Markdown using IntelliJ’s built-in Markdown editor, which provides a WYSIWYG experience for writing Markdown files.

Many text editors and online platforms (like GitHub or other project management platforms) support Markdown.

Markdown is very widely used in professional software projects. One reason is that keeping the documentation separate from the source code (for example as Word document) risks the two becoming out of synchronization. Writing the documentation using the same environment and using the same tools as your source code really helps make sure the documentation is up to date.

Learn to write Markdown using one or more of these resources.

- Take the online 10-minute Tutorial from [https://commonmark.org/help/](https://commonmark.org/help/)
- Follow the instructions in [https://www.jetbrains.com/help/idea/markdown.html](https://www.jetbrains.com/help/idea/markdown.html) to create a Markdown file in IntelliJ.
- GitHub Skills Markdown https://github.com/skills/communicate-using-markdown

### Example of Markdown syntax

```markdown
# Heading 1

## Heading 2

### Heading 3

*This text is italicized.*

**This text is bold.**

***This text is both bold and italicized.***

---

**This is an Unordered List**
- Item 1
- Item 2
  - Sub-item 1
    - Sub-item 2
- Item 3

---

**This is an ordered list**
1. First item
2. Second item
3. Third item

---

**Table**

| Fruit   | Color  | Price |
|---------|--------|-------|
| Apple   | Red    | 1.00  |
| Banana  | Yellow | 0.50  |

---

## A code example

```java
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}

```
Produces this output

# Heading 1

## Heading 2

### Heading 3

*This text is italicized.*

**This text is bold.**

***This text is both bold and italicized.***

---

**This is an Unordered List**
- Item 1
- Item 2
    - Sub-item 1
        - Sub-item 2
- Item 3

---

**This is an ordered list**
1. First item
2. Second item
3. Third item

---

**Table**

| Fruit   | Color  | Price |
|---------|--------|-------|
| Apple   | Red    | 1.00  |
| Banana  | Yellow | 0.50  |

---

## A code example

```java
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}
```


# Name the parts of a class correctly

Name the parts of this class using the lecture notes, Google style guide and your own research.

Copy the code into your IDE and annotate the code using comments to identify the parts of the class.

```java
public abstract class Student {
    private static final String UNIVERSITY = "Example University";
    private String name = "Unknown";
    private final int studentID;
    private String course = "Undeclared";


    public Student(int studentID) {
        this.studentID = studentID;
    }

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
# Implementing equals(), toString(), and hashCode()

If you implement a class in Java, it is often useful to implement the equals(), toString(), and hashCode() methods for that class. For classes that you want to compare for equality, or use as keys in hash-based collections (like HashMap), you must implement equals() and hashCode() correctly to ensure that your class behaves as expected.

IntelliJ has a quick and straightforward way of creating a new Java project that we can use for many of the labs.

`IntelliJ File menu → New > Project…`

Note that some settings may be different on your installation.

Provide a project name, choose a location, and ensure that you have ticked the Add sample code box.

This should generate a runnable program:

```java
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
```

When run, this outputs:

```
Hello, World!
Process finished with exit code 0
```

Implement equals(), toString(), and hashCode() for this DiceRoll class.

```java

class DiceRoll {
    private final int value;

    DiceRoll(int value) {
        this.value = value;
    }
}
```

Implement equals(), toString(), and hashCode() for the TwoDiceRoll class.


```java

class TwoDiceRoll {
private final DiceRoll one;
private final DiceRoll two;

    TwoDiceRoll(DiceRoll one, DiceRoll two) {
        this.one = one;
        this.two = two;
    }
}
```

**Question**: Should the equals() method of TwoDiceRoll compare the value of two the individual DiceRoll objects, or should it compare the total of the two DiceRolls - does the order of the two DiceRolls matter?

The equals implementation could

- Just compare the sum of the values of the two individual DiceRoll objects.
- Compare the two DiceRoll objects in order (i.e. one then two).
- Compare the two DiceRoll objects in any order (i.e. one and two are interchangeable).

Implement your chosen approach and justify your choice in comments in the code.

The teaching point here is that what constitutes equality depends on the context of the class and how it is to be used.

## Testing your implementation

You can write your test code in the static main method in the code generated for you by IntelliJ.

Typical demonstration code might look like this:

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
```
Expect output similar to this:

```
DiceRoll 6
== operator true
== operator false
equals true
one hashcode 32
anotherOne hashcode 32
six hashcode 37
anotherSix hashcode 37
TwoDiceRoll DiceRoll 1 DiceRoll 1
== operator true
== operator false
equals true
doubleOne hashcode 1985
anotherDoubleOne hashcode 1985
```

> ⚠ You might also use JUnit to write more formal unit tests for your classes. In IntelliJ, you can right-click on the class name and select "Generate" > "Test..." to create a test class automatically.

## Hints and Tips

Recall that Java has primitive and reference types.

`==` checks values for primitive types, but only checks the referential equality of two objects (i.e. both sides of the operation are the same instance in memory).

The default `Object.equals()` method also only checks the referential equality of two objects (i.e. equivalent to ==), so we need to override this default implementation to check the contents of our class.

Writing `equals()` correctly requires some care. You need to determine which fields participate (usually all of them) and then write the equality correctly.

The signature is `public boolean equals(Object obj)` so you need to deal with obj being null and also obj being a different class (in both cases return false).

Java has strict rules for equals() with non-null object references:

- Reflexive: for any non-null reference value x, x.equals(x) should return true.
- Symmetric: for any non-null reference values x and y, x.equals(y) should return true if and only if y.equals(x) returns true.
- Transitive: for any non-null reference values x, y, and z, if x.equals(y) returns true and y.equals(z) returns true, then x.equals(z) should return true.
- Consistent: for any non-null reference values x and y, multiple invocations of x.equals(y) consistently return true or consistently return false, provided no information used in equals comparisons on the objects is modified.

> ⚠ If you implement equals() you must implement hashCode() and vice-versa.

Some algorithms and data structures use the hash code value as a representation of a contents of the object—most notably the HashMap, which uses the hash code to decide which bucket should hold a key-value pair. As with equals(), there are a set of rules for implementing the hashCode() method:

It must be consistent like equals: `hashCode()` must consistently return the same integer, provided no information used in equals comparisons on the object is modified.

If two objects are equal according to the `equals()` method, their `hashCode()` methods must return the same integer value.

Hash codes are not necessarily unique, but a good `hashCode()` method distributes values widely.

> ☑ We strongly advise you use static int hash(Object... values) method of java.util.Objects to generate suitable hash values when there is more than one field involved in the equals. This is surprisingly hard to do well yourself.

# Making a Class Comparable (Advanced)
> ☑ Implementing the Comparable interface allows objects of your class to be compared to each other, which is useful for sorting and ordering.

For your DiceRoll and TwoDiceRoll class, implement the Comparable<T> interface so that DiceRoll objects can be compared based on their value.

Test your implementation in the main() method by creating multiple DiceRoll and TwoDiceRoll objects and comparing them using the compareTo() method.

For example

```java
DiceRoll one = new DiceRoll(1);
DiceRoll six = new DiceRoll(6);
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
```

This should produce output similar to this:

```
Comparing TwoDiceRoll DiceRoll 1 DiceRoll 1 to TwoDiceRoll DiceRoll 1 DiceRoll 6: -1
Comparing TwoDiceRoll DiceRoll 1 DiceRoll 6 to TwoDiceRoll DiceRoll 1 DiceRoll 6: 0
Comparing TwoDiceRoll DiceRoll 6 DiceRoll 6 to TwoDiceRoll DiceRoll 1 DiceRoll 6: 1
List before sort
TwoDiceRoll DiceRoll 6 DiceRoll 6 with value 12
TwoDiceRoll DiceRoll 1 DiceRoll 1 with value 2
TwoDiceRoll DiceRoll 1 DiceRoll 6 with value 7
List after sort
TwoDiceRoll DiceRoll 1 DiceRoll 1 with value 2
TwoDiceRoll DiceRoll 1 DiceRoll 6 with value 7
TwoDiceRoll DiceRoll 6 DiceRoll 6 with value 12
```
## Hints and Tips

The `Comparable<T>` interface defines a natural ordering for objects so they can be sorted, compared, and used in ordered collections. A class implements `Comparable<T>` and overrides `compareTo(T other)`.

Lists (and arrays) of objects that implement this interface can be sorted automatically by `Collections.sort` (and `Arrays.sort`). Objects that implement this interface can be used as keys in a sorted map or as elements in a sorted set.

The `compareTo(T other)` method returns:

- -1: this < other
- zero: this equals other
= +1: this > other

The `compareTo` method must be consistent with `equals()`: if `compareTo` returns zero for two objects, then `equals()` must return true for those objects.

When implementing `compareTo`, you typically compare the relevant fields of the objects to determine their order. For example, for DiceRoll, you would compare the value field. There are useful helper methods in the `Integer` class, such as `Integer.compare(int x, int y)`, which can simplify the implementation of `compareTo`.
