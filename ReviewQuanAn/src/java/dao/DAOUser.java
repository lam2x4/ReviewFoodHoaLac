package dao;

import dal.DBContext;
import entity.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOUser extends DBConnect {
    
    public int addUser(User u) throws SQLException{
        String sql = "INSERT INTO [dbo].[User] " +
                 "([username], [password], [email], [phone], [avatar], [gender], [description], [verify_status], [role_id]) " +
                 "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try(PreparedStatement pre = conn.prepareStatement(sql)){
            pre.setString(1, u.getUsername());
            pre.setString(2, u.getPassword());
            pre.setString(3, u.getEmail());
            pre.setString(4, u.getPhone());
            pre.setString(5, u.getAvatar());
            pre.setInt(6, u.getGender());
            pre.setString(7, u.getDescription());
            pre.setInt(8, u.getVerify_status());
            pre.setInt(9, u.getRole_id());

            return pre.executeUpdate();
        }
    }

    public int updateUser(User u) throws SQLException{
        String sql = "UPDATE [dbo].[User] " +
                    "SET [username] = ?, " +
                    "[password] = ?, " +
                    "[email] = ?, " +
                    "[phone] = ?, " +
                    "[avatar] = ?, " +
                    "[gender] = ?, " +
                    "[description] = ?, " +
                    "[verify_status] = ?, " +
                    "[role_id] = ? " +
                    "WHERE id = ?";
        
        try(PreparedStatement pre = conn.prepareStatement(sql)){
            pre.setString(1, u.getUsername());
            pre.setString(2, u.getPassword());
            pre.setString(3, u.getEmail());
            pre.setString(4, u.getPhone());
            pre.setString(5, u.getAvatar());
            pre.setInt(6, u.getGender());
            pre.setString(7, u.getDescription());
            pre.setInt(8, u.getVerify_status());
            pre.setInt(9, u.getRole_id());
            pre.setInt(10, u.getId());

            return pre.executeUpdate();
        }
    }
    
    public int deleteUser(int id) throws SQLException{
        String sql = "DELETE FROM [User] WHERE id = ? " +
                    "AND id NOT IN (SELECT user_id FROM Comment WHERE user_id = ?) " +
                    "AND id NOT IN (SELECT user_id FROM Blog WHERE user_id = ?)";
        
        try(PreparedStatement pre = conn.prepareStatement(sql)){
            pre.setInt(1, id);
            pre.setInt(2, id);
            pre.setInt(3, id);

            return pre.executeUpdate();
        }
    }
    
    public Vector<User> getAll() throws SQLException {
        Vector vector = new Vector<>();
        String sql = "SELECT * FROM [User]";
        
        try(PreparedStatement pre = conn.prepareStatement(sql)){
            ResultSet rs = pre.executeQuery();
            
            while(rs.next()){
                User u = new User();
                
                u.setId(rs.getInt(1));
                u.setUsername(rs.getString(2));
                u.setPassword(rs.getString(3));
                u.setEmail(rs.getString(4));
                u.setPhone(rs.getString(5));
                u.setAvatar(rs.getString(6));
                u.setGender(rs.getInt(7));
                u.setDescription(rs.getString(8));
                u.setVerify_status(rs.getInt(9));
                u.setRole_id(rs.getInt(10));

                vector.add(u);
            }
        }
        return vector;
    }
    
    public User getUser(int id) throws SQLException{
        String sql = "SELECT * FROM [User] WHERE id = ?";
        
        try(PreparedStatement pre = conn.prepareStatement(sql)){
            pre.setInt(1, id);
            
            try(ResultSet rs = pre.executeQuery()){
                if(rs.next()){
                    User u = new User();
            
                    u.setId(rs.getInt(1));
                    u.setUsername(rs.getString(2));
                    u.setPassword(rs.getString(3));
                    u.setEmail(rs.getString(4));
                    u.setPhone(rs.getString(5));
                    u.setAvatar(rs.getString(6));
                    u.setGender(rs.getInt(7));
                    u.setDescription(rs.getString(8));
                    u.setVerify_status(rs.getInt(9));
                    u.setRole_id(rs.getInt(10));
        
                    return u;
                }
                else{
                    throw new SQLException("User with ID "+id+" not found.");
                }
            }
        }
    }
    
//    public static void main(String[] args) {
//        DAOUser dao = new DAOUser();
//        User u = new User("New Username", "New Password", "New Email", "New Phone", "New Avatar", 1, "New Description", 1, 1);
//        try {
//            User newU = dao.getUser(5);
//            newU.setUsername("New Edited Username");
//            dao.updateUser(newU);
//            dao.addUser(u);
//            dao.deleteUser(5);
//            Vector<User> vector = dao.getAll();
//            for(User _u : vector) System.out.println(_u);
//        } catch (SQLException ex) {
//            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
