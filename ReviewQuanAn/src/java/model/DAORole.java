package model;

import entity.Role;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAORole extends DBConnect{
    public int addRole(Role r) throws SQLException{
        String sql = "INSERT INTO [dbo].[role]\n" +
                    "           ([role_id]\n" +
                    "           ,[role_name])\n" +
                    "     VALUES(?,?)";
        
        PreparedStatement pre = conn.prepareStatement(sql);
        pre.setInt(1, r.getRole_id());
        pre.setString(2, r.getRole_name());
        
        return pre.executeUpdate();
    }
    
    public int editRole(Role r) throws SQLException{
        String sql = "UPDATE Role " +
                    "SET role_name = ? " +
                    "WHERE role_id = ?";
        
        PreparedStatement pre = conn.prepareStatement(sql);
        pre.setString(1, r.getRole_name());
        pre.setInt(2, r.getRole_id());
        
        return pre.executeUpdate();
    }
    
    public int removeRole(Role r) throws SQLException{
        String sql = "DELETE FROM Role WHERE role_id = "+r.getRole_id()+" AND " +
                    ""+r.getRole_id()+" NOT IN (SELECT role_id FROM [User] WHERE role_id = "+r.getRole_id()+")";
        Statement state = conn.createStatement();
        return state.executeUpdate(sql);
    }
    
    public Vector<Role> viewAll() throws SQLException{
        Vector<Role> vector = new Vector<>();
        String sql = "SELECT * FROM Role";
        Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = state.executeQuery(sql);
        
        while(rs.next()){
            Role r = new Role();
            r.setRole_id(rs.getInt(1));
            r.setRole_name(rs.getString(2));
            
            vector.add(r);
        }
        
        return vector;
    }
    
    public static void main(String[] args){
        DAORole dao = new DAORole();
        try {
            dao.removeRole(new Role(4, "New Clown"));
        } catch (SQLException ex) {
            Logger.getLogger(DAORole.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
