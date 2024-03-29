package main;
// Umar Jan
// 101270578
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        System.out.println("testing connection");
        try (Connection connection = DbUtil.connect();) {
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("welcome to gym \n are you 1. member 2. trainer or 3. admin");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        if (choice == 1){
            System.out.println("are you: 1. new user 2. existing user");
            int choice2 = scanner.nextInt();
            if (choice2 == 1) {
                // Member member = new Member();
                System.out.println("enter your first name");
                //member.firstname = scanner.next();
                System.out.println("enter your last name");
                //member.lastname = scanner.next();
                System.out.println("enter your password");
                //member.password = scanner.next();
                //Goal_weight, goal_workout, height,weight, bf%, routineENUM, paymentstatus, plan
                System.out.println("enter your goal weight");
                //member.goal_weight = scanner.nextInt();
                System.out.println("enter your goal workout");
                //member.goal_workout = scanner.next();
                System.out.println("enter your height");
                //member.height = scanner.nextInt();
                System.out.println("enter your weight");
                //member.weight = scanner.nextInt();
                System.out.println("enter your body fat percentage");
                //member.bf = scanner.nextInt();
                System.out.println("enter your routine");
                //member.routine = scanner.next();
                //calculate payment plan by goal workout
                //payment?
                //write member to database
            } else if (choice2 == 2){
                System.out.println("enter your first name");
                String firstname = scanner.next();
                System.out.println("enter your last name");
                String lastname = scanner.next();
                System.out.println("enter your password");
                String password = scanner.next();
                // check if user exists

            }
        }else if (choice == 2){
            System.out.println("are you: 1. new trainer 2. existing trainer");
            //start time, endtime, date(monday-sunday)
            int choice3 = scanner.nextInt();
            if (choice3 == 1){
                // Trainer trainer = new Trainer();
                System.out.println("enter your first name");
                //trainer.firstname = scanner.next();
                System.out.println("enter your last name");
                //trainer.lastname = scanner.next();
                System.out.println("enter your password");
                //trainer.password = scanner.next();
                System.out.println("enter your start time");
                //trainer.starttime = scanner.nextInt();
                System.out.println("enter your end time");
                //trainer.endtime = scanner.nextInt();
                System.out.println("enter your date");
                //trainer.date = scanner.next();
                //write trainer to database
            }else if (choice3 == 2){
                System.out.println("enter your first name");
                String firstname = scanner.next();
                System.out.println("enter your last name");
                String lastname = scanner.next();
                System.out.println("enter your password");
                String password = scanner.next();
                // check if trainer exists
            }

        }else if (choice == 3){
            System.out.println("are you: 1. new admin 2. existing admin");
            int choice4 = scanner.nextInt();
            if (choice4 == 1){
                // Admin admin = new Admin();
                System.out.println("enter your first name");
                //admin.firstname = scanner.next();
                System.out.println("enter your last name");
                //admin.lastname = scanner.next();
                System.out.println("enter your password");
                //admin.password = scanner.next();
                //write admin to database
            }
            else if (choice4 == 2){
                System.out.println("enter your first name");
                String firstname = scanner.next();
                System.out.println("enter your last name");
                String lastname = scanner.next();
                System.out.println("enter your password");
                String password = scanner.next();
                // check if admin exists
            }
        }
    }
}

