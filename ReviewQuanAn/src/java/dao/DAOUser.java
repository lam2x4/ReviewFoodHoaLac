package dao;

import Utility.Mapper;
import dal.DBContext;
import entity.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import Utility.Mapper;

public class DAOUser extends DBContext {

    public Vector<User> getAll() {
        String sql = "Select * from [user]";
        Vector<User> vector = new Vector<>();
        try (Statement state
                = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE); ResultSet rs
                = state.executeQuery(sql)) {
            while (rs.next()) {
                User user = Mapper.mapRow(rs);
                vector.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(dao.DAOUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public int addUser(User user) {
        String sql
                = "INSERT INTO [User] (username,[password],email,phone,avatar,gender,[description],verify_status,role_id)VALUES(?,?,?,?,?,?,?,?,?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            Mapper.setRowAdd(user, pstmt);
            pstmt.executeUpdate();
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
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

    public User getUser(int id) {
        String sql = "SELECT * FROM [User] WHERE id = ?";

        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            pre.setInt(1, id);

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
    public static void main(String[] args) {
        DAOUser dao = new DAOUser();
        Vector<User> vector = dao.getAll();
        for(User user: vector){
            System.out.println(user.toString());
        }
        
    }
}
