package warehouse.data_access;

import warehouse.model.Admin;
import warehouse.model.Customer;

import java.util.ArrayList;
import java.util.List;

import static warehouse.business_logic.WarehouseManagement.print;
import static warehouse.business_logic.WarehouseManagement.scanner;

public class Users {

    private static List<Admin> admins = new ArrayList<>();
    private static List<Customer> customers = new ArrayList<>();

    public static List<Admin> getAdmins() {
        return admins;
    }

    public static List<Customer> getCustomers() {
        return customers;
    }

    public static void addUser(Admin admin) {
        admins.add(admin);
    }

    public static void addUser(Customer customer) {
        customers.add(customer);
    }

    public static void signUp() {//toDo validate
        Customer customer = new Customer();
        print("Enter your name:");
        customer.setName(scanner.next());
        print("Enter your user:");
        customer.setUser(scanner.next());
        print("Enter your password:");
        customer.setPass(scanner.next());//toDo confirm pass
        print("Enter your phone:");
        customer.setPhone(scanner.next());
        print("Enter your address:");
        customer.setAddress(scanner.next());
        print("Enter your email:");
        customer.setEmail(scanner.next());
        addUser(customer);
    }
}
