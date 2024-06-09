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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.mindrot.jbcrypt.BCrypt;

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
                = "INSERT INTO [User] "
                + "(username,[password],email,phone,avatar,gender,[description],create_date,verify_status,role_id)VALUES(?,?,?,?,?,?,?,?,?,?)";

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
                + "[description] = ?, [create_date] = ?, [verify_status] = ?, [role_id] = ? WHERE [id] = ?";
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

    public User getUser(String email) {
        String sql = "SELECT * FROM [user] WHERE email = ?";
        User user = null;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = Mapper.mapRow(rs);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, "Error fetching user by email: " + email, ex);
            // Consider throwing a custom exception here or handle the error appropriately
        }
        return user;
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
    
    public int getUser_id(String username){
         String sql = "SELECT id FROM [User] WHERE username = ?";

        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            pre.setString(1, username);

            try (ResultSet rs = pre.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public int deleteUser(int user_id) {
        String sql = """
                 DELETE FROM [User]
                 WHERE id = ?
                 AND id NOT IN (SELECT [user_id] FROM Blog)
                 AND id NOT IN (SELECT [user_id] FROM Images)
                 AND id NOT IN (SELECT [user_id] FROM Comment)
                 AND id NOT IN (SELECT [user_id] FROM Draft);""";

        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            pre.setInt(1, user_id);
            int rowsAffected = pre.executeUpdate();
            return rowsAffected; // return the number of rows affected
        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public static void main(String[] args) {
        DAOUser dao = new DAOUser();
        Vector<User> vector = dao.getAll();
        for (User user : vector) {
            System.out.println(user.toString());
        }
        /*
        LocalDate create_date = LocalDate.now();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String date = create_date.format(dateFormat);
        System.out.println(date);*/

    }

    public static boolean checkPassword(String password) {
        if (password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")) {
            return true;
        }
        return false;
    }

}
