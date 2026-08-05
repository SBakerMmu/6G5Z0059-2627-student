package factorymethod;

abstract class SingletonFactory implements AbstractFactory {

    private AbstractObject cachedInstance = null;

    public AbstractObject create()
    {

        if(cachedInstance == null)
        {
            //look up data from database or file
            cachedInstance = doCreate();
        }
        return cachedInstance;

    }
    protected abstract AbstractObject doCreate();
}
