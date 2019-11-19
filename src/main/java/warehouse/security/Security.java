package warehouse.security;

import warehouse.data_access.Users;
import warehouse.model.Customer;

import static warehouse.business_logic.WarehouseManagement.print;
import static warehouse.business_logic.WarehouseManagement.scanner;

public class Security {
    public static Customer login() {
        Customer customer = new Customer();
        boolean foundCredentials = false;

        print("Please confirm order by entering user and pass again.");
        print("Step 1/2 enter username below and press Enter:");
        String username = scanner.next();
        print("Step 2/2 enter password below and press Enter:");
        String password = scanner.next();
        print("Searching...");

        for (Customer c : Users.getCustomers()) {
            if (c.getUser().equals(username) && c.getPass().equals(password)) {
                foundCredentials = true;
                customer = new Customer(c.getName(), c.getUser(), c.getPass(), c.getPhone(), c.getAddress(), c.getEmail());
                print("Thank you for confirming, " + customer.getName() + "!");
            }
        }

        if (!foundCredentials) {
            print("Wrong username or password!");
        }

        return customer;
    }
}
