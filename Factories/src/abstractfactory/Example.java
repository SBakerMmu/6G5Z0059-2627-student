package abstractfactory;


public class Example {

    public static void run()
    {
        AbstractFactory factory = new FileObjectFactory();
        showOperations(factory);
        factory = new DatabaseObjectFactory();
        showOperations(factory);
    }

    private static void showOperations(AbstractFactory factory) {
        AbstractObject object = factory.create();
        System.out.format("%s%n", object);
    }
}
