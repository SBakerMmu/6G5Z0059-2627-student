package bridgeproduct;

import valueobjects.ASIN;
import valueobjects.GTIN13;
import valueobjects.InvalidException;
import valueobjects.Price;

public final class Example {

    public static void run() {


        //The two concrete Product implementations are in control of the format of a Bag label and Price ticket
        //New Printer implementations can be added without changing the Product classes and new Product types can be added without changing the Printer classes
        Price price = new Price(100.0d);
        BagLabelPrinter bagLabelPrinter = new BagLabelPrinter();
        PriceTicketPrinter priceTicketPrinter = new PriceTicketPrinter();

        try {
            print(new AmazonProduct(ASIN.parse("B09P4L33SW"), price, bagLabelPrinter));
            print(new AmazonProduct(ASIN.parse("B09P4L33SW"), price, priceTicketPrinter));
            print(new RetailProduct(GTIN13.parse("705353", "135569"), price, bagLabelPrinter));
            print(new RetailProduct(GTIN13.parse("705353", "135569"), price, priceTicketPrinter));
        } catch (InvalidException e) {
            System.out.println("Invalid: " + e.getMessage());
        }
    }


    private static void print(Product product) {
        product.print();
    }

}
