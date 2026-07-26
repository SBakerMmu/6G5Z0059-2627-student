package bridgeproduct;

import valueobjects.Price;

interface ProductPrinter {
    void printHeader();
    void printString(String string);
    void printPrice(Price price);
    void printNewLine();
    void printBarcode(String format,String code );
    void printFooter();
}
