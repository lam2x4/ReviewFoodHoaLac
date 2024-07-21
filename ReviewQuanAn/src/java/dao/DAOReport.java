/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dal.DBContext;
import entity.Report;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lam1
 */
public class DAOReport extends DBContext {

    public int addReport(Report b) throws SQLException {
        String sql = "INSERT INTO [dbo].[Report] "
                + "([user_id],[blog_id],[content],create_date,[type_id],[is_approved]) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setInt(1, b.getUser_id());
            pre.setInt(2, b.getBlog_id());
            pre.setString(3, b.getContent());
            pre.setString(4, b.getCreate_date());
            pre.setInt(5, b.getType_id());
            pre.setInt(6, b.getIs_approved());

            return pre.executeUpdate();
        }
    }

    public Vector<Report> getAll() throws SQLException {
        Vector<Report> vector = new Vector<>();
        String sql = "SELECT * FROM Report";

        try (PreparedStatement pre = conn.prepareStatement(sql)) {

            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                Report b = new Report();

                b.setId(rs.getInt(1));
                b.setUser_id(rs.getInt(2));
                b.setBlog_id(rs.getInt(3));
                b.setContent(rs.getString(4));
                b.setCreate_date(rs.getString(5));
                b.setType_id(rs.getInt(6));
                b.setIs_approved(rs.getInt(7));

                vector.add(b);
            }
        }
        return vector;
    }

    public Vector<Report> getAllById(int user_id) throws SQLException {
        Vector<Report> vector = new Vector<>();
        String sql = "SELECT * FROM Report WHERE [user_id]=?";

        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setInt(1, user_id);
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                Report b = new Report();

                b.setId(rs.getInt(1));
                b.setUser_id(rs.getInt(2));
                b.setBlog_id(rs.getInt(3));
                b.setContent(rs.getString(4));
                b.setCreate_date(rs.getString(5));
                b.setType_id(rs.getInt(6));
                b.setIs_approved(rs.getInt(7));

                vector.add(b);
            }
        }
        return vector;
    }

    public int editReportApproved(int id, int approved) throws SQLException {
        String sql = "UPDATE [dbo].[Report] "
                + "SET [is_approved] = ? "
                + "WHERE id = ?";

        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setInt(1, approved);
            pre.setInt(2, id);

            return pre.executeUpdate();
        }
    }

    public Vector<Report> getAllWaiting() throws SQLException {
        Vector<Report> vector = new Vector<>();
        String sql = "SELECT * FROM Report WHERE is_approved = 0";

        try (PreparedStatement pre = conn.prepareStatement(sql)) {

            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                Report b = new Report();

                b.setId(rs.getInt(1));
                b.setUser_id(rs.getInt(2));
                b.setBlog_id(rs.getInt(3));
                b.setContent(rs.getString(4));
                b.setCreate_date(rs.getString(5));
                b.setType_id(rs.getInt(6));
                b.setIs_approved(rs.getInt(7));

                vector.add(b);
            }
        }
        return vector;
    }
    
    public Vector<Report> getListReportByPage(Vector<Report> list, int start, int end) {
        Vector<Report> blog = new Vector<>();
        if (list == null) {
            return null;
        }
        for (int i = start; i < end; i++) {
            blog.add(list.get(i));
        }
        return blog;
    }
    
    public Vector<Report> getAllNotWaiting() throws SQLException {
        Vector<Report> vector = new Vector<>();
        String sql = "SELECT * FROM Report WHERE is_approved != 0";

        try (PreparedStatement pre = conn.prepareStatement(sql)) {

            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                Report b = new Report();

                b.setId(rs.getInt(1));
                b.setUser_id(rs.getInt(2));
                b.setBlog_id(rs.getInt(3));
                b.setContent(rs.getString(4));
                b.setCreate_date(rs.getString(5));
                b.setType_id(rs.getInt(6));
                b.setIs_approved(rs.getInt(7));

                vector.add(b);
            }
        }
        return vector;
    }

    public static void main(String[] args) {
        DAOReport dao = new DAOReport();
        try {
            System.out.println(dao.getAllNotWaiting());
           
        } catch (SQLException ex) {
            Logger.getLogger(DAOReport.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
