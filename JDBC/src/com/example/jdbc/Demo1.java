package com.example.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Demo1 {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/mydatabase";
        String username = "root";
        String password = "surya2006";

        try (Connection con = DriverManager.getConnection(jdbcUrl, username, password);
             Statement statement = con.createStatement();
             Scanner sc = new Scanner(System.in);) {
            if (con == null) {
                System.out.println("Connection failed");
            } else {
                System.out.println("Connected to the database");
                System.out.println("Enter the Id");
                int id = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter the Name");
                String name = sc.nextLine();
                System.out.println("Enter the Country");
                String country = sc.nextLine();
                System.out.println("Enter the Score");
                int score = sc.nextInt();
                String sql = "insert into customers(id,first_name,country,score) values('" + id + "','" + name + "','" + country + "','" + score + "')";

                int rowAffected = statement.executeUpdate(sql);
                if (rowAffected == 1) {
                    System.out.println("Inserted Successfully");
                } else {
                    System.out.println("Insert failed");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // see only the message
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Something went wokring ");
        }

    }
}