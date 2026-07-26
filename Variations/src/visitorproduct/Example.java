package visitorproduct;

import valueobjects.ASIN;
import valueobjects.GTIN13;
import valueobjects.InvalidException;
import valueobjects.Price;

public final class Example {

    public static void run() {



        //Using the visitor pattern the two concrete Printer implementations are in control of the format of a Bag label and Price ticket using information from different Product types
        //However adding a new Product type would require changing the Printer interface and all its implementations

        Price price = new Price(100.0d);
        BagLabelPrinter bagLabelPrinter = new BagLabelPrinter();
        PriceTicketPrinter priceTicketPrinter = new PriceTicketPrinter();

        try {
            print(new AmazonProduct(ASIN.parse("B09P4L33SW"), price), bagLabelPrinter);
            print(new AmazonProduct(ASIN.parse("B09P4L33SW"), price), priceTicketPrinter);
            print(new RetailProduct(GTIN13.parse("705353", "135569"), price), bagLabelPrinter);
            print(new RetailProduct(GTIN13.parse("705353", "135569"), price), priceTicketPrinter);
        } catch (InvalidException e) {
            System.out.println("Invalid: " + e.getMessage());
        }
    }

    private static void print(Product product, ProductVisitor visitor) {
        product.accept(visitor);
    }

}
