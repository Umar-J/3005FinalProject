//yaman
package main;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Trainer extends User {
    private String schedule;
    private List<Member> members; // A list of Member objects this trainer is associated with

    private Time startTime;
    private Time endTime;
    private Date startDate;

    private Date endDate;

    // Default constructor
    public Trainer() {
        super();
        setUserType(Trainer);
        this.members = new ArrayList<>(); // Initializes the list to avoid null pointer exceptions
    }

    // Constructor
    public Trainer(int id, String name, String password, int userType, String schedule, List<Member> members) {
        super(id, name, password, 2);
        this.schedule = schedule;
        this.members = members;
    }

    // Getters and Setters
    public void addSelftoDatabase(Connection conn){
        System.out.println("adding self to database");
        //write trainer to database
        String sql = "INSERT INTO TrainerAvailability (trainer_id, start_time, end_time, start_date, end_date) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Set parameters for the prepared statement
            pstmt.setInt(1, getId());
            pstmt.setTime(2, startTime);
            pstmt.setTime(3,endTime);
            pstmt.setDate(4, startDate);
            pstmt.setDate(5, endDate);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public void setStartTime(Time startTime){ this.startTime = startTime; }

    public void setEndTime(Time endTime){ this.endTime = endTime; }

    public void setStartDate(Date startDate){ this.startDate = startDate; }

    public void setEndDate(Date endDate){ this.endDate = endDate; }

    public Time getStartTime() {
        return this.startTime;
    }

    public Time getEndTime() {

        return this.endTime;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }


    public void viewMember(String name){
        for (int i = 0; i < members.size(); ++i){
            if (members.get(i).getName().equals(name)){
                members.get(i).printInformation();
            }
        }
        System.out.println("Member not found in trainer list!");
    }

    public void initializeTrainer(Scanner scanner){

        setUserType(Trainer);
        setUserTypeString("Trainer");
        System.out.println("enter your first name");
        setFirstName(scanner.next());
        System.out.println("enter your last name");
        setLastName(scanner.next());
        System.out.println("enter your password");
        setPassword(scanner.next());
        System.out.println("enter your start time (format: hh:mm:ss):");
        this.startTime = Time.valueOf(scanner.next());
        System.out.println("enter your end time (format: hh:mm:ss): ");
        this.endTime = Time.valueOf(scanner.next());
        System.out.println("enter your start date (format: yyyy-mm-dd): ");
        setStartDate(Date.valueOf(scanner.next()));
        System.out.println("enter your end date (format: yyyy-mm-dd): ");
        setEndDate(Date.valueOf(scanner.next()));

    }

    public boolean authenticateTrainer(Scanner scanner, Connection connection) throws SQLException {
        System.out.println("enter your first name");
        setFirstName(scanner.next());
        System.out.println("enter your last name");
        setLastName(scanner.next());
        System.out.println("enter your password");
        setPassword(scanner.next());
        if(authenticate(connection)){
            //populate member object
            //populateTrainer(connection);
            return true;
        }else{
            return false;
        }
        // check if member exists
    }

    public void populateTrainer(Connection connection) throws SQLException {
        //populate member object
        System.out.println("populating member object");
        String sql = "SELECT * FROM TrainerAvailability WHERE trainer_id = "+getId();
        Statement statement = connection.createStatement();
        statement.executeQuery(sql);
        ResultSet resultSet = statement.getResultSet();
        resultSet.next();
        //setId(); = resultSet.getInt("trainer_id");
        startTime = resultSet.getTime("start_time");
        endTime = resultSet.getTime("end_time");
        startDate = resultSet.getDate("start_date");
        endDate = resultSet.getDate("end_date");

    }

    public void showSessions(){
        System.out.println("sessions: \n ");
        String sql = "SELECT * FROM Sessions WHERE trainer_id = "+getId();
        try (Connection connection = DbUtil.connect();) {
            Statement statement = connection.createStatement();
            statement.executeQuery(sql);
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                System.out.println("Session ID: " + resultSet.getInt("session_id"));
                System.out.println("Trainer ID: " + resultSet.getInt("trainer_id"));
                System.out.println("Member ID: " + resultSet.getInt("member_id"));
                System.out.println("Start Time: " + resultSet.getTime("start_time"));
                System.out.println("End Time: " + resultSet.getTime("end_time"));
                System.out.println("Date: " + resultSet.getDate("date"));
                System.out.println("Room Number: " + resultSet.getInt("room_number"));
                //System.out.println(": " + resultSet.getDate("end_date"));
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }



    public void printInformation(){
        System.out.println("Trainer Information: \n ");
        System.out.println("Trainer ID: " + getId());
        System.out.println("First Name: " + getFirstName());
        System.out.println("Last Name: " + getLastName());
        System.out.println("Start Time: " + getStartTime());
        System.out.println("End Time: " + getEndTime());
        System.out.println("Start Date: " + getStartDate());
        System.out.println("End Date: " + getEndDate());
        System.out.println();

    }

    public void addSession(Scanner scanner){
        
        System.out.println("adding session to database");
        System.out.println("enter your start time (format: hh:mm:ss):");
        Time startTimeOfSession = Time.valueOf(scanner.next());
        System.out.println("enter your end time (format: hh:mm:ss): ");
        Time endTimeOfSession = Time.valueOf(scanner.next());
        System.out.println("enter your date (format: yyyy-mm-dd): ");
        Date dateOfSession = Date.valueOf(scanner.next());
        System.out.println("enter your room number: ");
        int roomNumber = scanner.nextInt();

        String sql = "INSERT INTO Sessions (trainer_id, start_time, end_time, date, room_number) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DbUtil.connect();) {
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setInt(1, getId());
                pstmt.setTime(2, startTimeOfSession);
                pstmt.setTime(3, endTimeOfSession);
                pstmt.setDate(4, dateOfSession);
                pstmt.setInt(5, roomNumber);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }



}


