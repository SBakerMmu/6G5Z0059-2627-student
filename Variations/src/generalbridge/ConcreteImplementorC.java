package generalbridge;

class ConcreteImplementorC implements Implementor {
    @Override
    public void operationImplementation(String abstraction) {
        System.out.format("ConcreteImplementorC abstraction%s%n", abstraction);
    }
}
