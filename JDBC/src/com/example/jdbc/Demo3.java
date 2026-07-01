package com.example.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Demo3 {
    public static void main(String[] args) {
        try (Connection con =DBUtil.getConnection()){

            String sql ="insert into customers values(?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,8);
            ps.setString(2,"Sanji");
            ps.setString(3,"Japan");
            ps.setInt(4,96);
            int rowsAffected = ps.executeUpdate();
            if(rowsAffected > 0){
                System.out.println("Insert Successful");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
