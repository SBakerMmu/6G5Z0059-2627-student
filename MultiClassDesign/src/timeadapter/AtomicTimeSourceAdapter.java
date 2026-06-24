package timeadapter;


class AtomicTimeSourceAdapter implements YearMonthProvider {

    private final AtomicTimeSource source;

    public AtomicTimeSourceAdapter(AtomicTimeSource source) {
        this.source = source;
    }


    @Override
    public int getYear() {
        long  timeInMs  = source.getTimeInMs();
        //logic to convert to year
        return 0;
    }

    @Override
    public int getMonth() {
        long timeInMs = source.getTimeInMs();
        //logic to convert to month
        return 0;
    }
}
