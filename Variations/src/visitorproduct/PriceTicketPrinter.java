package visitorproduct;

import valueobjects.Price;

class PriceTicketPrinter extends AbstractPrinter {

    private static final String PRINTER_TYPE = "PRICE TICKET";
        @Override
    public void visit(AmazonProduct product) {
        printHeader(PRINTER_TYPE);
        printString("ASIN: ");
        printString(product.getAsin().toString());
        printNewLine();
        printPrice(product.getPrice());
        printNewLine();
        printBarcode("ASIN:", product.getAsin().toString());
        printNewLine();
        printFooter(PRINTER_TYPE);
    }

    @Override
    public void visit(RetailProduct product) {
        printHeader(PRINTER_TYPE);
        printString("GTIN: ");
        printString(product.getGtin().toString());
        printNewLine();
        printPrice(product.getPrice());
        printNewLine();
        printBarcode("GTIN:", product.getGtin().toString());
        printNewLine();
        printFooter(PRINTER_TYPE);

    }
}
