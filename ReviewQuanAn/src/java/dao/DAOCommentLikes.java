package dao;

import dal.DBContext;
import entity.CommentLikes;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class DAOCommentLikes extends DBContext{
    public int addCommentLikes(CommentLikes cl) throws SQLException {
        String sql = "INSERT INTO [dbo].[CommentLikes] "
                + "([blog_id], [user_id], [comment_id], [interaction_type]) "
                + "VALUES (?, ?, ?, ?)";

        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            pre.setInt(1, cl.getBlog_id());
            pre.setInt(2, cl.getUser_id());
            pre.setInt(3, cl.getComment_id());
            pre.setString(4, cl.getInteraction_type());

            return pre.executeUpdate();
        }
    }
    
    public int updateCommentLikes(CommentLikes cl) throws SQLException{
        String sql = "UPDATE CommentLikes " +
                    "SET interaction_type = ? " +
                    "WHERE blog_id = ? AND user_id = ? AND comment_id = ?";
        
        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            pre.setString(1, cl.getInteraction_type());
            pre.setInt(2, cl.getBlog_id());
            pre.setInt(3, cl.getUser_id());
            pre.setInt(4, cl.getComment_id());

            return pre.executeUpdate();
        }
    }

    public int deleteCommentLikes(int blog_id, int user_id, int comment_id) throws SQLException {
        String sql = "DELETE FROM CommentLikes WHERE blog_id = ? AND user_id = ? AND comment_id = ?";

        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            pre.setInt(1, blog_id);
            pre.setInt(2, user_id);
            pre.setInt(3, comment_id);

            return pre.executeUpdate();
        }
    }
    
    public CommentLikes getCommentLikes(int blog_id, int user_id, int comment_id) throws SQLException{
        String sql = "SELECT * FROM CommentLikes WHERE blog_id = ? AND user_id = ? AND comment_id = ?";
        
        try(PreparedStatement pre = connection.prepareStatement(sql)){
            pre.setInt(1, blog_id);
            pre.setInt(2, user_id);
            pre.setInt(3, comment_id);
            
            try(ResultSet rs = pre.executeQuery()){
                if(rs.next()){
                    CommentLikes cl = new CommentLikes();
                    
                    cl.setBlog_id(rs.getInt(1));
                    cl.setUser_id(rs.getInt(2));
                    cl.setComment_id(rs.getInt(3));
                    cl.setInteraction_type(rs.getString(4));
                    
                    return cl;
                }
                else{
                    return null;
                }
            }
        }
    }
}
