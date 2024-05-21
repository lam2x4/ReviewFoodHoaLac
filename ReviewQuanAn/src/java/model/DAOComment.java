package model;

import entity.Comment;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DAOComment extends DBConnect {
    public int addComment(Comment comm) throws SQLException{
        String sql = "INSERT INTO [dbo].[Comment]\n" +
                    "           ([user_id]\n" +
                    "           ,[store_id]\n" +
                    "           ,[content]\n" +
                    "           ,[rating]\n" +
                    "           ,[likes])\n" +
                    "     VALUES(?,?,?,?,0)";
        PreparedStatement pre = conn.prepareStatement(sql);
        pre.setInt(1, comm.getUser_id());
        pre.setInt(2, comm.getStore_id());
        pre.setString(3, comm.getContent());
        pre.setInt(4, comm.getRating());
        return pre.executeUpdate();
    }
    
    public int editComment(Comment comm) throws SQLException{
        String sql = "UPDATE Comment " +
                    "SET content = ? " +
                    "WHERE user_id = ? AND store_id = ?";
        PreparedStatement pre = conn.prepareStatement(sql);
        pre.setString(1, comm.getContent());
        pre.setInt(2, comm.getUser_id());
        pre.setInt(3, comm.getStore_id());
        
        return pre.executeUpdate();
    }
    
    public int deleteComment(Comment comm) throws SQLException{
        String sql = "DELETE FROM Comment " +
                    "WHERE user_id = ? AND store_id = ?";
        PreparedStatement pre = conn.prepareStatement(sql);
        pre.setInt(1, comm.getUser_id());
        pre.setInt(2, comm.getStore_id());
        return pre.executeUpdate();
    }
    
    public Vector<Comment> viewAll() throws SQLException{
        Vector vector = new Vector<>();
        String sql = "SELECT * FROM Comment";
        Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = state.executeQuery(sql);
        
        while(rs.next()){
            Comment comm = new Comment();
            comm.setUser_id(rs.getInt(1));
            comm.setStore_id(rs.getInt(2));
            comm.setContent(rs.getString(3));
            comm.setRating(rs.getInt(4));
            comm.setLikes(rs.getInt(5));
            
            vector.add(comm);
        }
        return vector;
    }
}
