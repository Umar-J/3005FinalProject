//yaman
package main;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;
import java.sql.Connection;


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
}
