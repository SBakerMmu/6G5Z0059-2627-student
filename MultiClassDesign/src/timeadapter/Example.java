package timeadapter;

public final class Example {

    public static void run() {
        AtomicTimeSource timeSource = new AtomicTimeSource();
        YearMonthProvider provider = new AtomicTimeSourceAdapter(timeSource);
        provider.getMonth();
        provider.getYear();
    }
}
