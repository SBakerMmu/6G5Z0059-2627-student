package bridgeproduct;

import valueobjects.Price;

public abstract class Product {
    private final Price price;
    private final ProductPrinter printer;

    protected Product(Price price, ProductPrinter printer) {
        this.price = price;
        this.printer = printer;
    }

    public ProductPrinter getPrinter() {
        return printer;
    }

    public Price getPrice() {
        return price;
    }

    public abstract void print();

}
