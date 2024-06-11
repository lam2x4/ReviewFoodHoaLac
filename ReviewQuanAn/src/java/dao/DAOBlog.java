package dao;

import dal.DBContext;
import entity.Blog;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOBlog extends DBConnect {

    public int addBlog(Blog b) throws SQLException {
        String sql = "INSERT INTO [dbo].[Blog] "
                + "([user_id],[title],[content],[create_date],[likes],[is_approved],[is_banned]) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setInt(1, b.getUser_id());
            pre.setString(2, b.getTitle());
            pre.setString(3, b.getContent());
            pre.setString(4, b.getCreate_date());
            pre.setInt(5, b.getLikes());
            pre.setInt(6, b.getIs_approved());
            pre.setInt(7, b.getIs_banned());

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
            pre.setString(1, b.getTitle());
            pre.setString(2, b.getContent());
            pre.setString(3, b.getCreate_date());
            pre.setInt(4, b.getLikes());
            pre.setInt(5, b.getIs_approved());
            pre.setInt(6, b.getIs_banned());

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
                Blog b = new Blog();

                b.setId(rs.getInt(1));
                b.setUser_id(rs.getInt(2));
                b.setTitle(rs.getString(3));
                b.setContent(rs.getString(4));
                b.setCreate_date(rs.getString(5));
                b.setLikes(rs.getInt(6));
                b.setIs_approved(rs.getInt(7));
                b.setIs_banned(rs.getInt(8));

                vector.add(b);
            }
        }
        return vector;
    }

    public Vector<Blog> getAllApproved() throws SQLException {
        Vector<Blog> vector = new Vector<>();
        String sql = "SELECT * FROM Blog";

        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                Blog b = new Blog();

                b.setId(rs.getInt(1));
                b.setUser_id(rs.getInt(2));
                b.setTitle(rs.getString(3));
                b.setContent(rs.getString(4));
                b.setCreate_date(rs.getString(5));
                b.setLikes(rs.getInt(6));
                b.setIs_approved(rs.getInt(7));
                b.setIs_banned(rs.getInt(8));
                if (b.getIs_approved() == 1) {
                    vector.add(b);
                }
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
                    Blog b = new Blog();

                    b.setId(rs.getInt(1));
                    b.setUser_id(rs.getInt(2));
                    b.setTitle(rs.getString(3));
                    b.setContent(rs.getString(4));
                    b.setCreate_date(rs.getString(5));
                    b.setLikes(rs.getInt(6));
                    b.setIs_approved(rs.getInt(7));
                    b.setIs_banned(rs.getInt(8));

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
