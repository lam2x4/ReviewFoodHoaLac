/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.User;
import dal.DBContext;
import entity.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;

/**
 *
 * @author ACER
 */
public class DAOUser extends DBContext {

    public Vector<User> getAll(String sql) {
        Vector<User> vector = new Vector<>();
        try (Statement state = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE); ResultSet rs = state.executeQuery(sql)) {
            while (rs.next()) {
                User user = Mapper.mapRow(rs);
                vector.add(user);
            }
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    
    public int addUser(User user){
        int n = 0;
        String sql = "INSERT INTO [User] (username, [password], email, phone, avatar, gender, [description], role_id)\n" +
"VALUES('" + user.getUse_rname()+"','" + 
                user.getPassword()+ "','" + user.getEmail()+ "','" + 
                user.getPhone()+ "','" + user.getAvatar()+ "','" +
                user.getGender()+ "','" + user.getDescription()+ "','" +
                user.getRole_id()+"')";
        
        System.out.println(sql);
        try {
            Statement state = connection.createStatement();
            n = state.executeUpdate(sql);
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public int updateUser(User user) {
        String sql = "UPDATE [dbo].[User] SET [username] = ?, [password] = ?, [email] = ?, [phone] = ?, [avatar] = ?, [gender] = ?, "
                + "[description] = ?, [verify_status] = ?, [role_id] = ? WHERE [id] = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            Mapper.setRowUpdate(user, pstmt);
            pstmt.executeUpdate();
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }
        return 0;
    }

    public static void main(String[] args) {
        DAOUser dao = new DAOUser();
//        User user = new User("a", "a", "a", "1", "", 3, "", 0, 1);
//        int n = dao.addUser(user);
        Vector vector = dao.getAll("Select * from [user]");
        for (int i = 0; i < vector.size(); i++) {
            System.out.println(vector.get(i).toString());
        }
//        System.out.println("==============================================");
//        User user1 = (User)vector.get(vector.size()-1);
//        user1.setVerify_status(0);
//        dao.updateUser(user1);
//        
//        vector = dao.getAll("Select * from [user]");
//        for (int i = 0; i < vector.size(); i++) {
//            System.out.println(vector.get(i).toString());
//        }
    }
}
