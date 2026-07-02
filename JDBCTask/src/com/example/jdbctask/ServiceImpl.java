package com.example.jdbctask;

import java.sql.*;

public class ServiceImpl implements Service {

    @Override
    public void createDB(String dbname) {

        String sql = "CREATE DATABASE IF NOT EXISTS " + dbname;

        try (Connection con = DBUtil.getConnection();
             Statement st = con.createStatement()) {

            st.executeUpdate(sql);
            DBUtil.setDbName(dbname);

            System.out.println("Database Created Successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createTable() {

        String sql = "CREATE TABLE IF NOT EXISTS student(" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "regno VARCHAR(20)," +
                "name VARCHAR(30)," +
                "dept VARCHAR(20)," +
                "email VARCHAR(50))";

        try (Connection con = DBUtil.getDBConnection();
             Statement st = con.createStatement()) {

            st.executeUpdate(sql);

            System.out.println("Table Created Successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertData(Student student) {

        String sql =
                "INSERT INTO student(regno,name,dept,email) VALUES(?,?,?,?)";

        try (Connection con = DBUtil.getDBConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, student.getRegno());
            ps.setString(2, student.getName());
            ps.setString(3, student.getDept());
            ps.setString(4, student.getEmail());

            ps.executeUpdate();

            System.out.println("Data Inserted Successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(String regno, String name) {

        String sql = "UPDATE student SET name=? WHERE regno=?";

        try (Connection con = DBUtil.getDBConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, regno);

            ps.executeUpdate();

            System.out.println("Updated Successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String regno) {

        String sql = "DELETE FROM student WHERE regno=?";

        try (Connection con = DBUtil.getDBConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, regno);

            ps.executeUpdate();

            System.out.println("Deleted Successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void findAll() {

        String sql = "SELECT * FROM student";

        try (Connection con = DBUtil.getDBConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                System.out.println(
                        rs.getInt("id") + " " +
                                rs.getString("regno") + " " +
                                rs.getString("name") + " " +
                                rs.getString("dept") + " " +
                                rs.getString("email")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}