//yaman
package main;

public class Admin extends User {

    // Default constructor
    public Admin() {
        super();
        setUserType(1);
    }

    // Constructor
    public Admin(int id, String name, String password, int userType) {
        super(id, name, password, 1);
    }


}

