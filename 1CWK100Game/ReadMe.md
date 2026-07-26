# 1CWK100 Game Hints

Some hints if you have encountered difficulties with the logic of tracking the positions of Red and Blue players, particularly the Blue player as they start on the board at position 25.

**If you have already solved this and your code is working, DO NOT feel you have to change your code - this is intended to guide students who haven’t got a basic version working by providing the easiest possible implementation, and we’re not looking for all students to implement it this way.**

There are many ways of managing this, but one of the simplest coding strategies is to map an index for each player to a position on the board. Each player tracks their position as a simple increasing number, but that number is mapped to different board positions depending on the player.

First, create an array of position numbers, and then use a zero based index into that array (Java arrays are zero based, in that the index of the first element of the array = 0).

Red and Blue track their progress using a zero based index. For the small board each player has an index which goes from 0 to 24 but the index is **mapped** to a position number representing a position on the board.

Red's array of position numbers looks like

```java
private final static int[] POSITIONS = new int[]{
        1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25
};
```
Red's starting position is index = 0, which when applied to the array, return board position 1. If Red advanced 2 the index = 2, which maps to board position 3.

We can use the same method for Blue. Its POSITIONS array has different values

```java
private final static int[] POSITIONS = new int[]{
        25, 24, 23, 22, 21, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1
};

```

Blue's starting position is index = 0, which when applied to the array, return board position 25. If Blue advanced 2 the index = 2, which maps to board position 23.

We can use the HOME and END index positions in the `toString()` method to provide a string representation of the player's position.


For example
```java
class Red {
    private final static int HOME_INDEX = 0;
    private final static int END_INDEX = 24;
    private final static int[] POSITIONS = new int[]{
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25
    };

    private int currentIndex = 0;

    public boolean isHome() {
        return currentIndex == HOME_INDEX;
    }

    public boolean isAtEnd() {
        return currentIndex == END_INDEX;
    }

    public int getPosition() {
        return POSITIONS[currentIndex];
    }

    @Override
    public String toString() {
        if (isHome())
            return String.format("HOME (Position %d)", getPosition());
        if (isAtEnd())
            return String.format("END (Position %d)", getPosition());
        else
            return String.format("Position %d", getPosition());

    }

    public void advance(int positions) {
        currentIndex = currentIndex + positions;
        if (currentIndex > END_INDEX) {
            currentIndex = END_INDEX;
        }
    }
}
```
The `isHome()` method simply checks if the current index is 0 (i.e. the first element of the array).

The `isAtEnd()` method checks if the current index is the last (end) element of the array

The `advance(int positions)` method increments the current index, but if this would make the index after the end of the array (an overshoot), it sets the index to the end of the array instead

The implementation of the Blue class is identical, apart from the values inside the array, which represent the position numbers that the Blue player would follow.



The starter code above should help you get the basic game working, but the code is *terrible*.
There are many things that could be improved in the code above to make a much more object-oriented solution (and hence improve the marks for code quality) but this should be enough to get you going if you were stuck.

## Implementing Variations

To implement the varations, we recommend you study the strategy pattern.

For example, the implementation of the `advance` method in Blue and Red limits the index so that it does not go past the end of the array.

```Java
  public void advance(int positions) {
    currentIndex = currentIndex + positions;
    if (currentIndex > END_INDEX) {
        currentIndex = END_INDEX;
    }
}
```

You can replace that with a strategy that calculates the currentIndex, and provide two variations, one of which contains the original code, the other makes the player go backwards on overshoot.

An example interface

```Java
interface IndexStrategy {
    int calculateIndex(int currentIndex, int positions, int endIndex);
}
```

The implementation that simply sets the index to the end

```Java
class DoesNotNeedToLandOnEndToWin implements IndexStrategy {
    @Override
    public int calculateIndex(int currentIndex, int positions, int endIndex) {
        currentIndex = currentIndex + positions;
        if (currentIndex > endIndex) {
            currentIndex = endIndex;
        }
        return currentIndex;
    }
}
```

Now the `advance` method uses the strategy instead of having the logic hard coded, and by providing different strategy implementations, you can vary the outcome.

```java

  public void advance(int positions, IndexStrategy strategy) {
        currentIndex =  strategy.calculateIndex(currentIndex, positions, END_INDEX);
    }

```










