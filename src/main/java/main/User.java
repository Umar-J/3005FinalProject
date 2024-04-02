//yaman
package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class User {
    public static final int Admin = 1;
    public static final int Trainer = 2;
    public static final int Member = 3;
    private int id;
    private String name;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    private String firstName;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    private String lastName;
    private String password;
    private int userType;

    public String getUserTypeString() {
        return userTypeString;
    }

    public void setUserTypeString(String userTypeString) {
        this.userTypeString = userTypeString;
    }

    private String userTypeString;

    // Default constructor
    public User() {
        id = 999999;
        name = "placeholder";
        password = "placeholder";
        userType = 3;
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
    public void initID(){
        try {
            Connection connection = DbUtil.connect();
            String query = "SELECT id FROM users WHERE first_name = ? AND last_name = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, password);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            resultSet.next();
            id = resultSet.getInt("id");
            setId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    public String getName() {
        return name;
    }
    public abstract void addSelftoDatabase(Connection connection);

    public void addtoDatabase(Connection connection) {
        try {
            String query = "INSERT INTO users (first_name , last_name , password, user_type) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, password);
            preparedStatement.setInt(4, userType);
            preparedStatement.executeUpdate();
            initID(); //sets id for further adding member or whatever else
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

