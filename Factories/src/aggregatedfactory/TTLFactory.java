package aggregatedfactory;

class TTLFactory implements AbstractFactory {

    private static final long TTL_MS = 1000;

    private final AbstractFactory abstractfactory;
    private AbstractObject cachedInstance = null;
    private long cacheTimestamp = 0;

    public TTLFactory(AbstractFactory abstractfactory) {
        this.abstractfactory = abstractfactory;
    }

    public AbstractObject create() {
        long now = System.currentTimeMillis();
        if (cachedInstance == null || (now - cacheTimestamp) > TTL_MS) {
            cachedInstance = abstractfactory.create();
            cacheTimestamp = now;
        }
        return cachedInstance;
    }
}
