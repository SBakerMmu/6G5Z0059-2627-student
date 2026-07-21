package rbgstatemachinestatepattern;

class Green implements State {
    private static final String NAME = "Green";
    private final Context context;
    private final State previous;

    public Green(Context context, State previous) {
        this.context = context;
        this.previous = previous;
    }

    @Override
    public String toString() {
        return NAME;
    }

    @Override
    public void forward() {
        State next = new Red(context, this);
        System.out.printf("forward %s -> %s%n", this, next);
        context.changeState(next);
    }

    @Override
    public void reverse() {
        System.out.printf("reverse %s -> %s%n", this, previous);
        context.changeState(previous);
    }
}
