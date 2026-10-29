# Software Design and Architecture - Week 04 Lab Solutions

Suggested answers to the Week 4 Lab Exercises

## Create Singletons representing Colors

```java
public class Color {


    public static final Color RED = new Color("Red", "\u001B[31m");
    public static final Color BLUE = new Color("Blue", "\u001B[34m");
    public static final Color GREEN = new Color("Green", "\u001B[32m");
    public static final Color YELLOW = new Color("Yellow", "\u001B[33m");

    private final String name;
    private final String escapeSequence;

    private Color(String name, String escapeSequence) {
        this.name = Objects.requireNonNull(name, "Color name cannot be null");
        this.escapeSequence = Objects.requireNonNull(escapeSequence, "Color escape sequence cannot be null");
    }

    public String getName() {
        return name;
    }

    public String getEscapeSequence() {
        return escapeSequence;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Color otherColor)) return false;
        if (this == otherColor) return true;
        return escapeSequence.equals(otherColor.escapeSequence) && name.equalsIgnoreCase(otherColor.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public String toString() {
        return String.format("%s%s\u001B[0m", escapeSequence, name);
    }
}

```
The name for a single instance is **Singleton**. There are several ways of creating a Singleton, but this lab shows the most common, that of making a final static instance.

Note that in the implementation code, the constructor is private, so that client code is forced to use one of the singleton instances - it cannot create its own instances.

Why is it important that any Singletons are final and immutable? because the singleton has global scope across the whole program. If you could change the value of a singleton, it could potentially break everywhere the singleton is used.

## Singleton Game Boards

First we need a class to represent a position on a game board. The position is represented by an index and a display name, and the class is immutable.
```java
public class Position {

    private final int index;
    private final String displayName;

    public Position(int index, String displayName) {
        if((this.index = index) < 0){
            throw new IllegalArgumentException("index must be greater than or equal to 0");
        }
        this.displayName = Objects.requireNonNull(displayName, "Display name cannot be null");

    }

    public int getIndex() {
        return index;
    }
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Position otherPosition)) return false;
        if (this == otherPosition) return true;
        return (index == otherPosition.index) && displayName.equalsIgnoreCase(otherPosition.displayName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, displayName);
    }
}
```
Note the use of `Objects.hash()` to create a hash code for an object that has multiple fields.

Make 2 immutable Board classes derived from an abstract Board class.

```Java
public abstract class Board {
    public abstract Position[] getPositions();
    public abstract String getName();
    @Override
    public String toString() {
        return getName();
    }
}
```

The small board variant has 25 positions held as a singleton array.

```java
public class SmallBoard extends Board {

    public static final Board INSTANCE = new SmallBoard();

    private static final String SIZE = "SmallBoard";

    private static final Position[] positions = new Position[]{
            new Position(0, "01"),
            new Position(1, "02"),
            new Position(2, "03"),
            new Position(3, "04"),
            new Position(4, "05"),
            new Position(5, "06"),
            new Position(6, "07"),
            new Position(7, "08"),
            new Position(8, "09"),
            new Position(9, "10"),
            new Position(10, "11"),
            new Position(11, "12"),
            new Position(12, "13"),
            new Position(13, "14"),
            new Position(14, "15"),
            new Position(15, "16"),
            new Position(16, "17"),
            new Position(17, "18"),
            new Position(18, "19"),
            new Position(19, "20"),
            new Position(20, "21"),
            new Position(21, "22"),
            new Position(22, "23"),
            new Position(23, "24"),
            new Position(24, "25")
    };

    @Override
    public Position[] getPositions() {
        return positions;
    }

    @Override
    public String getName() {
        return SIZE;
    }
}

```

The large board has 36 positions held as a singleton array.

```java
public class LargeBoard extends Board {

    public static final Board INSTANCE = new LargeBoard();

    private static final String SIZE = "LargeBoard";
    private static final Position[] positions = new Position[]{
            new Position(0, "01"),
            new Position(1, "02"),
            new Position(2, "03"),
            new Position(3, "04"),
            new Position(4, "05"),
            new Position(5, "06"),
            new Position(6, "07"),
            new Position(7, "08"),
            new Position(8, "09"),
            new Position(9, "10"),
            new Position(10, "11"),
            new Position(11, "12"),
            new Position(12, "13"),
            new Position(13, "14"),
            new Position(14, "15"),
            new Position(15, "16"),
            new Position(16, "17"),
            new Position(17, "18"),
            new Position(18, "19"),
            new Position(19, "20"),
            new Position(20, "21"),
            new Position(21, "22"),
            new Position(22, "23"),
            new Position(23, "24"),
            new Position(24, "25"),
            new Position(25, "26"),
            new Position(26, "27"),
            new Position(27, "28"),
            new Position(28, "29"),
            new Position(29, "30"),
            new Position(30, "31"),
            new Position(31, "32"),
            new Position(32, "33"),
            new Position(33, "34"),
            new Position(34, "35"),
            new Position(35, "36")
    };

    @Override
    public Position[] getPositions() {
        return positions;
    }

    @Override
    public String getName() {
        return SIZE;
    }
}
```
 # Write a Game Factory using the Abstract Factory pattern.

We want to create a GameFactory that can create different types of games, with matching board sizes and players (a SmallBoard with 2 players, or a LargeBoard with 4 players). The concrete Board and size of the Player array are a family of related objects.

Start with the interface.

```java
public interface AbstractGameFactory {
    Board createBoard();
    Player[] createPlayers();
}

```
and provide two concrete implementations of the AbstractGameFactory interface, one for a small game and one for a large game.

```Java

public class SmallBoardGameFactory implements AbstractGameFactory {
    @Override
    public Board createBoard() {
        return SmallBoard.INSTANCE;
    }

    @Override
    public Player[] createPlayers() {
        return new Player[] { new Player(Color.RED), new Player(Color.BLUE) };
    }
}
```


```java

public class LargeBoardGameFactory implements AbstractGameFactory {

    @Override
    public Board createBoard() {
        return LargeBoard.INSTANCE;
    }

    @Override
    public Player[] createPlayers() {
        return new Player[] { new Player(Color.RED), new Player(Color.BLUE), new Player(Color.GREEN) , new Player(Color.YELLOW)    };
    }
}

```
Note how the factory uses the singleton instances of the Board classes, and creates new instances of the Player class for each player in the game. This is because the Board classes are immutable and can be shared, but the Player class is mutable, and we need new instances every time the game is played.

By choosing the required concrete implementation of the AbstractGameFactory interface, we can create a game with the correct number of players for the board size.


```Java
public class Example {

    public static void run()
    {
        create(new SmallBoardGameFactory());
        create(new LargeBoardGameFactory());
    }


    private static void create(AbstractGameFactory factory)
    {
        Board board = factory.createBoard();
        Player[] players = factory.createPlayers();
        System.out.format("Board Positions: %d, Players: %d%n", board.getPositions().length, players.length);

    }
}
```

# Game Factory using the Factory Method pattern

The Factory Method pattern allows us to add code before or after the creation method.

We start with an abstract class that has the same public methods as the Abstract Factory example. These methods call abstract creation methods that are implemented by the concrete subclasses. The public methods can also call other methods before or after the creation methods, to add additional functionality (in this case the `OnBefore` and `OnAfter` methods).

```java
public abstract class AbstractGameFactory {

    public Board createBoard()
    {
        OnBeforeCreateBoard();
        Board board = doCreateBoard();
        OnAfterCreateBoard(board);
        return board;

    }


    public Player[] createPlayers()
    {
        OnBeforeCreatePlayers();
        Player[] players = doCreatePlayers();
        OnAfterCreatePlayers(players);
        return players;

    }

    protected abstract Board doCreateBoard();
    protected abstract Player[] doCreatePlayers();

    protected void OnAfterCreateBoard(Board board){
    }
    protected void OnBeforeCreateBoard() {
    }
    protected void OnAfterCreatePlayers(Player[] players) {
    }
    protected void OnBeforeCreatePlayers() {
    }
}
```

The two concrete subclasses implement the abstract `doCreate` methods.

```Java
public class SmallBoardGameFactory extends AbstractGameFactory {
    @Override
    public Board doCreateBoard() {
        return SmallBoard.INSTANCE;
    }

    @Override
    public Player[] doCreatePlayers() {
        return new Player[] { new Player(Color.RED), new Player(Color.BLUE) };
    }
}
```

```Java
public class LargeBoardGameFactory extends AbstractGameFactory {

    @Override
    public Board doCreateBoard() {
        return LargeBoard.INSTANCE;
    }

    @Override
    public Player[] doCreatePlayers() {
        return new Player[] { new Player(Color.RED), new Player(Color.BLUE), new Player(Color.GREEN) , new Player(Color.YELLOW)    };
    }
}

```

# Using the Abstract Factory Pattern to create different Iterators
We previously introduced the Java Iterable and Iterator Interfaces as a standard way of providing an iterator for a collection. The Iterable interface has a single method `iterator()` that returns an Iterator, and the Iterator interface has methods `hasNext()` and `next()` to access the elements of the collection sequentially without exposing its underlying representation.

If we want to provide different iterators for the same collection, we can use the Abstract Factory pattern to create different iterators. We need to use a Factory because the definition of the Iterable<T> interface is that it creates and returns a new instance of Iterator<T> when the `iterator()` method is called, so we need to use a Factory to create the different iterators.

Now the concrete implementation of the Factory is the thing that provides the different iterators, and the client code just uses the Iterable interface to get an iterator, without needing to know which concrete implementation of the Factory is being used.

For example, in the Game we might want to provide different iterators over a collection of Players. In this case the iterator is selecting the next player to take their turn, so we might want to have a different iterator for different gamefactorymethod modes. For example, in a "normal" gamefactorymethod mode we might want to iterate over the players in the order they were added to the gamefactorymethod, but in a "random" gamefactorymethod mode we might want to iterate over the players in a random order. In our implementation of iterators the sequence never stops, the next() method keeps providing players in order.

We start with our simple `Player` class we used in the previous lab exercises. The Player class is immutable, and has a name and a color.

```java
public class Player {
    private final Color color;
    private Position position;

    public Player(Color color) {
        this.color = Objects.requireNonNull(color);
    }

    public Color getColor() {
        return color;
    }

    public void setPosition(Position position) {
        this.position = Objects.requireNonNull(position);
    }

    public Position getPosition() {
        return position;
    }
}
```
The `PlayerIteratorFactory` interface is the abstract factory that creates the different iterators for array of players.

```java
interface PlayerIteratorFactory {
    Iterator<Player> create(Player... players);
}
```
The `PlayerIterable` class implements `Iterable<Player>`.

It has a constructor that takes a `PlayerIteratorFactory` and an array of `Player`s, and the `iterator()` method uses the factory to create and return a new instance of an iterator over the players every time the `iterator()` method is called.

We need to create a new iterator every time the `iterator()` method is called because the state of the iterator (the current position in the collection) is maintained in each iterator instance, so we can't reuse the same iterator instance for multiple iterations.

```java
class PlayerIterable implements Iterable<Player> {

    private final Player[] players;
    private final PlayerIteratorFactory factory;

    public PlayerIterable(PlayerIteratorFactory factory, Player[] players) {
        //make a copy of the players array in case the original array is modified after we create the PlayerIterable instance.
        this.players = Arrays.copyOf(players, players.length);
        this.factory = factory;
    }

    @Override
    public Iterator<Player> iterator() {
        return factory.create(players);
    }
}

```

We can now create our Factories and their corresponding iterators. We are going to make factories for **forward** (players take turns in the order they were added to the game), **reverse** (players take turns in the reverse order they were added to the game) and **random** (players take turns in a random order) play.

All three iterators are implemented as private inner classes, but they could be implemented as separate package private or public classes if desired. All the concrete implementations all share some elements of common code, and these common code elements have been generalized into abstract super classes.

The ForwardSelector increments the index by 1 each time the next() method is called, and uses the % operator to reset the index back to 0 when it goes above the length of the players array.
```java
class ForwardIteratorFactory implements PlayerIteratorFactory {
    // The implementation of Iterator<Player> is a private nested class so the only way to create an instance of it is through the create method of the PlayerIteratorFactory interface.
    private static class ForwardIterator implements Iterator<Player> {
        private final Player[] players;
        private int index = 0;

        public ForwardIterator(Player[] players) {
            this.players = players;
        }

        @Override
        public boolean hasNext() {
            return true; //always returns true as it is a cyclic iterator
        }

        @Override
        public Player next() {
            index = index % players.length; //reset index back to 0 before use if it goes above the length of the array using the % operator
            return players[index++];
        }
    }

    @Override
    public Iterator<Player> create(Player... players) {
        return new ForwardIterator(players);
    }
}
```
The ReverseIterator decrements the index by 1 each time the next() method is called, resetting the index back to the end of the array when it goes below 0 before use.

```java
class ReverseIteratorFactory implements PlayerIteratorFactory {
    // The implementation of Iterator<Player> is a private nested class so the only way to create an instance of it is through the create method of the PlayerIteratorFactory interface.
    private static class ReverseIterator implements Iterator<Player> {
        private final Player[] players;
        private int index;
        private final int lastIndex;
        public ReverseIterator(Player[] players) {
            this.players = players;
            index = lastIndex =  this.players.length - 1;
        }

        @Override
        public boolean hasNext() {
            return true; //always returns true as it is a cyclic iterator
        }

        @Override
        public Player next() {
            //reset index back to the end of the array if it goes below 0 before use;
            index = (index >= 0) ? index : lastIndex;
            return players[index--];
        }
    }

    @Override
    public Iterator<Player> create(Player... players) {
        return new ReverseIterator(players);
    }
}
```
The RandomIterator uses the Java Random class to select a random index between 0 inclusive and the length of the players array exclusive, and returns the player at that index.

```java
class RandomIteratorFactory implements PlayerIteratorFactory {
    // The implementation of Iterator<Player> is a private nested class so the only way to create an instance of it is through the create method of the PlayerIteratorFactory interface.
    // Randomly selects a random player using the Java Random class to select a number between 0 inclusive and players.length exclusive.
    private static class RandomIterator implements Iterator<Player> {
        private final Player[] players;
        private final Random random = new Random();

        public RandomIterator(Player[] players) {
            this.players = players;
        }

        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public Player next() {
            return players[random.nextInt(0, players.length)];
        }
    }


    @Override
    public Iterator<Player> create(Player... players) {
        return new RandomIterator(players);
    }
}

```
The `Game` class uses the standard Java `Iterable<Player>` class to get an iterator over the players, the Java language enhanced for loop (the `for-each` loop) is used to iterate over the players.

```java
class Game {

    private final Iterable<Player> players;

    public Game(Iterable<Player> players) {
        this.players = players;
    }

    public void play() {
        //play a simulated number of turns
        int turns = 10;
        for (Player player : players) {
            System.out.format("%s%n", player.getColor());
            //simulate the end of a game
            if (--turns == 0) {
                break;
            }
        }
    }
}
```


To show the game working with different player orderings, an example method is provided that plays a game with each of the three different factories, and shows the different player orderings in the output.

```java
public class Example {

    public static void run() {

        Player[] players = new Player[]{
                new Player(Color.RED),
                new Player(Color.BLUE),
                new Player(Color.GREEN),
                new Player(Color.YELLOW)
        };

        System.out.format("Forward%n");
        PlayerIteratorFactory factory = new ForwardIteratorFactory();
        play(factory, players);

        System.out.format("Reverse%n");
        factory = new ReverseIteratorFactory();
        play(factory, players);

        System.out.format("Random%n");
        factory = new RandomIteratorFactory();
        play(factory, players);

    }

    private static void play(PlayerIteratorFactory factory , Player[] players) {
        Iterable<Player> iterable =  new PlayerIterable(factory, players);
        Game game = new Game(iterable);
        game.play();
    }
}

```
There is one standard PlayerIterable class that takes a PlayerIteratorFactory and an array of Players, and the Game class just uses the standard Java Iterable interface to get an iterator over the players, without needing to know which concrete implementation of the PlayerIteratorFactory is being used. In fact the Game class can work with any implementation of the Iterable<T> interface, so it is very flexible and reusable.

In this design.

- Separated the responsibility for selecting the next player to take their turn out of a Game class by allowing the Game class to use the standard Java `Iterable` and `Iterator` interfaces.
- Because the Game class just uses the standard Java `Iterable` interface, we can use any concrete implementation to provide any variation in player ordering as required.
- The `Iterable` interface requires us to create a new iterator every time we want to iterate over the players, so we created a `PlayerIteratorFactory` that creates different iterators over the players, and the PlayerIterable class uses the factory to create a new iterator every time the `iterator()` method is called.
- Therefore, the variation in player ordering is controlled by the choice of concrete implementation of the PlayerIteratorFactory interface.

This is one of the main uses of the Abstract Factory (or Factory Method pattern). Where we used something like a Strategy to provide a variation in an algorithm, the Strategy had to be created first and then used repeatedly. If we need to create a new instance of something that varies, then the concrete implementation of the Factory controls which concrete type is created, and the client code just uses the Factory interface to create new instances as required.

The variation  is represented by the factory - different concrete factories produce different concrete implementations of an abstract type.
