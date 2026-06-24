import applicationcode.Provided;
import applicationcode.Region;
import applicationcode.Required;
import infrastructure.driving.ShippingCostCliAdapter;
import infrastructure.driven.ShippingCostDatabase;
import infrastructure.driven.ShippingCostDatabaseAdapter;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class SoftwareProduct {

    public static void main(String[] args) {
        ShippingCostDatabase shippingCostDatabase = new ShippingCostDatabase(); // Create an instance of ShippingCostDatabase
        //Populate the database with some initial data
        shippingCostDatabase.regions.add(Region.UK);
        shippingCostDatabase.regions.add(Region.EUR);
        shippingCostDatabase.regions.add(Region.ROW);
        shippingCostDatabase.costPerKgMap.put(Region.UK, 0.0);
        shippingCostDatabase.costPerKgMap.put(Region.EUR, 1.25d);
        shippingCostDatabase.costPerKgMap.put(Region.ROW, 5.5d);
        shippingCostDatabase.minChargeMap.put(Region.UK, 0.0);
        shippingCostDatabase.minChargeMap.put(Region.EUR, 0.0);
        shippingCostDatabase.minChargeMap.put(Region.ROW, 10.0);

        Required shippingCostDatabaseAdapter = new ShippingCostDatabaseAdapter(shippingCostDatabase); // Create an adapter for the database
        Provided shippingCostCalculator = Provided.create(shippingCostDatabaseAdapter); // Create an instance of Provided with the shipping cost database
        ShippingCostCliAdapter cli = new ShippingCostCliAdapter(shippingCostCalculator); // Create an instance of the CLI
        cli.run();
    }






}
