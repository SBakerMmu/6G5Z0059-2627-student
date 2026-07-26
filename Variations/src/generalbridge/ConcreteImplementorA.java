package generalbridge;

class ConcreteImplementorA implements Implementor {
    @Override
    public void operationImplementation(String abstraction) {
        System.out.format("ConcreteImplementorA abstraction%s%n", abstraction);
    }
}
