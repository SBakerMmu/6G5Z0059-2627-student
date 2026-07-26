package multiobserver;

public final class Example {
    public static void run() {
        MyClass myClass = new MyClass();
        //Now can have multiple observers
        myClass.addObserver(new NullObserver());
        myClass.addObserver(new ConsoleObserver());

        myClass.myOperation("ABC123");
    }
}
