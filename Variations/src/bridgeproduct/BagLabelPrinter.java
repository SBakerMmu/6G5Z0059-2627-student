package bridgeproduct;

import valueobjects.Price;

class BagLabelPrinter extends AbstractPrinter {

    private static final String PRINTER_TYPE  = "BAG LABEL";
    @Override
    public void printHeader() {
        printHeader(PRINTER_TYPE);
    }

    @Override
    public void printFooter() {
        printFooter(PRINTER_TYPE);
    }

}
