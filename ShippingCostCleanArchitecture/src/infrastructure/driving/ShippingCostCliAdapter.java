package infrastructure.driving;



import java.util.Set;
import java.util.Scanner;
import java.util.stream.Collectors;

//Here we need to fully qualify the usecase interfaces to disambiguate the Provided interfaces
public class ShippingCostCliAdapter {
    private final applicationcode.usecase.listavailablecountries.Provided listAvailableCountries;
    private final applicationcode.usecase.calculateshipping.Provided calculateShipping;


    public ShippingCostCliAdapter( applicationcode.usecase.listavailablecountries.Provided listAvailableCountries, applicationcode.usecase.calculateshipping.Provided calculateShipping) {

        this.listAvailableCountries = listAvailableCountries;
        this.calculateShipping = calculateShipping;
    }

    public void run(){
        System.out.format("Calculate Shipping using calculate method:%n");
        Scanner scanner = new Scanner(System.in); // Create a Scanner object
        Set<String> availableCountries = listAvailableCountries.list();
        System.out.format("Select a country to ship to (%s): ", availableCountries.stream().collect(Collectors.joining(",")));
        String country = scanner.next();
        System.out.print("Enter the weight of the package in kg: ");
        double weight = scanner.nextDouble();
        System.out.format("Shipping cost to %s: %f%n", country, calculateShipping.calculate(country,weight));
    }
}
