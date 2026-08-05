package aggregatedfactory;

class DatabaseObjectFactory implements AbstractFactory {
    @Override
    public AbstractObject create() {
        //code to get object from databast
        return new DatabaseObject();
    }
}
