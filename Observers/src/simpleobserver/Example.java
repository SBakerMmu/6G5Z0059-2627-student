package simpleobserver;

public final class Example {
    public static void run() {
        //Replace NullObserver with ConsoleObserver to get output
        MyClassObserver observer = new NullObserver();
        MyClass myClass = new MyClass(observer);
        myClass.myOperation("ABC123");
    }
}
