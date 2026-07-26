import applicationcode.Provided;
import applicationcode.ShippingCostRepository;
import infrastructure.driven.ShippingCostRepositoryAdapter;
import infrastructure.driving.ShippingCostAdministrationCliAdaptor;
import infrastructure.driven.ShippingCostDatabase;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class SoftwareProduct {

    public static void main(String[] args) {
        ShippingCostDatabase shippingCostDatabase = new ShippingCostDatabase(); // Create an instance of ShippingCostDatabase
        ShippingCostRepository shippingCostDatabaseAdapter = new ShippingCostRepositoryAdapter(shippingCostDatabase); // Create an adapter for the database
        Provided shippingCostCalculator = Provided.create(shippingCostDatabaseAdapter); // Create an instance of Provided with the shipping cost database
        ShippingCostAdministrationCliAdaptor cli = new ShippingCostAdministrationCliAdaptor(shippingCostCalculator); // Create an instance of the CLI
        cli.run();
    }






}
