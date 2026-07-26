package infrastructure.driving;

import applicationcode.Region;
import applicationcode.Provided;

import java.util.Scanner;

 public class ShippingCostCliAdapter {
    private final Provided shippingCostCalculator;

    public ShippingCostCliAdapter(Provided shippingCostCalculator) {
        this.shippingCostCalculator = shippingCostCalculator;
    }

    public void run() {

        Scanner scanner = new Scanner(System.in); // Create a Scanner object

        System.out.format("Select a region to ship to (UK, EUR, ROW): %n");
        String region = scanner.next();
        System.out.print("Enter the weight of the package in kg: ");
        double weight = scanner.nextDouble();

        switch (region.toLowerCase()) {
            case "uk":
                System.out.format("Shipping cost to %s: %f%n", region, shippingCostCalculator.calculate(Region.UK, weight));
                break;
            case "eur":
                System.out.format("Shipping cost to %s: %f%n", region, shippingCostCalculator.calculate(Region.EUR, weight));
                break;
            case "row":
                System.out.format("Shipping cost to %s: %f%n", region, shippingCostCalculator.calculate(Region.ROW, weight));
                break;
            default:
                System.out.println("Shipping to this region is not available.");
        }
        scanner.close(); // Close the scanner
    }
}
