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
import entity.Blog;
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

    public int getUser_id(String username) {
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
        Vector<Blog> vector = dao.search1("anhtthe182190");
        for (Blog user1 : vector) {
            System.out.println(user1.toString());
        }
    }

    public static boolean checkPassword(String password) {
        if (password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")) {
            return true;
        }
        return false;
    }
    
    public Vector<String> search(String search){
        String sql = """
                     select u.username, b.title, b.content, b.create_date from blog b left join [user] u on b.user_id = u.id
                     """;
        Vector<String> vector = new Vector<>();
        try (Statement state
                = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE); ResultSet rs
                = state.executeQuery(sql)) {
            while (rs.next()) {
                String searchContent = "";
                searchContent += rs.getString(1);
                searchContent += rs.getString(2);
                searchContent += rs.getString(3);
                searchContent += rs.getString(4);
                vector.add(searchContent);
            }
        } catch (SQLException ex) {
            Logger.getLogger(dao.DAOUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    
    public Vector<Blog> search1 (String search){
        String sql = "select u.username, b.* from blog b left join [user] u on b.user_id = u.id";

        Vector<Blog> vector = new Vector<>();
        try (Statement state
                = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE); ResultSet rs
                = state.executeQuery(sql)) {
            while (rs.next()) {
                String username = rs.getString(1);
                int blog_id = rs.getInt(2);
                int user_id = rs.getInt(3);
                String title = rs.getString(4);
                String content = rs.getString(5);
                String create_date = rs.getString(6);
                int likes = rs.getInt(7);
                int is_approved = rs.getInt(8);
                int is_banned = rs.getInt(9);
                Blog blog = new Blog(username, blog_id, user_id, title, content, create_date, likes, is_approved, is_banned);
                String a = username + title + content+ create_date;
                if(a.contains(search)){
                    vector.add(blog);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(dao.DAOUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
            
    }

}
