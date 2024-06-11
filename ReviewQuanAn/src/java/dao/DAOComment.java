package dao;

import Utility.Mapper;
import entity.Comment;
import entity.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DAOComment extends DBConnect {
    
    public int addComment(Comment comm) throws SQLException{
        DAOUser daoUser = new DAOUser();
        String sql = "INSERT INTO [dbo].[Comment] " +
                    "([user_id], [blog_id], [content], [likes]) " +
                    "VALUES (?,?,?,?)";
        
        try(PreparedStatement pre = conn.prepareStatement(sql)){
            pre.setInt(1, comm.getUser_id());
            pre.setInt(2, comm.getBlog_id());
            pre.setString(3, comm.getContent());
            pre.setInt(4, comm.getLikes());
            
            return pre.executeUpdate();
        }
    }
    
    public int editComment(Comment comm) throws SQLException{
        String sql = "UPDATE Comment " +
                    "SET content = ? " +
                    "WHERE id = ?";
        
        try(PreparedStatement pre = conn.prepareStatement(sql)){
            pre.setString(1, comm.getContent());
            pre.setInt(2, comm.getId());

            return pre.executeUpdate();
        }
    }
    
    public int deleteComment(int id) throws SQLException{
        String sql = "DELETE FROM Comment WHERE id = ?";
        
        try(PreparedStatement pre = conn.prepareStatement(sql)){
            pre.setInt(1, id);
            
            return pre.executeUpdate();
        }
    }
    
    public Vector<Comment> viewAll() throws SQLException{
        Vector vector = new Vector<>();
        String sql = "SELECT * FROM Comment";
        
        try(PreparedStatement pre = conn.prepareStatement(sql)){
            ResultSet rs = pre.executeQuery();

            while(rs.next()){
                Comment comm = new Comment();
                
                comm.setId(rs.getInt(1));
                comm.setUser_id(rs.getInt(2));
                comm.setUsername(findUsername(rs.getInt(2)));
                comm.setBlog_id(rs.getInt(3));
                comm.setContent(rs.getString(4));
                comm.setLikes(rs.getInt(5));

                vector.add(comm);
            }
        }
        return vector;
    }
    
    public Vector<Comment> findCommentsById(int id) throws SQLException{
        Vector vector = new Vector<>();
        String sql = "SELECT * FROM Comment WHERE id = ?";
        
        try(PreparedStatement pre = conn.prepareStatement(sql)){
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();

            while(rs.next()){
                Comment comm = new Comment();
                
                comm.setId(rs.getInt(1));
                comm.setUser_id(rs.getInt(2));
                comm.setUsername(findUsername(rs.getInt(2)));
                comm.setBlog_id(rs.getInt(3));
                comm.setContent(rs.getString(4));
                comm.setCreate_date(rs.getString(5));
                comm.setLikes(rs.getInt(6));
                comm.setIs_banned(rs.getInt(7));

                vector.add(comm);
            }
        }
        return vector;
    }
    
    public User findUserById(int user_id) throws SQLException{
        String sql = "SELECT * FROM [User] WHERE id = (SELECT user_id FROM Comment WHERE user_id = ?)";

        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setInt(1, user_id);

            try (ResultSet rs = pre.executeQuery()) {
                if (rs.next()) {
                    User user = Mapper.mapRow(rs);
                    return user;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public String findUsername(int user_id) throws SQLException{
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
    
    public String findAvatarByUser_id(int user_id) throws SQLException{
        String sql = "SELECT avatar FROM [User] WHERE id = (SELECT TOP 1 user_id FROM Comment WHERE user_id = ?)";

        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setInt(1, user_id);

            try (ResultSet rs = pre.executeQuery()) {
                if (rs.next()) {
                    return "img/" + rs.getString(1);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
//    public static void main(String[] args) {
//        DAOComment dao = new DAOComment();
//        Comment comm = new Comment(1, 1, "New Edited Content", 0);
//        
//        try {
//            dao.addComment(comm);
//            comm.setId(8);
//            dao.editComment(comm);
//            dao.deleteComment(8);
//            Vector<Comment> vector = dao.viewAll();
//            for(Comment c : vector) System.out.println(c);
//        } catch (SQLException ex) {
//            Logger.getLogger(DAOComment.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
