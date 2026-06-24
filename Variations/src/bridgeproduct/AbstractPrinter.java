package bridgeproduct;

import valueobjects.Price;

abstract class AbstractPrinter implements ProductPrinter {

    @Override
    public void printString(String string) {
        System.out.format("%s", string);
    }

    @Override
    public void printPrice(Price price) {
        System.out.format("%s", price);
    }

    @Override
    public void printNewLine() {
        System.out.format("%n");
    }

    @Override
    public void printBarcode(String format, String code) {
        System.out.format("[%s] %s", format, code);
    }

    protected void printHeader(String header) {
        System.out.format("===BEGIN %s===%n", header);
    }

    public void printFooter(String footer) {
        System.out.format("===END %s===%n", footer);
    }


}
