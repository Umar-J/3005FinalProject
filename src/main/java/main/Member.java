//yaman
package main;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.ResultSet;



public class Member extends User {
    private int goalWeight;
    private double height;
    private double weight;
    private double bf;
    private String goalWorkout;
    private boolean paymentStatus;
    private String routine;
    private int plan;
    private LocalDate timeLine;

    // Default constructor
    public Member() {
        super();
        setUserType(3);
    }

    // Constructor
    public Member(int id, String name, String password, int userType, int goalWeight, String goalWorkout) {
        super(id, name, password, 3);
        this.goalWeight = goalWeight;
        this.goalWorkout = goalWorkout;
    }

    // Getters and Setters for additional attributes
    public int getGoalWeight() {
        return goalWeight;
    }

    public void setGoalWeight(int goalWeight) {
        this.goalWeight = goalWeight;
    }

    public String getGoalWorkout() {
        return goalWorkout;
    }

    public void setGoalWorkout(String goalWorkout) {
        this.goalWorkout = goalWorkout;
    }
    public void initializeMember(Scanner scanner){
        // Member member = new Member();
        setUserType(3);
        setUserTypeString("Member");
        System.out.println("enter your first name");
        setFirstName(scanner.next());
        System.out.println("enter your last name");
        setLastName(scanner.next());
        System.out.println("enter your password");
        setPassword(scanner.next());
        //Goal_weight, goal_workout, height,weight, bf%, routineENUM, paymentstatus, plan
        System.out.println("enter your goal weight");
        goalWeight = scanner.nextInt();
        System.out.println("enter your timeline; format; \"2021-01-23\"");
        String date = scanner.next();
        timeLine = LocalDate.parse(date);
        System.out.println("enter your goal workout");
        goalWorkout = scanner.next();
        System.out.println("enter your height");
        height = scanner.nextDouble();
        System.out.println("enter your weight");
        weight = scanner.nextDouble();
        System.out.println("enter your body fat percentage");
        bf = scanner.nextInt();
        System.out.println("enter your routine: 'upper', 'lower', 'cardio', 'full body', 'rest'");
        routine = scanner.next();
        //calculate payment plan by goal workout
        if (goalWorkout.equals("weight loss")) {
            paymentStatus = false;
        }else{
            paymentStatus = true;
        }
        //payment
        //write member to database

    }
    public boolean authenticateMember(Scanner scanner, Connection connection) throws SQLException {
        System.out.println("enter your first name");
        setFirstName(scanner.next());
        System.out.println("enter your last name");
        setLastName(scanner.next());
        System.out.println("enter your password");
        setPassword(scanner.next());
        if(authenticate(connection)){
            //populate member object
            populateMember(connection);
            return true;
        }else{
            return false;
        }
        // check if member exists
    }
    public void populateMember(Connection connection) throws SQLException {
        //populate member object
        System.out.println("populating member object");
        String sql = "SELECT * FROM MemberAttributes WHERE id = "+getId();
        Statement statement = connection.createStatement();
        statement.executeQuery(sql);
        ResultSet resultSet = statement.getResultSet();
        resultSet.next();
        goalWeight = resultSet.getInt("goal_weight");
        timeLine = resultSet.getDate("timeline").toLocalDate();
        goalWorkout = resultSet.getString("goal_workout");
        height = resultSet.getDouble("height");
        weight = resultSet.getDouble("weight");
        bf = resultSet.getDouble("bf_percentage");
        routine = resultSet.getString("routine");
        paymentStatus = Boolean.parseBoolean(resultSet.getString("payment_status"));
        plan = Integer.parseInt(resultSet.getString("plan"));


    }
    public void addSelftoDatabase(Connection connection){
        System.out.println("adding self to database");
        //write member to database
        String sql = "INSERT INTO MemberAttributes (id, goal_weight, timeline, goal_workout, height, weight, bf_percentage, routine, payment_status, plan) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            // Set parameters for the prepared statement
            pstmt.setInt(1, getId());
            pstmt.setDouble(2, goalWeight);
            pstmt.setObject(3, timeLine);
            pstmt.setString(4, goalWorkout);
            pstmt.setDouble(5, height);
            pstmt.setDouble(6, weight);
            pstmt.setDouble(7, bf);
            pstmt.setString(8, routine);
            pstmt.setString(9, Boolean.toString(paymentStatus));
            pstmt.setString(10, Integer.toString(plan));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void setPlan(){
        if (goalWorkout.equals("weight loss")) {
            plan = 1;
        } else if (goalWorkout.equals("muscle gain")) {
            plan = 2;
        } else if (goalWorkout.equals("maintain")) {
            plan = 3;
        }
    }
    public void printInformation(){
        System.out.println("--------------------------------------------------");
        System.out.println("Information for Member ID: " + getId());
        System.out.println("--------------------------------------------------");
        System.out.println(String.format("Name: %-20s", getFirstName() + " " + getLastName()));
        System.out.println(String.format("Goal Weight: %-15s", goalWeight + " lbs"));
        System.out.println(String.format("Goal Workout: %-13s", goalWorkout));
        System.out.println(String.format("Height: %-18s", height + " inches"));
        System.out.println(String.format("Weight: %-17s", weight + " lbs"));
        System.out.println(String.format("Body Fat Percentage: %-5s", bf + "%"));
        System.out.println(String.format("Routine: %-17s", routine));
        System.out.println(String.format("Payment Status: %-10s", paymentStatus));
        System.out.println(String.format("Plan: %-20s", plan));
        System.out.println(String.format("Timeline: %-16s", timeLine));
        System.out.println("--------------------------------------------------");



    }
    public void updateInformation(Scanner scanner){
        System.out.println("What would you like to update?");
        System.out.println("1. Goal Weight\n2. Goal Workout\n3. Height\n4. Weight\n5. Body Fat Percentage\n6. Routine\n7. Timeline");
        int choice = scanner.nextInt();
        switch (choice){
            case 1:
                System.out.println("Enter new goal weight");
                goalWeight = scanner.nextInt();
                break;
            case 2:
                System.out.println("Enter new goal workout");
                goalWorkout = scanner.next();
                break;
            case 3:
                System.out.println("Enter new height");
                height = scanner.nextDouble();
                break;
            case 4:
                System.out.println("Enter new weight");
                weight = scanner.nextDouble();
                break;
            case 5:
                System.out.println("Enter new body fat percentage");
                bf = scanner.nextDouble();
                break;
            case 6:
                System.out.println("Enter new routine");
                routine = scanner.next();
                break;
            case 7:
                System.out.println("Enter new timeline");
                String date = scanner.next();
                timeLine = LocalDate.parse(date);
                break;
        }
        sendUpdates();
    }
    private void sendUpdates(){
        //send updates to database

        System.out.println("updating self to database");
        //write member to database
        String sql = "UPDATE MemberAttributes SET goal_weight = ?, timeline = ?, goal_workout = ?, height = ?, weight = ?, bf_percentage = ?, routine = ?, payment_status = ?, plan = ? WHERE id = "+ getId();
        try (Connection connection = DbUtil.connect();
                PreparedStatement pstmt = connection.prepareStatement(sql)) {

            // Set parameters for the prepared statement
            pstmt.setDouble(1, goalWeight);
            pstmt.setObject(2, timeLine);
            pstmt.setString(3, goalWorkout);
            pstmt.setDouble(4, height);
            pstmt.setDouble(5, weight);
            pstmt.setDouble(6, bf);
            pstmt.setString(7, routine);
            pstmt.setString(8, Boolean.toString(paymentStatus));
            pstmt.setString(9, Integer.toString(plan));

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    public void showDashboard(){
        System.out.println("--------------------------------------------------");
        System.out.println("             Welcome to your dashboard            ");
        System.out.println("--------------------------------------------------");
        System.out.printf("Exercise Routines: %s%n", routine);
        System.out.println("Health Stats:");
        System.out.printf(" - Weight: %.2f lbs%n", weight);
        System.out.printf(" - Height: %.2f inches%n", height);
        System.out.printf(" - Body Fat Percentage: %.2f%%%n", bf);
        System.out.println("--------------------------------------------------");
        System.out.println("Fitness Achievements:");

        // display fitness achievements (join member attributes with achievements
        String sql = "select achievements_user from achievements where id = "+ getId();
        try (Connection connection = DbUtil.connect();
             Statement statement = connection.createStatement()) {
            statement.executeQuery(sql);
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()){
                System.out.println(resultSet.getString("achievements_user"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("--------------------------------------------------");

    }
    public String toString(){
        return "Member: " + getName() + " with goal weight: " + goalWeight + " and goal workout: " + goalWorkout;
    }
}
