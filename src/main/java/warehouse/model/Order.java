package warehouse.model;

public class Order {
    private Product product;
    private Customer customer;
//    private Payment onDeliveryPayment;
//    private LocalDateTime orderedOn;

    public Order() {
    }

    public Order(Product product, Customer customer/*, Payment onDeliveryPayment, LocalDateTime orderedOn*/) {
        this.product = product;
        this.customer = customer;
//        this.onDeliveryPayment = onDeliveryPayment;
//        this.orderedOn = orderedOn;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

//    public Payment getOnDeliveryPayment() {
//        return onDeliveryPayment;
//    }
//
//    public void setOnDeliveryPayment(Payment onDeliveryPayment) {
//        this.onDeliveryPayment = onDeliveryPayment;
//    }

//    public LocalDateTime getOrderedOn() {
//        return orderedOn;
//    }
//
//    public void setOrderedOn(LocalDateTime orderedOn) {
//        this.orderedOn = orderedOn;
//    }

    @Override
    public String toString() {
        return "Order{" +
                "product=" + product.getName() + product.getItems() +
                ", customer=" + customer.getName() + " tel." + customer.getPhone() + ", " + customer.getAddress() +
//                ", onDeliveryPayment=" + onDeliveryPayment + "$" +
//                ", orderedOn=" + orderedOn +
                '}';
    }
}
