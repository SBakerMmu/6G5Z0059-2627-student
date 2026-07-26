package generalbridge;

public final class Example {

    public static void run() {

        Implementor implementationA = new ConcreteImplementorA();
        Implementor implementationB = new ConcreteImplementorB();
        Implementor implementationC = new ConcreteImplementorC();

        Abstraction abstraction1A = new RefinedAbstraction1(implementationA);
        Abstraction abstraction1B = new RefinedAbstraction1(implementationB);
        Abstraction abstraction1C = new RefinedAbstraction1(implementationC);
        Abstraction abstraction2A = new RefinedAbstraction2(implementationA);
        Abstraction abstraction2B = new RefinedAbstraction2(implementationB);
        Abstraction abstraction2C = new RefinedAbstraction2(implementationC);
        Abstraction abstraction3A = new RefinedAbstraction3(implementationA);
        Abstraction abstraction3B = new RefinedAbstraction3(implementationB);
        Abstraction abstraction3C = new RefinedAbstraction3(implementationC);

        //There are 9 combinations, but 6 classes (3 abstractions and 3 implementors)
        invokeOperation(abstraction1A);
        invokeOperation(abstraction1B);
        invokeOperation(abstraction1C);

        invokeOperation(abstraction2A);
        invokeOperation(abstraction2B);
        invokeOperation(abstraction2C);

        invokeOperation(abstraction3A);
        invokeOperation(abstraction3B);
        invokeOperation(abstraction3C);
    }

    private static void invokeOperation(Abstraction abstraction) {
        abstraction.operation();
    }
}
