/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Bookmark;
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
public class DAOBookmark extends DBConnect {

    public int addBookmark(Bookmark b) throws SQLException {
        String sql = "INSERT INTO [dbo].[Bookmark] "
                + "([user_id],[blog_id]) "
                + "VALUES (?, ?)";

        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setInt(1, b.getUserID());
            pre.setInt(2, b.getBlogID());

            return pre.executeUpdate();
        }
    }

    public int removeBookmark(Bookmark b) throws SQLException {
        String sql = "DELETE FROM [dbo].[Bookmark] WHERE [user_id] = ? AND [blog_id] = ?";

        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setInt(1, b.getUserID());
            pre.setInt(2, b.getBlogID());

            return pre.executeUpdate();
        }
    }

    public Vector<Bookmark> getAll() throws SQLException {
        Vector<Bookmark> vector = new Vector<>();
        String sql = "SELECT * FROM Bookmark";

        try (PreparedStatement pre = conn.prepareStatement(sql)) {

            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                Bookmark b = new Bookmark();

                b.setId(rs.getInt(1));
                b.setUserID(rs.getInt(2));
                b.setBlogID(rs.getInt(3));

                vector.add(b);
            }
        }
        return vector;
    }

    public Vector<Bookmark> getAllByUserId(int user_id) throws SQLException {
        Vector<Bookmark> vector = new Vector<>();
        String sql = "SELECT * FROM Bookmark WHERE [user_id]=?";

        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setInt(1, user_id);
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                Bookmark b = new Bookmark();

                b.setId(rs.getInt(1));
                b.setUserID(rs.getInt(2));
                b.setBlogID(rs.getInt(3));

                vector.add(b);
            }
        }
        return vector;
    }

    public Bookmark getOne(int user_id, int blog_id) throws SQLException {
        Bookmark bookmark = null;
        String sql = "SELECT * FROM Bookmark WHERE [user_id]=? AND [blog_id]=?";

        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setInt(1, user_id);
            pre.setInt(2, blog_id);
            ResultSet rs = pre.executeQuery();

            if (rs.next()) {
                bookmark = new Bookmark();
                bookmark.setId(rs.getInt(1));
                bookmark.setUserID(rs.getInt(2));
                bookmark.setBlogID(rs.getInt(3));
            }
        }
        return bookmark;
    }

    public static void main(String[] args) {
        DAOBookmark dao = new DAOBookmark();
        try {
            System.out.println(dao.getAll().get(2).getId());
        } catch (SQLException ex) {
            Logger.getLogger(DAOBookmark.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
