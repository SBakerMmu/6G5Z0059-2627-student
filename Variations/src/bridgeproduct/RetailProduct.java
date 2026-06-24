package bridgeproduct;


import valueobjects.GTIN13;
import valueobjects.Price;

class RetailProduct extends Product {
    private final GTIN13 gtin13;

    public RetailProduct(GTIN13 gtin13, Price price, ProductPrinter printer) {
        super(price,printer);
        this.gtin13 = gtin13;
    }

    public GTIN13 getGtin() {
        return gtin13;
    }

    @Override
    public String toString() {
        return gtin13.toString();
    }

    //Retail Product specific print implementation requires the GTIN and Price to be printed on separate lines followed by a barcode
    @Override
    public void print() {
        ProductPrinter printer = getPrinter();
        printer.printHeader();
        printer.printString("GTIN: ");
        printer.printString(gtin13.toString());
        printer.printNewLine();
        printer.printString("Price: ");
        printer.printPrice(getPrice());
        printer.printNewLine();
        printer.printBarcode("GTIN-13", gtin13.toString());
        printer.printNewLine();
        printer.printFooter();

    }
}
