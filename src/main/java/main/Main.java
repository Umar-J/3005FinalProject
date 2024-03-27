package main;
// Umar Jan
// 101270578
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;
import java.sql.PreparedStatement;



public class Main {
    public static void main(String[] args) {
        System.out.println("testing connection");
        try (Connection connection = DbUtil.connect();) {
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (Exception e) {
            System.out.println(e);
        }


    }
}

