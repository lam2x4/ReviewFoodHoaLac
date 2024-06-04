/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ACER
 */
import entity.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;


public class Mapper {
    public static User mapRow(ResultSet rs) throws SQLException {
        int user_id = rs.getInt(1);
        String user_name = rs.getString(2);
        String password = rs.getString(3);
        String email = rs.getString(4);
        String phone = rs.getString(5);
        String avatar = rs.getString(6);
        int gender = rs.getByte(7);
        String description = rs.getString(8);
        int verify_status = rs.getInt(9);
        int role_id = rs.getInt(10);

        return new User(user_id, user_name, password, email, phone, avatar, gender, description, verify_status, role_id);
    }
    public static void setRowUpdate(User user, PreparedStatement pstmt) throws SQLException {
        pstmt.setString(1, user.getUsername());
        pstmt.setString(2, user.getPassword());
        pstmt.setString(3, user.getEmail());
        pstmt.setString(4, user.getPhone());
        pstmt.setString(5, user.getAvatar());
        pstmt.setInt(6, user.getGender());
        pstmt.setString(7, user.getDescription());
        pstmt.setInt(8, user.getVerify_status());
        pstmt.setInt(9, user.getRole_id());
        pstmt.setInt(10, user.getId());
    }
    public static void setRowAdd(User user, PreparedStatement pstmt) throws SQLException {
        pstmt.setString(1, user.getUsername());
        pstmt.setString(2, user.getPassword());
        pstmt.setString(3, user.getEmail());
        pstmt.setString(4, user.getPhone());
        pstmt.setString(5, user.getAvatar());
        pstmt.setInt(6, user.getGender());
        pstmt.setString(7, user.getDescription());
        pstmt.setInt(8, user.getVerify_status());
        pstmt.setInt(9, user.getRole_id());
    }
}
