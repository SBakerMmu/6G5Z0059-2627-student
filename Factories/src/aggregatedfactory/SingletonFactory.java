package aggregatedfactory;

class SingletonFactory implements AbstractFactory {

    private final AbstractFactory abstractfactory;
    private AbstractObject cachedInstance = null;

    SingletonFactory(AbstractFactory abstractfactory) {
        this.abstractfactory = abstractfactory;
    }

    public AbstractObject create() {
        if (cachedInstance == null) {
            //look up data from database or file
            cachedInstance = abstractfactory.create();
        }
        return cachedInstance;
    }
}
