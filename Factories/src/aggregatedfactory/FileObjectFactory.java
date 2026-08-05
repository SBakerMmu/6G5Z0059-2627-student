package aggregatedfactory;

class FileObjectFactory implements AbstractFactory {
    @Override
    public AbstractObject create() {
        //code to get object from filestore
        return new FileObject();
    }
}
