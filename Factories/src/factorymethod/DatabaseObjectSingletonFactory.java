package factorymethod;

class DatabaseObjectSingletonFactory extends SingletonFactory {
    @Override
    protected AbstractObject doCreate() {
        //code to fetch from database
        return new DatabaseObject();
    }
}
