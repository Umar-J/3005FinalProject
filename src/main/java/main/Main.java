package main;
// Umar Jan
// 101270578
//yaman
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
        System.out.println("============Welcome to gym============\nAre you 1. Member 2. trainer 3. admin");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        // if they are registering
        if (choice == 1) {
            System.out.println("Sign in as: 1. New User 2. Existing user");
            int choice2 = scanner.nextInt();
            switch (choice2){
                case 1:
                    //register new member
                    Member member = new Member();
                    member.initializeMember(scanner);
                    // add to db
                    try (Connection connection = DbUtil.connect();) {
                        addUserToDb(member);
                        member.addSelftoDatabase(connection);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    break;
                //else if they are already a member
                case 2:
                    boolean isExists = false;
                    //Member member = new Member();
                    do {
                        member = new Member();
                        try (Connection connection = DbUtil.connect();) {
                            isExists = member.authenticateMember(scanner, connection);
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    } while (!isExists);
                    System.out.println("==========Main Menu==========");
                    System.out.println("1. View Dashboard\n2. Manage Profile\n3. Manage Schedule\n4. Exit");
                    int userSelect = scanner.nextInt();

                    switch (userSelect) {
                        case 1:
                            // view dashboard
                            member.showDashboard();
                            break;
                        case 2:
                            // print out information
                            member.printInformation();
                            System.out.println("Would you like to update your information? 1. yes 2. no");
                            int update = scanner.nextInt();
                            if (update == 1) {
                                member.updateInformation(scanner);
                            }
                            // manage profile
                            break;
                        case 3:
                            // manage schedule
                            member.manageSchedule(scanner);
                            break;
                        case 4:
                            //make flag false to exit
                            // exit
                            break;
                        default:
                            System.out.println("Invalid selection");
                            break;
                    }
                    break;
            }
            if (choice2 == 1) {

            } else if (choice2 == 2) {


                // Trainer functions
                // TODO: for ammar
            } else if (choice == 2) {
                System.out.println("are you: 1. new trainer 2. existing trainer");
                //start time, endtime, date(monday-sunday)
                int choice3 = scanner.nextInt();
                if (choice3 == 1) {
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
                } else if (choice3 == 2) {
                    System.out.println("enter your first name");
                    String firstname = scanner.next();
                    System.out.println("enter your last name");
                    String lastname = scanner.next();
                    System.out.println("enter your password");
                    String password = scanner.next();
                    // check if trainer exists
                }

            } else if (choice == 3) {
                System.out.println("are you: 1. new admin 2. existing admin");
                int choice4 = scanner.nextInt();
                if (choice4 == 1) {
                    // Admin admin = new Admin();
                    System.out.println("enter your first name");
                    //admin.firstname = scanner.next();
                    System.out.println("enter your last name");
                    //admin.lastname = scanner.next();
                    System.out.println("enter your password");
                    //admin.password = scanner.next();
                    //write admin to database
                } else if (choice4 == 2) {
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
    public static void addUserToDb(User user){
        try (Connection connection = DbUtil.connect();) {
            user.addtoDatabase(connection);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

