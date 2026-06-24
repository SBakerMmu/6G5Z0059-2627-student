package rbgstatemachinestatepattern;

class Blue implements State {

    private static String NAME = "Blue";
    private final Context context;
    public Blue(Context context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return NAME;
    }

    @Override
    public void forward() {
        State next = new Green(context);
        System.out.printf("forward %s -> %s%n", this, next);
        context.changeState(next);
    }

    @Override
    public void reverse() {
        State previous = new Red(context);
        System.out.printf("reverse %s -> %s%n", this, previous);
        context.changeState(previous);
    }
}
