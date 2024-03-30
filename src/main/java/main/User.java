//yaman
package main;
public class User {
    public static final int Admin = 1;
    public static final int Trainer = 2;
    public static final int Member = 3;
    private int id;
    private String name;
    private String password;
    private int userType;
    // Default constructor
    public User() {
    }

    // Constructor
    public User(int id, String name, String password, int userType) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.userType = userType;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }
}

