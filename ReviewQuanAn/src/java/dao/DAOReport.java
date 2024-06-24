/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;


import entity.Report;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author lam1
 */
public class DAOReport extends DBConnect{
//     public int addReport(Report b) throws SQLException {
//        String sql = "INSERT INTO [dbo].[Report] "
//                + "([user_id],[title],[content],[create_date],[likes],[is_approved],[is_banned]) "
//                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
//
//        try (PreparedStatement pre = conn.prepareStatement(sql)) {
//            pre.setInt(1, b.getUser_id());
//            pre.setString(2, b.getTitle());
//            pre.setString(3, b.getContent());
//            pre.setString(4, b.getCreate_date());
//            pre.setInt(5, b.getLikes());
//            pre.setInt(6, b.getIs_approved());
//            pre.setInt(7, b.getIs_banned());
//
//            return pre.executeUpdate();
//        }
//    }
}
