package aggregatedfactory;


public class Example {

    public static void run() {
        AbstractFactory factory = new SingletonFactory(new FileObjectFactory());
        showOperations(factory);
        factory = new SingletonFactory(new DatabaseObjectFactory());
        showOperations(factory);

        factory = new TTLFactory(new FileObjectFactory());
        showOperations(factory);
        factory = new TTLFactory(new DatabaseObjectFactory());
        showOperations(factory);
    }


    private static void showOperations(AbstractFactory factory) {
        AbstractObject object = factory.create();
        System.out.format("%s%n", object);
    }


}
