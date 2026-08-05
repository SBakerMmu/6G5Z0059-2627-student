package factorymethod;


public class Example {

    public static void run()
    {
        AbstractFactory factory = new FileObjectSingletonFactory();
        showOperations(factory);
        factory = new DatabaseObjectSingletonFactory();
        showOperations(factory);
        factory = new FileObjectTTLFactory();
        showOperations(factory);
        factory = new DatabaseObjectTTLFactory();
        showOperations(factory);
    }


    private static void showOperations(AbstractFactory factory) {
        AbstractObject expensiveObject = factory.create();
        System.out.format("%s%n", expensiveObject);
    }


}
