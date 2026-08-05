package factorymethod;

abstract class TTLFactory  implements AbstractFactory {

    private static final long TTL_MS = 1000;

    private AbstractObject cachedInstance = null;
    private long cacheTimestamp = 0;

    public AbstractObject create() {
        long now = System.currentTimeMillis();
        if (cachedInstance == null || (now - cacheTimestamp) > TTL_MS) {
            cachedInstance = doCreate();
            cacheTimestamp = now;
        }
        return cachedInstance;
    }

    protected abstract AbstractObject doCreate();

}
