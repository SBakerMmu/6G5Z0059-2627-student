package rbgstatemachinestatepattern;

class ConcreteContext implements Context {
    private State currentState;

    ConcreteContext() {
        currentState = new Red(this); //initial state
    }

    @Override
    public void changeState(State nextState) {
        currentState = nextState;
    }

    public void forward() {
        currentState.forward();
    }

    public void reverse() {
        currentState.reverse();
    }
}
