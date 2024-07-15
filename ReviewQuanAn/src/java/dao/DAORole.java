package dao;

import Utility.Mapper;
import dal.DBContext;
import entity.Role;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAORole extends DBContext {

    public int addRole(Role r) throws SQLException {
        String sql = "INSERT INTO [dbo].[role] "
                + "([role_id], [role_name]) "
                + "VALUES (?,?)";

        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            Mapper.setRowAddRole(r, pre);

            return pre.executeUpdate();
        }
    }

    public int editRole(Role r) throws SQLException {
        String sql = "UPDATE Role "
                + "SET role_name = ? "
                + "WHERE role_id = ?";

        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            Mapper.setRowEditRole(r, pre);

            return pre.executeUpdate();
        }
    }

    public int removeRole(int id) throws SQLException {
        String sql = "DELETE FROM Role WHERE role_id = ? AND "
                + "role_id NOT IN (SELECT role_id FROM [User] WHERE role_id = ?)";

        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setInt(1, id);
            pre.setInt(2, id);

            return pre.executeUpdate();
        }
    }

    public Vector<Role> viewAll() throws SQLException {
        Vector<Role> vector = new Vector<>();
        String sql = "SELECT * FROM Role";

        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                Role r = Mapper.mapRowRole(rs);

                vector.add(r);
            }
        }
        return vector;
    }

//    public static void main(String[] args){
//        DAORole dao = new DAORole();
//        try {
//            dao.addRole(new Role(3, "New Role"));
//            dao.editRole(new Role(3, "New Edited"));
//            dao.removeRole(3);
//            Vector<Role> vector = dao.viewAll();
//            for(Role r : vector) System.out.println(r);
//        } catch (SQLException ex) {
//            Logger.getLogger(DAORole.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
