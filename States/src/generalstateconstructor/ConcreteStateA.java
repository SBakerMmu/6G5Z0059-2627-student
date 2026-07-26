package generalstateconstructor;

class ConcreteStateA implements State {

    private final Context context;

    ConcreteStateA(Context context) {
        this.context = context;
    }

    @Override
    public void handle() {
        context.setStatus(new ConcreteStateB(context));
    }
}
