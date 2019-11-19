package warehouse.model;

import warehouse.business_logic.WarehouseManagement;

import static warehouse.business_logic.WarehouseManagement.getOptionFromInput;
import static warehouse.business_logic.WarehouseManagement.print;

public class Product {
    private String name;
    private double price = 0;
    private int items = 0;
    private int maxItems = 0;

    public Product() {
    }

    public Product(String name, double price, int items, int maxItems) {
        this.name = name;
        this.price = price;
        this.items = items;
        this.maxItems = maxItems;
    }

    @Override
    public String toString() {
        return name + " " + price + "$ x" + items;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }
    public double getTotalPrice() {
        return price*items;
    }



    public void setPrice(double price) {
        this.price = price;
    }

    public int getItems() {
        return items;
    }

    public void setItems(int items) {
        this.items = items;
    }

    public int getMaxItems() {
        return maxItems;
    }

    public void setMaxItems(int maxItems) {
        this.maxItems = maxItems;
    }

    public void updateItems(int items) {
        this.items += items;
    }

    public static void updateProduct(Product product) {
        boolean cancel = false;
        if (product.getName() != null) {
            while (!cancel){
                print("Select a) price, b) items or r) return to Menu:");
                switch (getOptionFromInput()) {
                    case 'a':
                        print("price: ");
                        product.setPrice(WarehouseManagement.scanner.nextDouble());
                        System.out.println(product);
                        break;
                    case 'b':
                        print(product.getName() + "s: ");
                        product.updateItems(WarehouseManagement.scanner.nextInt());
                        System.out.println(product);
                        break;
                    case 'r':
                        cancel = true;
                        break;
                    default:
                        print("Invalid input, try again!");
                }
            }
        } else {
            System.out.println((char)27 + "[31m" + "That product doesn't exist."); //"Esc[31m" colours text in red
        }
    }
}
