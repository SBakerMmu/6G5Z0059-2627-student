package generalstateconstructor;

class ConcreteContext implements Context {
    private State status;

    ConcreteContext() {
        //set initial state
        status = new ConcreteStateA(this);
    }

    @Override
    public void setStatus(State state) {
        this.status = state;
    }

    public void request() {
        this.status.handle();
    }
}
