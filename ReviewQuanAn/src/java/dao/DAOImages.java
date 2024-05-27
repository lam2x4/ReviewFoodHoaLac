package dao;

import entity.Images;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOImages extends DBConnect{
    public int addImages(Images img) throws SQLException{
        String sql = "INSERT INTO [dbo].[Images] " +
                    "([blog_id], [link]) " +
                    "VALUES (?,?)";
        
        try(PreparedStatement pre = conn.prepareStatement(sql)){
            pre.setInt(1, img.getBlog_id());
            pre.setString(2, img.getLink());

            return pre.executeUpdate();
        }
    }
    
    public int updateImages(Images img) throws SQLException{
        String sql = "UPDATE [dbo].[Images] " +
                    "SET [blog_id] = ?, " +
                    "[link] = ? " +
                    "WHERE id = ?";
        
        try(PreparedStatement pre = conn.prepareStatement(sql)){
            pre.setInt(1, img.getBlog_id());
            pre.setString(2, img.getLink());
            pre.setInt(3, img.getId());

            return pre.executeUpdate();
        }
    }
    
    public int removeImages(int id) throws SQLException{
        String sql = "DELETE FROM Images WHERE id = ?";
        
        try(PreparedStatement pre = conn.prepareStatement(sql)){
            pre.setInt(1, id);
            
            return pre.executeUpdate();
        }
    }
    
    public Vector<Images> getAll() throws SQLException{
        Vector<Images> vector = new Vector<>();
        String sql = "SELECT * FROM Images";
        
        try(PreparedStatement pre = conn.prepareStatement(sql)){
            ResultSet rs = pre.executeQuery();

            while(rs.next()){
                Images img = new Images();
                
                img.setId(rs.getInt(1));
                img.setBlog_id(rs.getInt(2));
                img.setLink(rs.getString(3));

                vector.add(img);
            }
        }
        return vector;
    }
    
    public Images getImages(int id) throws SQLException{
        String sql = "SELECT * FROM Images WHERE id = ?";
        
        try(PreparedStatement pre = conn.prepareStatement(sql)){
            pre.setInt(1, id);
            
            try(ResultSet rs = pre.executeQuery()){
                if(rs.next()){
                    Images img = new Images();
                    
                    img.setId(rs.getInt(1));
                    img.setBlog_id(rs.getInt(2));
                    img.setLink(rs.getString(3));
                    
                    return img;
                }
                else{
                    throw new SQLException("No image found with ID: " + id);
                }
            }
        }
    }
    
//    public static void main(String[] args) {
//        DAOImages dao = new DAOImages();
//        Images img = new Images(1, "New Link");
//        
//        try {
//            dao.addImages(img);
//            Images newImg = dao.getImages(6);
//            newImg.setLink("New Edited Link");
//            dao.updateImages(newImg);
//            dao.removeImages(6);
//            Vector<Images> vector = dao.getAll();
//            for(Images i : vector) System.out.println(i);
//        } catch (SQLException ex) {
//            Logger.getLogger(DAOImages.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}