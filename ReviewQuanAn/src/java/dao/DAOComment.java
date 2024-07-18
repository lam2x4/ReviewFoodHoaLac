package dao;

import Utility.Mapper;
import dal.DBContext;
import entity.Comment;
import entity.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOComment extends DBContext {

    public int addComment(Comment comm) throws SQLException {
        String sql = "INSERT INTO [dbo].[Comment] "
                + "([user_id], [blog_id], [content], [create_date], [likes], [is_banned]) "
                + "VALUES (?,?,?,?,?,?)";

        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            Mapper.setRowAddComment(comm, pre);

            return pre.executeUpdate();
        }
    }

    public int editComment(Comment comm) throws SQLException {
        String sql = "UPDATE Comment "
                + "SET content = ? "
                + "WHERE id = ?";

        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            Mapper.setRowEditComment(comm, pre);

            return pre.executeUpdate();
        }
    }

    public int updateLikes(int comment_id, int likes) throws SQLException {
        String sql = "UPDATE Comment "
                + "SET likes = ? "
                + "WHERE id = ?";

        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setInt(1, likes);
            pre.setInt(2, comment_id);

            return pre.executeUpdate();
        }
    }

    public int deleteComment(int id) throws SQLException {
        String deleteCommentLikesSql = "DELETE FROM CommentLikes WHERE comment_id = ?";
    String deleteCommentSql = "DELETE FROM Comment WHERE id = ?";

    try (PreparedStatement preCommentLikes = conn.prepareStatement(deleteCommentLikesSql);
         PreparedStatement preComment = conn.prepareStatement(deleteCommentSql)) {

        // First, delete from CommentLikes table
        preCommentLikes.setInt(1, id);
        preCommentLikes.executeUpdate();

        // Then, delete from Comment table
        preComment.setInt(1, id);
        return preComment.executeUpdate();
    }
    }

    public Vector<Comment> viewAll() throws SQLException {
        Vector vector = new Vector<>();
        String sql = "SELECT * FROM Comment ORDER BY create_date DESC";

        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                Comment comm = Mapper.mapRowComment(rs);

                vector.add(comm);
            }
        }
        return vector;
    }

    public Vector<Comment> getCommentsSortedByLikes(int blog_id) throws SQLException {
        Vector vector = new Vector<>();
        String sql = "SELECT * FROM Comment WHERE blog_id = ? ORDER BY likes DESC";

        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setInt(1, blog_id);
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                Comment comm = Mapper.mapRowComment(rs);

                vector.add(comm);
            }
        }
        return vector;
    }

    public Vector<Comment> findCommentsByBlog_id(int blog_id) throws SQLException {
        Vector vector = new Vector<>();
        String sql = "SELECT * FROM Comment WHERE blog_id = ? ORDER BY create_date DESC";

        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setInt(1, blog_id);
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                Comment comm = Mapper.mapRowComment(rs);

                vector.add(comm);
            }
        }
        return vector;
    }

    public User findUserById(int user_id) throws SQLException {
        String sql = "SELECT * FROM [User] WHERE id = (SELECT user_id FROM Comment WHERE user_id = ?)";

        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setInt(1, user_id);

            try (ResultSet rs = pre.executeQuery()) {
                if (rs.next()) {
                    User user = Mapper.mapRowUser(rs);
                    return user;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String findUsername(int user_id) throws SQLException {
        String sql = "SELECT username FROM [User] WHERE id = (SELECT TOP 1 user_id FROM Comment WHERE user_id = ?)";

        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setInt(1, user_id);

            try (ResultSet rs = pre.executeQuery()) {
                if (rs.next()) {
                    return rs.getString(1);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String findAvatarByUser_id(int user_id) throws SQLException {
        String sql = "SELECT avatar FROM [User] WHERE id = (SELECT TOP 1 user_id FROM Comment WHERE user_id = ?)";
        
        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setInt(1, user_id);
            
            try (ResultSet rs = pre.executeQuery()) {
                if (rs.next()) {
                    return rs.getString(1);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int getLastestId() {
        String sql = "SELECT TOP 1 id FROM Comment ORDER BY id DESC;";

        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            
            try (ResultSet rs = pre.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public Comment getComment(int comment_id) throws SQLException {
        String sql = "SELECT * FROM Comment WHERE id = ?";

        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setInt(1, comment_id);
            try (ResultSet rs = pre.executeQuery()) {
                if (rs.next()) {
                    Comment comm = Mapper.mapRowComment(rs);
                    return comm;
                } else {
                    throw new SQLException("No comment found with ID: " + comment_id);
                }
            }
        }
    }
    
    public Vector<Comment> GetAllById(int user_id) throws SQLException {
        Vector<Comment> vector = new Vector<>();
        String sql = "SELECT * FROM Comment WHERE [user_id]=?";
        
        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setInt(1, user_id);
            ResultSet rs = pre.executeQuery();
            
            while (rs.next()) {
                Comment b = new Comment();
                
                b.setId(rs.getInt(1));
                b.setUser_id(rs.getInt(2));
                b.setBlog_id(rs.getInt(3));
                b.setContent(rs.getString(4));
                b.setCreate_date(rs.getString(5));
                b.setLikes(rs.getInt(6));
                b.setIs_banned(rs.getInt(7));
                vector.add(b);
            }
        }
        return vector;
    }
    
    
    public Vector<Comment> getListCommentByPage(Vector<Comment> list, int start, int end) {
        Vector<Comment> comment = new Vector<>();
        if (list == null) {
            return null;
        }
        for (int i = start; i < end; i++) {
            comment.add(list.get(i));
        }
        return comment;
    }

    public static void main(String[] args) {
        DAOComment dao = new DAOComment();
        try {
            Vector<Comment> vector = dao.getCommentsSortedByLikes(8);
            for (Comment c : vector) {
                System.out.println(c.toString());
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOComment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
