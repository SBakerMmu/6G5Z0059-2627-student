package visitorproduct;

import valueobjects.Price;

abstract class AbstractPrinter implements ProductVisitor {

    protected void printHeader(String header) {
        System.out.format("===BEGIN %s===%n",header);
    }

    protected void printString(String string)
    {
        System.out.format("%s", string);
    }

    protected void printPrice(Price price)
    {
        System.out.format("%s", price);
    }

    protected void printNewLine() {
        System.out.format("%n");
    }
    protected void printBarcode(String format, String code) {
        System.out.format("[%s] %s", format, code);
    }
    protected void printFooter(String footer) {
        System.out.format("===END %s===%n",footer);
    }
}
