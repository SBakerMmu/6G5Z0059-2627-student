package visitorproduct;


import valueobjects.ASIN;
import valueobjects.Price;

class AmazonProduct extends Product {
    private final ASIN asin;

    public AmazonProduct(ASIN asin, Price price) {
        super(price);
        this.asin = asin;
    }

    public ASIN getAsin() {
        return asin;
    }


    @Override
    public String toString() {
        return asin.toString();
    }

    @Override
    public void accept(ProductVisitor visitor) {
        visitor.visit(this);
    }
}
