package warehouse.security;

import warehouse.model.Product;

public class Payment {
    private double payment;
    private double transportationPayment = 5; //let's say that we have a unique transportation price for now

    public Payment(Product product) {
        this.payment = product.getTotalPrice() + transportationPayment;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public double getTransportationPayment() {
        return transportationPayment;
    }

    public void setTransportationPayment(double transportationPayment) {
        this.transportationPayment = transportationPayment;
    }


}
