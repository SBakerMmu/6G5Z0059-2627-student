package visitorproduct;


import valueobjects.GTIN13;
import valueobjects.Price;

class RetailProduct extends Product {
    private final GTIN13 gtin13;

    public RetailProduct(GTIN13 gtin13, Price price) {
        super(price);
        this.gtin13 = gtin13;
    }

    public GTIN13 getGtin() {
        return gtin13;
    }

    @Override
    public String toString() {
        return gtin13.toString();
    }

    @Override
    public void accept(ProductVisitor visitor) {
        visitor.visit(this);
    }
}
