package rbgstatemachinestatepattern;

class Red implements State {

  private final static String NAME = "Red";
  private final Context context;

  public Red(Context context) {
    this.context = context;
  }

  @Override
  public String toString() {
    return NAME;
  }

  @Override
  public void forward() {
    State next = new Blue(context);
    System.out.printf("forward %s -> %s%n", this, next);
    context.changeState(next);
  }

  @Override
  public void reverse() {
    State previous = new Green(context);
    System.out.printf("reverse %s -> %s%n", this, previous);
    context.changeState(previous);
  }
}
