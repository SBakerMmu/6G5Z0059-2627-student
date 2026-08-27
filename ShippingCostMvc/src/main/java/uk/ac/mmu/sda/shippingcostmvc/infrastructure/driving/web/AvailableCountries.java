package uk.ac.mmu.sda.shippingcostmvc.infrastructure.driving.web;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import uk.ac.mmu.sda.shippingcostmvc.applicationcode.usecase.listavailablecountries.Provided;

import java.util.SortedSet;
import java.util.TreeSet;


//A ControllerAdvice component runs before each request, so we can centralise the code that sets countries into the model
//Here we are mapping the unsorted Use Case Country to Country DTO that implements the Comparable interface and therefore can be sorted
@ControllerAdvice
public class AvailableCountries {

    private final Provided listAvailableCountries;

    public AvailableCountries(Provided listAvailableCountries){
        this.listAvailableCountries = listAvailableCountries;
    }

    @ModelAttribute
    public void listAvailableCountries(Model model) {
        SortedSet<Country> countries = new TreeSet<>();
        listAvailableCountries.list().forEach( country -> countries.add(new Country(country.code(),country.name())));
        model.addAttribute("countries", countries);
    }
}
