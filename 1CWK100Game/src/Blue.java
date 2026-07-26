class Blue {
    private final static int HOME_INDEX = 0;
    private final static int END_INDEX = 24;
    private final static int[] POSITIONS = new int[]{
            25, 24, 23, 22, 21, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1
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

