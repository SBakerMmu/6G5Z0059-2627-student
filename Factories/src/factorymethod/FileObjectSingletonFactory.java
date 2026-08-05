package factorymethod;

class FileObjectSingletonFactory extends SingletonFactory {
    @Override
    protected AbstractObject doCreate() {
        //code to get object from filestore
        return new FileObject();
    }
}
