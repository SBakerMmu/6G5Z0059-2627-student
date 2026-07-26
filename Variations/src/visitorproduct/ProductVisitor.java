package visitorproduct;

import valueobjects.Price;

interface ProductVisitor {
    void visit(AmazonProduct product);
    void visit(RetailProduct product);
}
