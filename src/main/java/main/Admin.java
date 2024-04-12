//yaman
package main;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
public class Admin extends User {

    // Default constructor
    public Admin() {
        super();
        setUserType(Admin);

    }

    // Constructor
    public Admin(int id, String name, String password, int userType) {
        super(id, name, password, 1);
    }

    @Override
    public void addSelftoDatabase(Connection connection) {
        System.out.println("adding self to database");
        //write member to database
        String sql = "INSERT INTO MemberAttributes (id, goal_weight, timeline, goal_workout, height, weight, bf_percentage, routine, payment_status, plan) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (
                PreparedStatement pstmt = connection.prepareStatement(sql)) {

            // Set parameters for the prepared statement
            pstmt.setInt(1, getId());
            pstmt.setDouble(2, 0);
            pstmt.setString(3, "none");
            pstmt.setString(4, "none");
            pstmt.setDouble(5, 0);
            pstmt.setDouble(6, 0);
            pstmt.setDouble(7, 0);
            pstmt.setString(8, "none");
            pstmt.setBoolean(9, false);
            pstmt.setString(10, "none");
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public boolean authenticateAdmin(Scanner scanner, Connection connection) throws SQLException {
        System.out.println("enter your first name");
        setFirstName(scanner.next());
        System.out.println("enter your last name");
        setLastName(scanner.next());
        System.out.println("enter your password");
        setPassword(scanner.next());
        if (authenticate(connection)) {
            //populate member object
            populateAdmin(connection);
            return true;
        } else {
            return false;
        }
        // check if member exists
    }

    public void populateAdmin(Connection connection) throws SQLException {
        //populate Admin object

    }

    public void equipmentMaintenance(Scanner scanner) {
        //
    }

    public void classScheduleUpdating(Scanner scanner, Connection conn) {
        //update session talbes
        //update it in database
        System.out.println("Enter the session ID you would like to update");
            System.out.println("What would you like to update?");
            System.out.println("1. session ID\n2. trainer ID\n3. member ID\n4. start time\n5. end time\n6.  date\n7. group\n8. room number\n9. equipment ID\n10. exit");
            int choice = scanner.nextInt();
//            switch (choice){
//                case 1:
//                    System.out.println("Enter new session ID");
//                    sessionID = scanner.nextInt();
//                    break;
//                case 2:
//                    System.out.println("Enter new trainer ID");
//                    trainerID = scanner.next();
//                    break;
//                case 3:
//                    System.out.println("Enter new member ID");
//                    memberID = scanner.nextDouble();
//                    break;
//                case 4:
//                    System.out.println("Enter new start time");
//                    startTime = scanner.nextDouble();
//                    break;
//                case 5:
//                    System.out.println("Enter new end time");
//                    endTime = scanner.nextDouble();
//                    break;
//                case 6:
//                    System.out.println("Enter new date");
//                    date = scanner.next();
//                    break;
//                case 7:
//                    System.out.println("Enter new group");
//                    group = scanner.next();
//                    break;
//                case 8:
//                    System.out.println("Enter new room number");
//                    roomNumber = scanner.next();
//                    break;
//                case 9:
//                System.out.println("Enter new equipment id");
//                    equipmentID = scanner.next();
//                break;
//            }
//            sendUpdates();
//        }
    }

    public void processPayment(Scanner scanner){
        // process payment
        System.out.println("processing payment for those who did not pay yet id's:");
        ArrayList<Integer> ids = new ArrayList<Integer>();
        // get payment status where it is false from memberatributes
        String sql = "SELECT * FROM MemberAttributes WHERE payment_status = false";
        try (Connection connection = DbUtil.connect();) {
            Statement statement = connection.createStatement();
            statement.executeQuery(sql);
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                System.out.println("Member ID: " + resultSet.getInt("id"));
                ids.add(resultSet.getInt("id"));
                System.out.println("their monthly payment is " + resultSet.getString("plan"));
                System.out.println("stealing their money.....");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        for (int i =0; i<ids.size(); i++){
            String sql2 = "UPDATE MemberAttributes SET payment_status = true WHERE id = "+ ids.get(i);
            try (Connection connection = DbUtil.connect();
                 PreparedStatement pstmt = connection.prepareStatement(sql2)) {
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public void initializeAdmin(Scanner scanner) {
        System.out.println("Enter your first name: ");
        setFirstName(scanner.next());
        System.out.println("Enter your last name: ");
        setLastName(scanner.next());
        System.out.println("Enter your password: ");
        setPassword(scanner.next());
        setUserType(1);
        setUserTypeString("Admin");
    }
}




