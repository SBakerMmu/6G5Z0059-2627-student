package generalstateconstructor;

public final class Example {

    public static void run() {
        ConcreteContext context = new ConcreteContext();
        context.request();
        context.request();
    }
}
