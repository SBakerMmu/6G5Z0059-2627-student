package visitorproduct;

import valueobjects.Price;

public abstract class Product {

    private final Price price;


    protected Product(Price price) {
        this.price = price;
    }

    public Price getPrice() {
        return price;
    }
    public abstract void accept(ProductVisitor visitor);

}
