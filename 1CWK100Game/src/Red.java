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
