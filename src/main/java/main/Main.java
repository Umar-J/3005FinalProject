package main;
// Umar Jan
// 101270578
//yaman

import java.sql.*;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        //Establishing connection
        System.out.println("testing connection");
        try (Connection connection = DbUtil.connect();) {
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (Exception e) {
            System.out.println(e);
        }
        //Program starts
        System.out.println("============Welcome to gym============\nAre you 1. Member 2. Trainer 3. Admin 4. Exit");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        // if they are registering
        do {
            if (choice == 1) {
                System.out.println("Sign in as: 1. New User 2. Existing user");
                int choice2 = scanner.nextInt();
                switch (choice2) {
                    case 1:
                        //register new member
                        Member member = new Member();
                        member.initializeMember(scanner);
                        // add to db
                        try (Connection connection = DbUtil.connect();) {
                            addUserToDb(member);
                            member.addSelftoDatabase(connection);
                            System.out.println("Congratulations! You have successfully registered!\nPlease sign in again to access your dashboard.");

                        } catch (Exception e) {
                            System.out.println(e);
                        }
                        //else if they are already a member
                    case 2:
                        boolean isExists = false;
                        do {
                            member = new Member();
                            try (Connection connection = DbUtil.connect();) {
                                isExists = member.authenticateMember(scanner, connection);
                            } catch (Exception e) {
                                System.out.println(e);
                            }
                        } while (!isExists);
                        boolean flag = true;
                        do {
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
                                    flag = false;
                                    // exit
                                    break;
                                default:
                                    System.out.println("Invalid selection");
                                    break;
                            }

                        } while (flag);
                }
                if (choice2 == 1) {

                } else if (choice2 == 2) {


                }

            }
            // Trainer functions
            // TODO: for ammar
            else if (choice == 2) {
                System.out.println("Are you: 1. New Trainer 2. Existing Trainer 3. Exit");
                int typeOfTrainer = scanner.nextInt();
                switch (typeOfTrainer) {
                    case 1:
                        Trainer trainer = new Trainer();
                        trainer.initializeTrainer(scanner);
                        // add to db
                        try (Connection connection = DbUtil.connect();) {
                            addUserToDb(trainer);
                            trainer.addSelftoDatabase(connection);
                            //System.out.println("Congratulations! You have successfully registered!\nPlease sign in again to access your dashboard.");
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                        //write trainer to database
                        System.out.println("Congratulations! You have successfully registered!\nPlease sign in again to access your dashboard.");
                    case 2:
                        boolean isExists = false;
                        do {
                            // check if trainer exists
                            trainer = new Trainer();
                            try (Connection connection = DbUtil.connect();) {
                                isExists = trainer.authenticateTrainer(scanner, connection);
                            } catch (Exception e) {
                                System.out.println(e);
                            }
                        } while (!isExists);
                        boolean flag = true;
                        do {
                            System.out.println("==========Main Menu==========");
                            System.out.println("1. View Sessions\n2. View Profile\n3. Add Session\n4. Exit");
                            int userSelect = scanner.nextInt();
                            switch (userSelect) {
                                case 1:
                                    // view sessions
                                    trainer.showSessions();
                                    break;
                                case 2:
                                    // print out information
                                    trainer.printInformation();
                                    //System.out.println("Would you like to update your information? 1. yes 2. no");
//                                int update = scanner.nextInt();
//                                if (update == 1) {
//                                    member.updateInformation(scanner);
//                                }
                                    // manage profile
                                    break;
                                case 3:
                                    // manage schedule
                                    trainer.addSession(scanner);
                                    break;
                                case 4:
                                    flag = false;
                                    // exit
                                    break;
                                default:
                                    System.out.println("Invalid selection");
                                    break;
                            }
                        } while (flag);
                    case 3:
                        System.out.println("Exiting...");
                        flag = false;
                        break;
                        // leave the switch statement
                    default:
                        System.out.println("Invalid type of trainer");
                        break;
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
        } while (choice != 4);
    }

    public static void addUserToDb(User user) {
        try (Connection connection = DbUtil.connect();) {
            user.addtoDatabase(connection);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}

