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
    
    public int addBlog(Blog b) throws SQLException{
        String sql = "INSERT INTO [dbo].[Blog] " +
                    "([user_id], [title], [content], [likes]) " +
                    "VALUES (?, ?, ?, ?)";
        
        try(PreparedStatement pre = conn.prepareStatement(sql)){
            pre.setInt(1, b.getUser_id());
            pre.setString(2, b.getTitle());
            pre.setString(3, b.getContent());
            pre.setInt(4, b.getLikes());
        
            return pre.executeUpdate();
        }
    }
    
    public int editBlog(Blog b) throws SQLException{
        String sql = "UPDATE [dbo].[Blog] " +
                    "SET [title] = ?, " +
                    "[content] = ? " +
                    "WHERE id = ?";
        
        try(PreparedStatement pre = conn.prepareStatement(sql)){
            pre.setString(1, b.getTitle());
            pre.setString(2, b.getContent());
            pre.setInt(3, b.getId());

            return pre.executeUpdate();
        }
    }
    
    public int deleteBlog(int id) throws SQLException{
        String sql = "DELETE FROM Blog WHERE id = ? " +
                    "AND id NOT IN (SELECT blog_id FROM Comment WHERE blog_id = ?) " +
                    "AND id NOT IN (SELECT blog_id FROM Images WHERE blog_id = ?)";
        
        try(PreparedStatement pre = conn.prepareStatement(sql)){
            pre.setInt(1, id);
            pre.setInt(2, id);
            pre.setInt(3, id);

            return pre.executeUpdate();
        }
    }
    
     public Vector<Blog> getAll() throws SQLException {
        Vector<Blog> vector = new Vector<>();
        String sql = "SELECT * FROM Blog";
        
        try(PreparedStatement pre = conn.prepareStatement(sql)){
            ResultSet rs = pre.executeQuery();
        
            while(rs.next()){
                Blog b = new Blog();
                
                b.setId(rs.getInt(1));
                b.setUser_id(rs.getInt(2));
                b.setTitle(rs.getString(3));
                b.setContent(rs.getString(4));
                b.setLikes(rs.getInt(5));

                vector.add(b);
            }
        }
        return vector;
    }
     
     public Blog getBlog(int id) throws SQLException{
         String sql = "SELECT * FROM Blog WHERE id = ?";
         
         try(PreparedStatement pre = conn.prepareStatement(sql)){
             pre.setInt(1,id);
             
             try(ResultSet rs = pre.executeQuery()){
                 if(rs.next()){
                    Blog b = new Blog();
                    
                    b.setId(rs.getInt(1));
                    b.setUser_id(rs.getInt(2));
                    b.setTitle(rs.getString(3));
                    b.setContent(rs.getString(4));
                    b.setLikes(rs.getInt(5));
                    
                    return b;
                 }
                 else{
                     throw new SQLException("No blog found with ID: " + id);
                 }
             }
         }
     }
     
//     public static void main(String[] args) {
//        DAOBlog dao = new DAOBlog();
//        
//        Blog b = new Blog(1, "New Title", "New Content", 0);
//        try {
//            dao.addBlog(b);
//            Blog newB = dao.getBlog(6);
//            newB.setTitle("New Edited Title");
//            dao.editBlog(newB);
//            dao.deleteBlog(6);
//        } catch (SQLException ex) {
//            Logger.getLogger(DAOBlog.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}