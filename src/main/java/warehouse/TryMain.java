package warehouse;

import warehouse.model.Product;
import warehouse.utility.ReportingImpl;

import static warehouse.business_logic.WarehouseManagement.addProduct;

public class TryMain {
    public static void main(String[] args) {
//        OrderProcessing.readOrdersFromJsonFile();
//
//        Product productToBeShipped = new Product("plier", 3.5, 1);
//        Customer customer = new Customer("Shopaddict", "user", "customer",
//                "0786869564", "Horea st. 15/7", "shopaddict@email.com");
//        Payment onDeliveryPayment = new Payment(productToBeShipped);
//        LocalDateTime now = LocalDateTime.now();
//
////        Order orderToBeProcessed = new Order(productToBeShipped, customer, onDeliveryPayment, now);
//
//
////        OrderProcessing.orders.add(orderToBeProcessed);
//
//        OrderProcessing.writeToJsonFile(OrderProcessing.orders, "src/main/java/warehouse/data_access/orders.json");

        Product plier = new Product("plier", 3.5, 99, 100);
        Product scissor = new Product("scissor", 3, 189, 200);
        Product clipper = new Product("clipper", 1.5, 70, 100);
        Product tweezer = new Product("tweezer", 1, 150, 150);
        Product pincer = new Product("pincer", 3, 50, 50);
        Product cutter = new Product("cutter", 2, 150, 150);

        addProduct(plier);
        addProduct(scissor);
        addProduct(clipper);
        addProduct(tweezer);
        addProduct(pincer);
        addProduct(cutter);

        ReportingImpl reporting = new ReportingImpl();
        reporting.underStock();
    }
}
