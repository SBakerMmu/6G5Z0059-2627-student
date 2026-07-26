package generalbridge;

class RefinedAbstraction3 extends Abstraction {
    public RefinedAbstraction3(Implementor implementor) {
        super(implementor);
    }

    @Override
    public void operation() {
        //Do something specific to RefinedAbstraction3
        implementor.operationImplementation("Hello from Abstraction 3");
    }
}
