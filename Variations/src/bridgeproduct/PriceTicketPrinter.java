package bridgeproduct;

import valueobjects.Price;

class PriceTicketPrinter extends AbstractPrinter {

    private static final String PRINTER_TYPE  = "PRICE TICKET";
    @Override
    public void printHeader() {
        printHeader(PRINTER_TYPE);
    }

    @Override
    public void printFooter() {
        printFooter(PRINTER_TYPE);
    }

}
