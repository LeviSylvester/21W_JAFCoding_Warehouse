package warehouse;

import warehouse.model.Admin;
import warehouse.model.Customer;
import warehouse.model.Order;
import warehouse.model.Product;
import warehouse.utility.ReportingImpl;

import static warehouse.business_logic.OrderProcessing.*;
import static warehouse.business_logic.WarehouseManagement.*;
import static warehouse.data_access.Users.addUser;
import static warehouse.data_access.Users.signUp;
import static warehouse.model.Product.updateProduct;
import static warehouse.security.Login.login;

public class Main {//"src/main/java/warehouse/data_access/orders.json"

    public static void main(String[] args) {
        Admin admin = new Admin("Levi", "root", "admin");
        Customer customer = new Customer("Shopaddict", "user", "customer",
                "0786869564", "Horea st. 15/7", "shopaddict@email.com");
        addUser(admin);
        addUser(customer);

        Product plier = new Product("plier", 3.5, 100, 100);
        Product scissor = new Product("scissor", 3, 200, 200);
        Product clipper = new Product("clipper", 1.5, 100, 100);
        Product tweezer = new Product("tweezer", 1, 150, 150);
        Product pincer = new Product("pincer", 3, 50, 50);
        Product cutter = new Product("cutter", 2, 150, 150);

        addProduct(plier);
        addProduct(scissor);
        addProduct(clipper);
        addProduct(tweezer);
        addProduct(pincer);
        addProduct(cutter);
        Order order = new Order(pincer, customer);
        orders.add(order);
        writeToJsonFile(orders, "src/main/java/warehouse/data_access/orders.json");
        readOrdersFromJsonFile();

        ReportingImpl reporting = new ReportingImpl();

        boolean exit = false;

        while (!exit) {

            print("Warehouse\\");
            print((char) 27 + "[37m" + "Enter a character from the Menu:");
            print("d  Display products   f  Filter products");
            print("l  Log in & order (must be logged to order)");
            print("s  Sign up            c  Contact info");
            print("▼                     e  Exit");

            switch (getOptionFromInput()) {
                case 'd':
                    printProducts();
                    break;
                case 'f':
                    reporting.filterProducts();
                    break;
                case 's':
                    signUp();
                    break;
                case 'e':
                    exit = true;
                    break;
                case 'l':
                    switch (login()) {
                        case CUSTOMER:
                            print("Warehouse\\Order");
                            orderProduct(existingProductFromInput());
                            break;
                        case ADMIN:
                            boolean quit = false;
                            while (!quit) {
                                print("Warehouse\\AdminRights");
                                print((char) 27 + "[37m" + "Enter a character from the Menu:");
                                print("s  List sum of all products & their prices");
                                print("a  Add product        d Delete product");
                                print("u  Update product     r Reporting & email");
                                print("▼                     q  Quit");

                                switch (getOptionFromInput()) {
                                    case 's':
                                        reporting.totals();
                                        break;
                                    case 'a':
                                        addProduct(constructProductFromInput());
                                        break;
                                    case 'd':
                                        removeProduct(existingProductFromInput());
                                        break;
                                    case 'u':
                                        updateProduct(existingProductFromInput());
                                        break;
                                        //report today's sales and underStock compensation
                                    //do you want to send an email with the reports to the CEO?
//                                    case 'r':
//                                        reporting();
//                                        break;
                                    case 'q':
                                        quit = true;
                                        break;
                                    default:
                                        print("Not a valid input! Try again:");
                                }
                            }
                            break;
                    }
                    break;
                default:
                    print("Not a valid input! Try again:");
            }
        }
        print("Goodbye!");
    }

    //toDo: DEBUG READING FROM JSON FILE
    // -make writeToJsonFile accept generic list
    // -let's say that payment takes place in the moment of delivery, implement payment later
    // -put methods in their right classes in their right order, e.g. have a clean OrderProcessing class
    // -calculate underStock based on desired Quantity, load warehouse with difference,
    //  if DateTime current - DateTime yesterday > 24h, send email-report
    /*toDo (ideas) for menu:
    -display directory position in header
    -display logged in name in header
    -display progress bar as filled-in characters |_||_|
    -create separate (switch) case: menu tree for admin and log in before it, putting no break after it,
     jumping directly to admin case after successful login, else (return) break
    -put all options in one switch, and allow (run menupoint)
     or restrict (sout no permission) options separately (in cases)
    -menu in adminrights to process orders
    -change text colour from grey to purple for logged in customer*/
}
