# Software Design and Architecture - Week 05 Lab Solutions

## Using Decorators to implement different Dice Shakers
We asked you to use the **Decorator** pattern to decorate an implementation of a RandomSingleDiceShaker to simulate a shaker with 2 (or more) six-sided dice.

We start with the `DiceShaker` interface

```java
interface DiceShaker {
    int shake();
}
```
A simple implementation of a random dice shaker that shakes a single 6-sided die.

```java
class SingleDiceShaker implements DiceShaker {

    private final Random random = new Random();

    @Override
    public int shake() {
        //generate number between 1 and 6
        return random.nextInt(6) + 1;
    }
}
```
Usage is simple
```java
  DiceShaker shaker = new SingleDiceShaker();
  int value = shaker.shake();
```
Because the client is using an abstract type (in this case an interface), we can wrap a **decorator** around our dice shaker.

```java
class DiceShakerDecorator implements DiceShaker{
    private final DiceShaker component;
    DiceShakerDecorator(DiceShaker component) {
        this.component = component;
    }

    @Override
    public int shake() {
        return component.shake();
    }
}
```
The usage changes to supply the decorator to the client.
```java
  DiceShaker shaker = new DiceShakerDecorator(new SingleDiceShaker());
  int value = shaker.shake();
```
At this stage the decorator isn't actually adding any functionality, it is just forwarding the call. We have implemented a pipeline (or chain) of calls.

We can add to our decorator class to roll another die and add to the result.

```java
class DiceShakerDecorator implements DiceShaker{
    private final DiceShaker component;
    private final DiceShaker shaker = new SingleDiceShaker();
    DiceShakerDecorator(DiceShaker component) {
        this.component = component;
    }

    @Override
    public int shake() {
        return shaker.shake() + component.shake();
    }
}
```
Now the decorator is adding another die to the original component.

We can chain these decorators as many times as we want to simulate throwing multiple dice.

For example this will simulate throwing 3 dice.

```java
  DiceShaker shaker = new DiceShakerDecorator(
    new DiceShakerDecorator(
        new SingleDiceShaker()
    )
);
int value = shaker.shake();
```
However, we can just as easily write a decorator that logs the throw to the console.

Each decorator is responsible for one thing only, but we have added multiple functionalities to our chain.

```java
class ConsoleWriter implements DiceShaker{

    private final DiceShaker component;

    ConsoleWriter(DiceShaker component) {
        this.component = component;
    }

    @Override
    public int shake() {
        int value = component.shake();
        System.out.format("Shake %d%n", value);
        return value;
    }
}
```
Usage

```java
DiceShaker shaker = new ConsoleWriter(
  new DiceShakerDecorator(
    new DiceShakerDecorator(
      new SingleDiceShaker()
    )
  )
);
int value = shaker.shake();
```
We could end up repeating the code that builds the pipeline. We have seen this problem before.

Solve this with a set of abstract factories that create the pipelines of different lengths. Note how we are aggregated factories so we are reusing code rather than reimplementing it.

```java
interface DiceShakerFactory {
    DiceShaker create();
}

class SingleDiceShakerFactory implements DiceShakerFactory {
  @Override
  public DiceShaker create() {
    return new SingleDiceShaker();
  }
}

class DoubleDiceShakerFactory implements DiceShakerFactory {
  private final DiceShakerFactory factory = new SingleDiceShakerFactory();
  @Override
  public DiceShaker create() {
    return new DiceShakerDecorator(factory.create());
  }
}

class TripleDiceShakerFactory implements DiceShakerFactory {
  private final DiceShakerFactory factory = new DoubleDiceShakerFactory();
  @Override
  public DiceShaker create() {
    return new DiceShakerDecorator(factory.create());
  }
}
```
Now we can use any of these factories to create new DiceShakers with the required number of dice.

```java
 public static void run() {
  DiceShakerFactory factory = new SingleDiceShakerFactory();
  show(factory);

  factory = new DoubleDiceShakerFactory();
  show(factory);

  factory = new TripleDiceShakerFactory();
  show(factory);
}

//Outputs (for example) from the SingleDiceShakerFactory
//Shake 6
//Shake 1
//Shake 5

private static void show(DiceShakerFactory factory) {
    DiceShaker shaker = new ConsoleWriter(factory.create());
    shaker.shake();
    shaker.shake();
    shaker.shake();
}
```
Decorators provide a form of subclassing.

In Java subclassing the subclass extends the superclass and can add or replace functionality provided by the superclass. Using conventional subclassing the functionality is decided at compile time. With decorators, the functionality is decided dynamically at runtime by adding one or more decorators.

Decorators can add additional code before or after the call to the decorated component.

A Decorator can also decide to handle the call itself and not pass it on to its decorated component.

This pattern is called the **Chain of Responsibility** pattern or sometimes the **Handler** pattern - the code structure is almost the same as the Decorator pattern, the difference between the Decorator Pattern and the Chain of Responsibility is that Decorators *always* pass on the call to the next component in the chain (the successor), whereas with this pattern the handler *sometimes* passes on the call to the successor, depending on if the handler could handle the call themselves or not.

Decorators provide us with a way of enhancing (adding or changing functionality) an operation. Chains of Responsibility allow different classes to handle an operation. These components can be chained together to make a **Pipeline** for handling an operation requested by a client.

In large-scale software systems there is often functionality that we want to add to our code that has nothing to do with the core business functionality. Chaining components can help us satisfy these requirements.

## Implementing Dice Shaker Decorators using the Null Object Pattern
The implementation above uses two concrete classes - SingleDiceShaker and DiceShakerDecorator. There is a design solution that uses only one concrete class by using the **Null Object** pattern.

If we create an implementation of DiceShaker that optionally takes another DiceShaker to decorate, we can use a **Null Object** when no decoration is required.

```Java
class SingleDiceShaker implements DiceShaker {

    private static class NullShaker implements DiceShaker {
        public int shake() {
            return 0;
        }
    }

    private final Random random = new Random();
    private final DiceShaker component;

    public SingleDiceShaker() {
        this.component = new SingleDiceShaker.NullShaker();
    }

    public SingleDiceShaker(DiceShaker component) {
        this.component = component;
    }

    @Override
    public int shake() {
        //generate number between 1 and 6
        return (random.nextInt(6) + 1) + component.shake();
    }
}
```
In this implementation, there are two constructors, one that takes an external component and one that doesn't (the parameterless constructor).

However, in the parameterless constructor we provide a **Null Object** which works with the `int shake()` method but returns a "null" value (which in this case is 0).

We could have used `null` instead of a Null Object, but then we would have to check for null in the `shake()` method, which would add complexity to the code.

The implementation uses a private nested class  (`NullShaker`) to implement the Null Object, but it could be a separate package private class if required.

### The Null Object Patten.
The **Null Object** pattern is a design pattern that provides an object to represent the lack of an object, which would otherwise leave us testing for null value.

The special "null" implementation conforms to the expected interface but does nothing. Obviously the null implementation must **conform** and be substitutable for non-null implementations. In this case returning 0 from the `shake()` method is appropriate as it does not affect the total value of the dice shake.

Consider using the Null Object pattern when you have to test for `null` values in your code or are using `Optional<T>` types, and testing for the presence or absence of T.

## Using Observers to observe Game Events
We proposed a simple game board with the following positions.
```text
         1 [HOME]
[END] 6     2
      5     3
         4
```
A game piece starts at position 1 (the HOME position) and advances clockwise based on the throw of a 6-sided die.

For example, a throw of 4 will move the piece from position 1 to 5. This is an “underflow” because the piece has not gone past the HOME position.

A subsequent throw of 3 with move the piece from position 5 to position 2. This is an “overflow” because the piece has gone past the last or END position.

The interface that describes the operations of the Game board

```java
interface GameBoard {
    void advance(int count);
}
```
One implementation of the game board without an observer.

```java
class NonObservedGameBoard implements GameBoard {

  private static final int HOME = 0;
  private static final int END = 5;
  private static final int LENGTH = END - HOME + 1;
  private int index;

  NonObservedGameBoard() {
    setIndex(HOME);
  }

  private void setIndex(int newIndex) {
      if (newIndex >= HOME && newIndex <= END) {
          index = newIndex;
      } else {
          throw new IndexOutOfBoundsException(newIndex);
      }
  }

  @Override
  public int getCurrentPosition() {
    return index + 1;
  }

  @Override
  public void advance(int count) {
    int newIndex = index + count;
    setIndex(newIndex % LENGTH);
  }
}
```
To "observe" the game create some events and an Observer interface

We have asked for Underflow, Overflow and Home events, which can all share a common abstract PositionChangedEvent superclass.

```java
abstract class PositionChangeEvent {
  private final int advance;
  private final int oldPosition;
  private final int newPosition;

  PositionChangeEvent(int advance, int oldPosition, int newPosition) {
    this.advance = advance;
    this.oldPosition = oldPosition;
    this.newPosition = newPosition;
  }

  public int getAdvance() {  return advance; }

  public int oldPosition() {
    return oldPosition;
  }

  public int newPosition() {
    return newPosition;
  }

  @Override
  public String toString() {
    return String.format("{advance %d oldPosition=%d, newPosition=%d}", advance, oldPosition, newPosition);
  }
}

final class UnderflowEvent extends PositionChangeEvent {

    UnderflowEvent(int advance, int oldPosition, int newPosition) {

        super(advance, oldPosition, newPosition);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UnderflowEvent");
        sb.append(super.toString());
        return sb.toString();
    }
}


class OverflowEvent extends PositionChangeEvent {

  OverflowEvent(int advance,int oldPosition, int newPosition) {
    super(advance, oldPosition, newPosition);
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("OverflowEvent");
    sb.append(super.toString());
    return sb.toString();
  }
}

class HomeEvent extends PositionChangeEvent {

  HomeEvent(int advance, int originalPosition, int newPosition) {
    super(advance, originalPosition, newPosition);
  }

  public String toString() {
    final StringBuilder sb = new StringBuilder("HomeEvent");
    sb.append(super.toString());
    return sb.toString();
  }
}
```

The observer interface.

```java
interface GameBoardObserver {
    void onEvent(OverflowEvent overflowEvent);
    void onEvent(UnderflowEvent underflowEvent);
    void onEvent(HomeEvent homeEvent);
}
```
An implementation of the observed game.

```java
class ObservedGameBoard implements GameBoard {

  private static final int HOME = 0;
  private static final int END = 5;
  private static final int LENGTH = END - HOME + 1;
  private int currentIndex;
  private final List<GameBoardObserver> observers = new ArrayList<>();

  private static int indexToPosition(int index) {
    return index + 1;
  }

  ObservedGameBoard() {
    setIndex(HOME);
  }

  private void setIndex(int newIndex) {
    if (newIndex >= HOME && newIndex <= END) {
      currentIndex = newIndex;
    } else {
      throw new IndexOutOfBoundsException(newIndex);
    }
  }

  void add(GameBoardObserver observer) {
    observers.add(observer);
  }

  void detach(GameBoardObserver observer) {
    observers.remove(observer);
  }


  @Override
  public int getCurrentPosition() {
    return indexToPosition(currentIndex);
  }

  @Override
  public void advance(int advance) {
    int candidateIndex = currentIndex + advance;
    if (candidateIndex > END) {
      onOverflow(advance, candidateIndex);
    } else {
      onUnderflow(advance, candidateIndex);
    }
  }

  private void onOverflow(int advance, int candidateIndex) {
    int originalIndex = currentIndex;
    int newIndex = candidateIndex % LENGTH;
    setIndex(newIndex);
    if (newIndex == HOME) {
      HomeEvent event = new HomeEvent(advance, indexToPosition(originalIndex),
          indexToPosition(newIndex));
      notifyObservers(observer -> observer.onEvent(event));
    } else {
      OverflowEvent event = new OverflowEvent(advance, indexToPosition(originalIndex),
          indexToPosition(newIndex));
      notifyObservers(observer -> observer.onEvent(event));
    }

  }

  private void onUnderflow(int advance, int newIndex) {
    int originalIndex = currentIndex;
    setIndex(newIndex);
    UnderflowEvent event = new UnderflowEvent(advance, indexToPosition(originalIndex),
        indexToPosition(newIndex));
    notifyObservers(observer -> observer.onEvent(event));
  }

  private void notifyObservers(Consumer<GameBoardObserver> action) {
    observers.forEach(action);
  }
}
```

We now need to write an actual observer to receive these events. This example just prints the events out, using the event's toString() method.

```java
class ExampleObserver implements GameBoardObserver
{

    @Override
    public void onEvent(OverflowEvent overflowEvent) {
        System.out.format("%s%n", overflowEvent);
    }

    @Override
    public void onEvent(UnderflowEvent overflowEvent) {
        System.out.format("%s%n", overflowEvent);
    }

    @Override
    public void onEvent(HomeEvent homeEvent) {
        System.out.format("%s%n", homeEvent);
    }
}
```
When we create an instance of the ObservedGameBoard we can attach as many observers as we like.

```java
ObservedGameBoard game = new ObservedGameBoard();
GameBoardObserver observer = new ExampleObserver();
game.add(observer);
//play the game
game.detach(observer);
```

## Do and Undo using Commands

We asked you to use the Command pattern to provide an advance and undo advance for the game board used in the lab.

We will need to extend the `GameBoard` interface to support getting and setting positions.

```Java
interface GameBoard {
    int getCurrentPosition();
    void setPosition(int newPosition);
    void advance(int count);
}
```

We define Advance and PositionChanged events to provide to an observer.

```Java

class AdvanceEvent {
    private final int advance;
    private final int oldPosition;
    private final int newPosition;

    AdvanceEvent(int advance, int oldPosition, int newPosition) {
        this.advance = advance;
        this.oldPosition = oldPosition;
        this.newPosition = newPosition;
    }

    public int getAdvance() {  return advance; }

    public int oldPosition() {
        return oldPosition;
    }

    public int newPosition() {
        return newPosition;
    }

    @Override
    public String toString() {
        return String.format("{advance %d oldPosition=%d, newPosition=%d}", advance, oldPosition, newPosition);
    }
}

class PositionChangeEvent {
    private final int oldPosition;
    private final int newPosition;

    PositionChangeEvent(int oldPosition, int newPosition) {
        this.oldPosition = oldPosition;
        this.newPosition = newPosition;
    }

    public int oldPosition() {
        return oldPosition;
    }

    public int newPosition() {
        return newPosition;
    }

    @Override
    public String toString() {
        return String.format("{oldPosition=%d, newPosition=%d}",oldPosition, newPosition);
    }
}

interface GameBoardObserver {
    void onEvent(AdvanceEvent event);
    void onEvent(PositionChangeEvent event);
}
```
The command interface.

```Java
interface Command {
  void execute();
  void undo();
}
```
The implementation of an AdvanceCommand takes the GameBoard and records the current position just before calling `board.advance(...)`. That why each command instance knows the position to restore the game to when its `undo()` method is called.

```Java
class AdvanceCommand implements Command{

  private final GameBoard board;
  private final int advance;
  private int previousPosition;

  AdvanceCommand(GameBoard board, int advance) {
    this.board = board;
    this.advance = advance;
  }

  @Override
  public void execute() {
    previousPosition = board.getCurrentPosition();
    board.advance(advance);
  }

  @Override
  public void undo() {
    board.setPosition(previousPosition);
  }
}
```

We use a Stack<T> collection to record the command history, as this makes reversing the sequence of play simple by popping commands off the top of the stack.

```Java
 static void run() {
    ObservedGameBoard game = new ObservedGameBoard();
    GameBoardObserver observer = new ExampleObserver();
    game.add(observer);
    play(game);
    game.detach(observer);
 }

private static void play(GameBoard game)
{
    Stack<AdvanceCommand> commands = new Stack<>();
    commands.push(new AdvanceCommand(game,4));
    commands.push(new AdvanceCommand(game,3));
    for(Command command: commands)
    {
        command.execute();
    }
    //undo
    while(!commands.empty())
    {
        commands.pop().undo();
    }
}
```

