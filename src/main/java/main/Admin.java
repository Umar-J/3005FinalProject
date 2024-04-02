//yaman
package main;

import java.sql.Connection;

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
    public void addSelftoDatabase(Connection conn){

    }

}

