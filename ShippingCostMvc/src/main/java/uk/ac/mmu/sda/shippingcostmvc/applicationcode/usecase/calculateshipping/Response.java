package uk.ac.mmu.sda.shippingcostmvc.applicationcode.usecase.calculateshipping;

public record Response(Country country, double weight, double cost) {

    //Package private  constructor that uses the request object
    Response(Request request, Country country, double cost) {
        this( country, request.weight(), cost);
    }

}

