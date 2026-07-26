package rbgstatemachinestatepattern;


public final class Example {

    public static void run() {
        System.out.format("Two Event Machine using State Pattern%n");
        ConcreteContext concreteContext = new ConcreteContext();
        concreteContext.forward();
        concreteContext.forward();
        concreteContext.forward();
        concreteContext.reverse();
        concreteContext.reverse();
        concreteContext.reverse();
    }
}
