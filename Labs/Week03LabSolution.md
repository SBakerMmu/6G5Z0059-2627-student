# Software Design and Architecture Week 3 Lab Solutions

Suggested answers to the Week 3 Lab Exercises

# Evaluate the design of the Polymorphic selling price product

We asked you to evaluate the design of the Polymorphic Selling Price product.

The key design element is representing the concept of a SellingPrice as an interface. The interface is implemented by two different Value Objects, one representing a Full Price and one representing a Discounted Price. The Product holds a reference of abstract `SellingPrice`, which is either a Full Price or a Discounted Price depending on if we had applied or removed a discount. Note how all the calculation and validation logic exists in the relevant ValueObject classes.

**Q)** We asked is wrong with the design of the Product class?, specifically the getPrice() method?

**A)** The getPrice method has reverted back to returning a primitive `double`. It would be better if the getPrice returned another ValueObject. For example, we could create another ValueObject class called 'ProductPrice'.

```Java
class ProductPrice
{
    final private double price;
    //Constructor is package-private as we want to restrict the creation of ProductPrice objects to the Product class
    ProductPrice(SellingPrice price) {

        this.price = price.get();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof ProductPrice other) {
            return price == other.get();
        } else return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(price);
    }

    @Override
    public String toString() {
        return "ProductPrice{" +
                "price=" + price +
                '}';
    }
    public double get() {
        return price;
    }
}
```
We could extend our design to include other ValueObject classes that represent a `ShippingCost`, and `TotalPrice` ValueObject that keeps both the ProductPrice and ShippingCost together.

```Java
public class ShippingCost {

    public final static ShippingCost Zero = new ShippingCost();

    private final double cost;

    private ShippingCost() {
        this(0);
    }

    public ShippingCost(double cost) {
        if( cost < 0) {
            throw new IllegalArgumentException("Shipping cost cannot be negative");
        }
        this.cost = cost;
    }

    public double get() {
        return cost;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ShippingCost that = (ShippingCost) o;
        return Double.compare(cost, that.cost) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cost);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ShippingCost{");
        sb.append("cost=").append(cost);
        sb.append('}');
        return sb.toString();
    }
}
```
And then create a TotalPrice ValueObject that brings together the ProductPrice and ShippingCost

```Java
class TotalPrice
{
    final private ProductPrice productPrice;
    final private ShippingCost shippingCost;
    private final double totalPrice;

    //Constructor is package-private as we want to restrict the creation of ProductPrice objects to the Product class
    TotalPrice(ProductPrice productPrice, ShippingCost shippingCost) {
        this.productPrice = productPrice;
        this.shippingCost = shippingCost;
        //because product price and shipping cost are immutable, we can safely sum them once
        totalPrice = productPrice.get() + shippingCost.get();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof TotalPrice other) {
            return totalPrice == other.get();
        } else return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(totalPrice);
    }

    @Override
    public String toString() {
        return "TotalPrice{" +
                "price=" + totalPrice +
                '}';
    }
    public double get() {
        return totalPrice;
    }

    public ProductPrice getProductPrice() {
        return productPrice;
    }

    public ShippingCost getShippingCost() {
        return shippingCost;
    }
}
```
The updated Product class

```Java
class Product {

    private final MinimumPrice minimumPrice;
    private final ShippingCost shippingCost;
    private SellingPrice sellingPrice;

    public Product(FullPrice fullPrice, MinimumPrice minimumPrice, ShippingCost shippingCost) {
        this.sellingPrice = fullPrice;
        this.minimumPrice = minimumPrice;
        this.shippingCost = shippingCost;
    }

    public void applyDiscount(Discount discount) {

        sellingPrice = sellingPrice.applyDiscount(minimumPrice, discount);
    }

    public void removeDiscount() {

        sellingPrice = sellingPrice.removeDiscount();
    }

    public TotalPrice getTotalPrice() {
        return new TotalPrice(new ProductPrice(sellingPrice), shippingCost);
    }
}
```

## Implement a Strategy Pattern for Calculating Shipping Costs

We provided you with some starter code of a Product class and a Basket class, representing a basket in an ecommerce checkout process.

The lab task was to replace the Destination enum parameter with a Strategy Pattern, implementing 3 concrete strategies representing UK Shipping, Europe Shipping and Rest of World Shipping.

The code to calculate the shipping charge is in the Basket class and is based on a Destination enum.

```java


enum Destination {
    UK,
    Europe,
    RestOfWorld
}
```
```java
import java.util.ArrayList;
import java.util.List;

class Basket {
    private final List<Product> products = new ArrayList<>();
    private final Destination shipTo;


    public Basket(Destination shipTo) {
        this.shipTo = shipTo;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    private double totalWeight()
    {
        double weight = 0.0d;
        for(Product product: products)
        {
            weight += product.getWeight();
        }
        return weight;
    }

    public double getShippingCharge()
    {
        return switch( shipTo) {
            case UK -> 0.0d; //Free Shipping in UK
            case Europe -> totalWeight() * 1.25; //£1.25 per Kg
            default -> switch (products.size()) //Rest of the World
                {
                    case 0 -> 0.0d;
                    default -> Math.max(10.00d, totalWeight() * 5.50); // higher of £10.00 or 5.50 per Kg
                };
        };
    }

}
```

The Basket is created with a Destination and calculates the shipping charge in the getShippingCharge() method. There are currently 3 different destination based algorithms for calculating shipping.

> An **algorithm** is the term given to any sequence of steps to solve a problem, make a decision or calculate a value.

It might look perfectly sensible, but there are several design issues with the code that could cause maintenance issues later.
1)	All the algorithm code to calculate shipping is in the Basket class, which means that if any part of any calculation needs to change, I must change the Basket class, which is wrong because the role of the Basket class is to hold and manage a list of products.
2)	The only thing that I can use to vary the shipping charge is the destination – there is nothing in the Basket class for example that would differentiate between normal or next day shipping.
3)	If I want to add a new shipping method, for example Europe Next Day shipping then I must change the Basket class again.

All these issues arise because we have mixed up responsibilities – the Basket class is responsible for managing lists of products AND calculating shipping. Calculating shipping has got nothing to do with adding and removing products. Worse, the shipping calculation is highly likely to change on a regular basis (changes to charges, adding or replacing shipping options).

We solve the design problem by the shipping calculations (algorithms) and encapsulating them behind an interface into their own classes. There is a family of shipping calculations (UK, Europe and ROW at time of writing) so we define a common interface.

```java
public interface ShippingCostStrategy {
    ShippingCost calculate (List<Product> products);
}
```
The Basket code now only needs to be concerned with adding and removing products. We remove the Destination from the Basket and replace it with the ShippingCostStrategy. This makes the Basket class much simpler. We also create a Value Object class called ShippingCost to represent the actual cost.

```java
public class ShippingCost {
    public final static ShippingCost Zero = new ShippingCost();
    private final double cost;
    private ShippingCost() {
        this(0);
    }
    public ShippingCost(double cost) {
        this.cost = cost;
    }
    public double getCost() {
        return cost;
    }
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ShippingCost that = (ShippingCost) o;
        return Double.compare(cost, that.cost) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cost);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ShippingCost{");
        sb.append("cost=").append(cost);
        sb.append('}');
        return sb.toString();
    }
}
```

```java
public class BasketWithShippingStrategy {
    private final List<Product> products = new ArrayList<>();
    private final ShippingCostStrategy shippingCostStrategy;
    public BasketWithShippingStrategy(ShippingCostStrategy shippingCostStrategy) {
        this.shippingCostStrategy = shippingCostStrategy;
    }
    public void addProduct(Product product) {
        products.add(product);
    }
    public void removeProduct(Product product) {
        products.remove(product);
    }
    public ShippingCost getShippingCharge()
    {
        return shippingCostStrategy.calculate(products);
    }
}
```

Note how the Basket no longer needs to know about Destination, we can provide a ShippingCost strategy based on anything we like.

Each shipping cost algorithm goes into its own class. For example, the UK strategy just returns 0.

```java

public class UKShippingStrategy implements ShippingCostStrategy {
    @Override
    public ShippingCost calculate(List<Product> products) {
        return  ShippingCost.Zero;
    }
}
```

Both Europe and ROW strategies are currently based on total weight of product. We can pull the total weight calculation up into a base class.

```java
abstract class WeightBasedShippingStrategy implements ShippingCostStrategy {
    protected static double totalWeight(List<Product> products) {
        double weight = 0.0d;
        for (Product product : products) {
            weight += product.getWeight();
        }
        return weight;
    }
}
````
The two concrete implementations extend the base class and implement the interface.
```java

public class EuropeShippingStrategy extends WeightBasedShippingStrategy  {
    private final static double CHARGE_PER_KG = 1.25d;

    @Override
    public ShippingCost calculate(List<Product> products) {
        return  new ShippingCost(totalWeight(products) * CHARGE_PER_KG);
    }
}
```

```java
public class RowShippingStrategy extends WeightBasedShippingStrategy  {
    private final static double NO_COST = 0.0d;
    private final static double MIN_CHARGE = 10.0d;
    private final static double CHARGE_PER_KG = 5.5d;

    @Override
    public ShippingCost calculate(List<Product> products) {
        double cost = products.size() == 0.0d ? NO_COST : Math.max(MIN_CHARGE, totalWeight(products) * CHARGE_PER_KG); // higher of MIN_CHARGE or CHARGE_PER_KG
        return new ShippingCost(cost);
    }
}
```

Example usage
```java

BasketWithShippingStrategy basketWithShippingStrategy = new BasketWithShippingStrategy(new RowShippingStrategy());
basketWithShippingStrategy.addProduct(book1);
System.out.format("Shipping %f%n", basketWithShippingStrategy.getShippingCharge());
```

### Summary

In a real Ecommerce system, we will still need some method of asking the user which shipping method they want and instantiating the correct concrete implementation class to pass into the Basket, and we will look at some design patterns that help us do that later, but for now a summary of what we have accomplished using the Strategy pattern.

- We have reduced the number of responsibilities the Basket class has from two (manage list of products, calculate shipping charge) to one.
- We have simplified the Basket class because it’s doing less.
- We have created independent implementations of ShippingCost strategy in separate classes. If we need change an implementation, we just update that class.
- We can create many different concrete implementations of a ShippingCost strategy for any kind of business reason, and the Basket class does not need to change.

- Each concrete implementation of a ShippingCost can be independently tested and validated.

"Define a family of algorithms, encapsulate each one, and make them interchangeable. Strategy lets the algorithm vary independently of classes that use it." (Gamma et al. 1994 Ch5).

The Strategy pattern is one of the most useful design patterns, it simplifies code by taking algorithmic code out of a class and putting into its own class encapsulated behind and abstract interface. Getting the client code to make calls to the abstract interface means that we can choose the concrete implementation at runtime and put the code which chooses the concrete implementation somewhere else.

# Using a Strategy Pattern to select which player has a turn in a board game

If we are playing board game with more than one player, we need to determine which player has the next turn. Here are some examples of strategy that returns the next player.

First we define a Player

```Java
class Player {
    private final String color;
    private final String name;

    Player(String name, String color) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(color);
        this.name = name;
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public String getName() {
        return name;
    }
}
```

The PlayerSelector interface (this is the interface to our variable strategy).

```java
interface PlayerSelector {
    int size();
    Player next();
}
```

We can implement a standard round-robin selector using the order that players were given to the selector.

```java
class ForwardSelector implements PlayerSelector {
    private final Player[] players;
    private int index = 0;

    public ForwardSelector(Player[] players) {

        this.players = Arrays.copyOf(players, players.length);
    }

    @Override
    public int size() {
        return players.length;
    }

    @Override
    public Player next() {
        index = index % players.length; //reset index back to 0 before use if it goes above the length of the array using the % operator
        return players[index++];
    }
}
```

We can then implement a reverse order version

```java
class ReverseSelector implements PlayerSelector {
    private final Player[] players ;
    private int index;
    private final int lastIndex;

    public ReverseSelector(Player[] players) {
        this.players = Arrays.copyOf(players, players.length);
        index = lastIndex =  this.players.length - 1;

    }

    @Override
    public int size() { return players.length; }

    @Override
    public Player next() {
        //reset index back to the end of the array if it goes below 0 before use;
        index = (index >= 0) ? index : lastIndex;
        return players[index--];
    }
}

```

or even a Random version

```Java
class RandomSelector implements PlayerSelector {
    private final List<Player> players ;
    private Random random = new Random();

    public RandomSelector(Player... players) {
        this.players = Arrays.asList(players);
    }

    @Override
    public int size() {
        return players.size();
    }

    @Override
    public Player next() {
        return players.get(random.nextInt(0, size()));
    }
}
```
> ⚠ There is a lot of duplicated code in the above examples, consider how you might generalise these implementations.

All these different strategy implementation can be used by the same client code

```Java


void play(PlayerSelector selector) {
    for(int i = 0; i < selector.size(); i++){
        Player player = selector.next();
        System.out.format("%s%n", player.getName());
    }
}
```
The family of algorithms here is the way we decide which player has the next turn. We can create many different algorithms to select the next player, and hide them behind the PlayerSelector interface. We can then decide on the method of choosing next player at runtime by selecting different concrete implementations of the PlayerSelector interface.

