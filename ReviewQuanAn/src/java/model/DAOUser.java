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

/**
 *
 * @author ACER
 */
public class DAOUser extends DBContext {

    public Vector<User> getAll(String sql) {
        Vector<User> vector = new Vector<>();
        try {
            Statement state = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int user_id = rs.getInt(1);
                String user_name = rs.getString(2);
                String password = rs.getString(3);
                String email = rs.getString(4);
                String phone = rs.getString(5);
                String avatar = rs.getString(6);
                int gender = rs.getByte(7);
                String description = rs.getString(8);
                int role_id = rs.getInt(9);

                User user = new User(user_id, user_name, password, email, phone, avatar, gender, description, role_id);
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
        return n;

    }
    

//    public static void main(String[] args) {
//        DAOUser dao = new DAOUser();
//        User user = new User("a", "a", "a", "1", "", 0, "", 3);
//        int n = dao.addUser(user);
//        Vector vector = dao.getAll("Select * from [user]");
//        for (int i = 0; i < vector.size(); i++) {
//            System.out.println(vector.get(i).toString());
//        }
//    }
}
