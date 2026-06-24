package visitorproduct;


class BagLabelPrinter extends AbstractPrinter {

    private static final String PRINTER_TYPE = "BAG LABEL";
    @Override
    public void visit(AmazonProduct product) {
        printHeader(PRINTER_TYPE);
        printString("ASIN: ");
        printString(product.getAsin().toString());
        printString(" ");
        printPrice(product.getPrice());
        printNewLine();
        printFooter(PRINTER_TYPE);
    }

    @Override
    public void visit(RetailProduct product) {
        printHeader(PRINTER_TYPE);
        printString("GTIN: ");
        printString(product.getGtin().toString());
        printString(" ");
        printPrice(product.getPrice());
        printNewLine();
        printFooter(PRINTER_TYPE);

    }
}
