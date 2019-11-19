package warehouse.model;

public class Admin {
    private String name;
    private String user;
    private String pass;

    public Admin() {
    }

    public Admin(String name, String user, String pass) {
        this.name = name;
        this.user = user;
        this.pass = pass;
    }

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }

    public String getName() {
        return name;
    }
}
