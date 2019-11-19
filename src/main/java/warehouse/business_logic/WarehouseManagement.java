package warehouse.business_logic;

import warehouse.model.Product;

import java.util.Scanner;

import static warehouse.data_access.Products.products;

public class WarehouseManagement {

    public static final Scanner scanner = new Scanner(System.in);

    public static void addProduct(Product product) {
        products.add(product);
    }

    public static void removeProduct(Product product) {
        products.remove(product);
    }

    public static void print(String txt) {
        System.out.println(txt);
    }

    public static char getOptionFromInput() {
        return scanner.next().charAt(0);
    }

    public static void printProducts() {
        for (Product product : products) {
            System.out.println(product);
        }
    }

    public static Product existingProductFromInput() {
        printProducts();
        print("Enter the name of the product you'd like to select:");
        String productNameFromInput = scanner.next();
        Product existingProduct = new Product();
        for (Product product : products) {
            if (product.getName().equals(productNameFromInput)) {
                existingProduct = product;
            }
        }
        return existingProduct;
    }

    public static Product constructProductFromInput() {
        Product product = new Product();
        print("Enter product's name:");
        product.setName(scanner.next());
        print("Enter product's price");
        product.setPrice(scanner.nextDouble());
        print("Enter number of items:");
        product.updateItems(scanner.nextInt());
        return product;
    }
}
