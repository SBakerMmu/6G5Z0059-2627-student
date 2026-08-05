package abstractfactory;

class DatabaseObjectFactory implements AbstractFactory {
    @Override
    public AbstractObject create() {
        //code to get object from database
        return new DatabaseObject();
    }
}
