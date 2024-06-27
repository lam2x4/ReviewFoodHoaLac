package dao;

import Utility.Mapper;
import dal.DBContext;
import entity.Blog;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOBlog extends DBContext {

    public int addBlog(Blog b) throws SQLException {
        String sql = "INSERT INTO [dbo].[Blog] "
                + "([user_id],[title],[content],[create_date],[likes],[is_approved],[is_banned]) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            Mapper.setRowAddBlog(b, pre);

            return pre.executeUpdate();
        }
    }

    public int updateBlogUser_id(int user_id) throws SQLException {
        String sql = "UPDATE [dbo].[Blog] "
                + "SET [user_id] = null "
                + "WHERE [user_id] = ?";

        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setInt(1, user_id);

            return pre.executeUpdate();
        }
    }

    public int updateLikes(int blog_id, int likes) throws SQLException {
        String sql = "UPDATE Blog "
                + "SET likes = ? "
                + "WHERE id = ?";

        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setInt(1, likes);
            pre.setInt(2, blog_id);

            return pre.executeUpdate();
        }
    }

    public int editBlog(Blog b) throws SQLException {
        String sql = "UPDATE [dbo].[Blog] "
                + "SET [title] = ?, "
                + "[content] = ? "
                + "[create_date] = ? "
                + "[likes] = ? "
                + "[is_approved] = ? "
                + "[is_banned] = ? "
                + "WHERE id = ?";

        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            Mapper.setRowEditBlog(b, pre);

            return pre.executeUpdate();
        }
    }

    public int deleteBlog(int id) throws SQLException {
        String sql = "DELETE FROM Blog WHERE id = ? "
                + "AND id NOT IN (SELECT blog_id FROM Comment WHERE blog_id = ?) "
                + "AND id NOT IN (SELECT blog_id FROM Images WHERE blog_id = ?)";

        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setInt(1, id);
            pre.setInt(2, id);
            pre.setInt(3, id);

            return pre.executeUpdate();
        }
    }

    public int deleteBlogIgnoreConstraint(int id) throws SQLException {
        String sql = "delete from images where blog_id=? "
                + "delete from comment where blog_id=? "
                + "delete from blog where id=?";

        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setInt(1, id);
            pre.setInt(2, id);
            pre.setInt(3, id);

            return pre.executeUpdate();
        }
    }

    public Vector<Blog> getAll() throws SQLException {
        Vector<Blog> vector = new Vector<>();
        String sql = "SELECT * FROM Blog";

        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                Blog b = Mapper.mapRowBlog(rs);

                vector.add(b);
            }
        }
        return vector;
    }

    public Vector<Blog> getAllApproved() throws SQLException {
        Vector<Blog> vector = new Vector<>();
        String sql = "SELECT * FROM Blog WHERE is_approved = 1";

        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                Blog b = Mapper.mapRowBlog(rs);

                vector.add(b);
            }
        }
        return vector;
    }

    public int editBlogApproved(int id, int approved) throws SQLException {
        String sql = "UPDATE [dbo].[Blog] "
                + "SET [is_approved] = ? "
                + "WHERE id = ?";

        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setInt(1, approved);
            pre.setInt(2, id);

            return pre.executeUpdate();
        }
    }

    public Blog getBlog(int id) throws SQLException {
        String sql = "SELECT * FROM Blog WHERE id = ?";

        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setInt(1, id);

            try (ResultSet rs = pre.executeQuery()) {
                if (rs.next()) {
                    Blog b = Mapper.mapRowBlog(rs);

                    return b;
                } else {
                    throw new SQLException("No blog found with ID: " + id);
                }
            }
        }
    }

    public int getLastInsertedBlog() throws SQLException {
        String sql = "SELECT MAX(ID) AS LastInsertedBlogID FROM Blog";
        try (PreparedStatement pre = conn.prepareStatement(sql)) {

            try (ResultSet rs = pre.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("LastInsertedBlogID");
                } else {
                    throw new SQLException("Failed to retrieve last inserted blog ID");
                }
            }
        }
    }

    public static void main(String[] args) {
        DAOBlog dao = new DAOBlog();

        Blog b = new Blog(1, "New Title", "New Content", "", 0, 0, 0);
        try {
            dao.getAllApproved();
        } catch (SQLException ex) {
            Logger.getLogger(DAOBlog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
