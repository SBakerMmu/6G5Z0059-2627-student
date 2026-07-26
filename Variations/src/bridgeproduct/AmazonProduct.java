package bridgeproduct;

import valueobjects.ASIN;
import valueobjects.Price;

class AmazonProduct extends Product {
    private final ASIN asin;

    public AmazonProduct(ASIN asin, Price price, ProductPrinter printer) {
        super(price, printer);
        this.asin = asin;
    }

    public ASIN getAsin() {
        return asin;
    }


    @Override
    public String toString() {
        return asin.toString();
    }


    //Amazon specific print implementation requires ASIN and Price to be printed on the same line and no barcode is printer
    @Override
    public void print() {
        ProductPrinter printer = getPrinter();
        printer.printHeader();
        printer.printString("ASIN: ");
        printer.printString(asin.toString());
        printer.printString(" ");
        printer.printPrice(getPrice());
        printer.printNewLine();
        printer.printFooter();

    }
}
