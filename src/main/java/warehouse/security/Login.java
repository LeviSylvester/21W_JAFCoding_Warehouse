package warehouse.security;

import warehouse.data_access.Users;
import warehouse.model.AccessLevel;
import warehouse.model.Admin;
import warehouse.model.Customer;

import static warehouse.business_logic.WarehouseManagement.print;
import static warehouse.business_logic.WarehouseManagement.scanner;
import static warehouse.model.AccessLevel.*;

public class Login {

    public static AccessLevel login() {
        AccessLevel accessLevel = LOGGED_OUT;
        boolean foundCredentials = false;

        print("Step 1/2 enter username below and press Enter:");
        String username = scanner.next();
        print("Step 2/2 enter password below and press Enter:");
        String password = scanner.next();
        print("Searching...");

        for (Admin a : Users.getAdmins()) {
            if (a.getUser().equals(username) && a.getPass().equals(password)) {
                foundCredentials = true;
                accessLevel = ADMIN;
                print("Welcome back, " + a.getName() + "!");
            }
        }

        if (!foundCredentials) {
            for (Customer c : Users.getCustomers()) {
                if (c.getUser().equals(username) && c.getPass().equals(password)) {
                    foundCredentials = true;
                    accessLevel = CUSTOMER;
                    print("Welcome back, " + c.getName() + "!");
                }
            }
        }

        if(!foundCredentials){
            print("Wrong username or password!");
        }

        return accessLevel;
    }

    //toDo: when many users, change code to search alphabetically
    // e.g. only the list with the initial of user,
    // not having to iterate through all users

}
