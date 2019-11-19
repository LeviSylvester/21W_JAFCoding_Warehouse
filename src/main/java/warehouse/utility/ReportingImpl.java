package warehouse.utility;

import warehouse.business_logic.OrderProcessing;
import warehouse.model.Product;

import java.util.*;

import static warehouse.business_logic.WarehouseManagement.getOptionFromInput;
import static warehouse.business_logic.WarehouseManagement.print;
import static warehouse.data_access.Products.products;

public class ReportingImpl implements Reporting {

    @Override
    public void underStock() {
        List<Product> underStock = new ArrayList<>();
        for (Product p : products) {
            if (p.getItems() < p.getMaxItems()) {
                underStock.add(p);
            }
        }
        OrderProcessing.writeUnderStockToJsonFile(underStock, "src/main/java/warehouse/data_access/orders.json");
    }

    @Override
    public void totals() {
        int countProducts = 0;
        long countItems = 0;
        double countPrice = 0;
        for (Product p : products) {
            countProducts++;
            countItems += p.getItems();
            countPrice += p.getItems() * p.getPrice();
        }
        print("There are a number of " + countProducts + " products in our warehouse,");
        print("with a total of " + countItems + " items, summing up to " + countPrice + "$.");
    }

    @Override
    public void filterByName() {
        products.sort(new Comparator<Product>() {//todo: work on separate list
            //toDo: foreach alphabetical interval
            @Override
            public int compare(Product p1, Product p2) {
                if (p1.getName().compareTo(p2.getName()) > 0) {
                    return -1;
                } else if (p1.getName().compareTo(p2.getName()) < 0) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
    }

    @Override
    public void filterByPrice() {//toDo: work on separate list
        //toDo: foreach price interval
        //
        products.sort(new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                if (p1.getPrice() > p2.getPrice()) {
                    return -1;
                } else if (p1.getPrice() < p2.getPrice()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
    }

    @Override
    public void filterProducts() {
        print("Filter by n  name or p  price, enter you option:");
        switch (getOptionFromInput()) {
            case 'n':
                filterByName();
                print("" + products);
                break;
            case 'p':
                filterByPrice();
                print("" + products);
                break;
            default:
                print("Not a valid input!");
        }

    }

    @Override
    public void emailReports() {
    }


}
