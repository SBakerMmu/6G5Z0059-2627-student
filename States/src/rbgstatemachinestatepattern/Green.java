package rbgstatemachinestatepattern;

class Green implements State {

    private final static String NAME = "Green";
    private final Context  context;
    public Green(Context context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return NAME;
    }

    @Override
    public void forward() {
        State next = new Red(context);
        System.out.printf("forward %s -> %s%n", this, next);
        context.changeState(next);
    }

    @Override
    public void reverse() {
        State previous = new Blue(context);
        System.out.printf("reverse %s -> %s%n", this, previous);
        context.changeState(previous);
    }
}
