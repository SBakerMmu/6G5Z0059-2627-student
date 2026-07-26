package generalbridge;

class ConcreteImplementorB implements Implementor {
    @Override
    public void operationImplementation(String abstraction) {
        System.out.format("ConcreteImplementorB abstraction%s%n", abstraction);
    }
}
