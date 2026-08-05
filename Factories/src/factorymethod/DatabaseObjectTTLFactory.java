package factorymethod;

class DatabaseObjectTTLFactory extends TTLFactory {
    @Override
    protected AbstractObject doCreate() {
        //code to fetch from database
        return new DatabaseObject();
    }
}
