package model;

import entity.User;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOUser extends DBConnect{
    public int addUser(User u) throws SQLException{
        String sql = "INSERT INTO [dbo].[User]\n" +
                    "           ([username]\n" +
                    "           ,[password]\n" +
                    "           ,[email]\n" +
                    "           ,[phone]\n" +
                    "           ,[avatar]\n" +
                    "           ,[gender]\n" +
                    "           ,[description]\n" +
                    "           ,[role_id])\n" +
                    "     VALUES(?,?,?,?,?,?,?,?)";
        
        PreparedStatement pre = conn.prepareStatement(sql);
        pre.setString(1, u.getUsername());
        pre.setString(2, u.getPassword());
        pre.setString(3, u.getEmail());
        pre.setString(4, u.getPhone());
        pre.setString(5, u.getAvatar());
        pre.setInt(6, u.getGender());
        pre.setString(7, u.getDescription());
        pre.setInt(8, u.getRole_id());
        
        return pre.executeUpdate();
    }
    
    public int updateUser(User u) throws SQLException{
        String sql = "UPDATE [dbo].[User]\n" +
                    "   SET [username] = ?\n" +
                    "      ,[password] = ?\n" +
                    "      ,[email] = ?\n" +
                    "      ,[phone] = ?\n" +
                    "      ,[avatar] = ?\n" +
                    "      ,[gender] = ?\n" +
                    "      ,[description] = ?\n" +
                    "      ,[role_id] = ?\n" +
                    " WHERE user_id = ?";
        
        PreparedStatement pre = conn.prepareStatement(sql);
        
        pre.setString(1, u.getUsername());
        pre.setString(2, u.getPassword());
        pre.setString(3, u.getEmail());
        pre.setString(4, u.getPhone());
        pre.setString(5, u.getAvatar());
        pre.setInt(6, u.getGender());
        pre.setString(7, u.getDescription());
        pre.setInt(8, u.getRole_id());
        pre.setInt(9, u.getUser_id());
        
        return pre.executeUpdate();
    }
    
    public int deleteUser(User u) throws SQLException{ //not working
        String sql = "DELETE FROM [User] WHERE user_id = "+u.getUser_id()+" " +
                    "AND "+u.getUser_id()+" NOT IN ((SELECT user_id FROM Comment WHERE user_id = "+u.getUser_id()+") " +
                    "AND (SELECT user_id FROM Store WHERE user_id = "+u.getUser_id()+"))";
        Statement state = conn.createStatement();
        
        return state.executeUpdate(sql);
    }
    
    public Vector<User> getAll() throws SQLException{
        Vector vector = new Vector<>();
        String sql = "SELECT * FROM User";
        Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = state.executeQuery(sql);
        
        while(rs.next()){
            User u = new User();
            u.setUser_id(rs.getInt(1));
            u.setUsername(rs.getString(2));
            u.setPassword(rs.getString(3));
            u.setEmail(rs.getString(4));
            u.setPhone(rs.getString(5));
            u.setGender(rs.getInt(6));
            u.setDescription(rs.getString(7));
            u.setRole_id(rs.getInt(8));
            
            vector.add(u);
        }
        
        return vector;
    }
    
    public User getUser(User u) throws SQLException{
        String sql = "SELECT * FROM User " +
                "WHERE menu_id = "+u.getUser_id()+"";
        Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = state.executeQuery(sql);
        
        User _u = new User();
            _u.setUser_id(rs.getInt(1));
            _u.setUsername(rs.getString(2));
            _u.setPassword(rs.getString(3));
            _u.setEmail(rs.getString(4));
            _u.setPhone(rs.getString(5));
            _u.setGender(rs.getInt(6));
            _u.setDescription(rs.getString(7));
            _u.setRole_id(rs.getInt(8));
        
        return _u;
    }
    
    public static void main(String[] args){
        DAOUser dao = new DAOUser();
        
        try {
            dao.deleteUser(new User(2,"Radik","Malevian", "@email.com", "2131312", "fsdfdsf", 1, "dadasdasd", 1));
        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
