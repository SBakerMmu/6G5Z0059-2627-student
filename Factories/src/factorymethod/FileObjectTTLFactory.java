package factorymethod;

class FileObjectTTLFactory extends TTLFactory {
    @Override
    protected AbstractObject doCreate() {
        //code to get object from filestore
        return new FileObject();
    }
}
