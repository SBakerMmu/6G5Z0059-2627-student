package infrastructure.driving;

import applicationcode.Region;
import applicationcode.Provided;
import applicationcode.ShippingCost;

import java.util.Scanner;

 public class ShippingCostAdministrationCliAdaptor {
    private final Provided shippingCostAdministrator;

    public ShippingCostAdministrationCliAdaptor(Provided shippingCostAdministrator) {
        this.shippingCostAdministrator = shippingCostAdministrator;
    }

    public void run() {

        Scanner scanner = new Scanner(System.in); // Create a Scanner object

        for (Region region : Region.values()) {
            System.out.format("Enter minCharge for: %s%n", region);
            double minCharge = scanner.nextDouble();
            System.out.format("Enter the cost per kg for: %s%n", region);
            double cost = scanner.nextDouble();
            shippingCostAdministrator.addShippingCost(region, minCharge, cost);
            for (ShippingCost shippingCost: shippingCostAdministrator.getAllShippingCosts()){
                System.out.format("%s%n",shippingCost);
            }
        }
        scanner.close(); // Close the scanner
    }
}
