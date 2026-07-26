package generalstateconstructor;

class ConcreteStateB implements State {
    private final Context context;

    ConcreteStateB(Context context) {
        this.context = context;
    }

    @Override
    public void handle() {
        context.setStatus(new ConcreteStateA(context));
    }
}

