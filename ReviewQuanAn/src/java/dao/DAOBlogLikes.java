package dao;

import dal.DBContext;
import entity.BlogLikes;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOBlogLikes extends DBContext {

    public int addBlogLikes(BlogLikes bl) throws SQLException {
        String sql = "INSERT INTO [dbo].[BlogLikes] "
                + "([user_id], [blog_id], [interaction_type]) "
                + "VALUES (?, ?, ?)";

        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            pre.setInt(1, bl.getUser_id());
            pre.setInt(2, bl.getBlog_id());
            pre.setString(3, bl.getInteraction_type());

            return pre.executeUpdate();
        }
    }
    
    public int updateBlogLikes(BlogLikes bl) throws SQLException{
        String sql = "UPDATE BlogLikes " +
                    "SET interaction_type = ? " +
                    "WHERE user_id = ? AND blog_id = ?";
        
        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            pre.setString(1, bl.getInteraction_type());
            pre.setInt(2, bl.getUser_id());
            pre.setInt(3, bl.getBlog_id());

            return pre.executeUpdate();
        }
    }

    public int deleteBlogLikes(int user_id, int blog_id) throws SQLException {
        String sql = "DELETE FROM BlogLikes WHERE user_id = ? AND blog_id = ?";

        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            pre.setInt(1, user_id);
            pre.setInt(2, blog_id);

            return pre.executeUpdate();
        }
    }
    
    public BlogLikes getBlogLikes(int user_id, int blog_id) throws SQLException{
        String sql = "SELECT * FROM BlogLikes WHERE user_id = ? AND blog_id = ?";
        
        try(PreparedStatement pre = connection.prepareStatement(sql)){
            pre.setInt(1, user_id);
            pre.setInt(2, blog_id);
            
            try(ResultSet rs = pre.executeQuery()){
                if(rs.next()){
                    BlogLikes bl = new BlogLikes();
                    
                    bl.setUser_id(rs.getInt(1));
                    bl.setBlog_id(rs.getInt(2));
                    bl.setInteraction_type(rs.getString(3));
                    
                    return bl;
                }
                else{
                    return null;
                }
            }
        }
    }
}
