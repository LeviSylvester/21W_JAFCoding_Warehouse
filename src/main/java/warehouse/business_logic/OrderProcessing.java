package warehouse.business_logic;

import com.fasterxml.jackson.databind.ObjectMapper;
import warehouse.model.Order;
import warehouse.model.Product;
import warehouse.security.Payment;
import warehouse.security.Security;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static warehouse.business_logic.WarehouseManagement.print;
import static warehouse.business_logic.WarehouseManagement.scanner;
import static warehouse.data_access.Products.products;

public class OrderProcessing {

    public static List<Order> orders = new ArrayList<>();

    public static void readOrdersFromJsonFile(){
        ObjectMapper objectMapper = new ObjectMapper();

        try {

            String input = new String(Files.readAllBytes(Paths.get("src/main/java/warehouse/data_access/orders.json")));

            orders = objectMapper
                    .readValue(input, objectMapper.getTypeFactory()
                            .constructCollectionType(List.class, Order.class));
        } catch (IOException e) {
            System.out.println("Reading form file failed");
        }
    }

    public static void writeToJsonFile(List<Order> toWrite, String pathname) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
//            objectMapper.writeValue(new File("src/main/java/warehouse/data_access/orders.json"), product);
            objectMapper.writeValue(new FileWriter(pathname), toWrite);
        } catch (Exception e) {
            System.out.println("Writing to json file failed.");
        }
//        try (FileWriter file = new FileWriter("src/main/java/warehouse/data_access/orders.json")) {
//            file.write(product.toString());
//        } catch (Exception e) {
//            System.out.println("Writing to json file failed.");
//        }
    }

    public static void writeUnderStockToJsonFile(List<Product> understock, String pathname) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
//            objectMapper.writeValue(new File("src/main/java/warehouse/data_access/orders.json"), product);
            objectMapper.writeValue(new FileWriter(pathname), understock);
        } catch (Exception e) {
            System.out.println("Writing to json file failed.");
        }
    }

    public static void orderProduct(Product productToBeShipped) {
        OrderProcessing.readOrdersFromJsonFile();
        print("Enter no of items:");
        int noOfItems = scanner.nextInt();
        productToBeShipped.setItems(noOfItems);
        Payment onDeliveryPayment = new Payment(productToBeShipped);
        print("Your payment of " + onDeliveryPayment.getPayment() +
                "$ and transportation of " + onDeliveryPayment.getTransportationPayment() +
                "$ is expected on delivery.");
        LocalDateTime now = LocalDateTime.now();
//        Order orderToBeProcessed = new Order(productToBeShipped, Security.login(), onDeliveryPayment, now);
        Order orderToBeProcessed = new Order(productToBeShipped, Security.login());
        OrderProcessing.orders.add(orderToBeProcessed);
        for (Product product : products) {
            if (productToBeShipped.equals(product)){
                product.updateItems(-noOfItems);
            }
        }
        OrderProcessing.writeToJsonFile(OrderProcessing.orders, "src/main/java/warehouse/data_access/orders.json");
        print("Order has been placed successfully.");
    }
}
