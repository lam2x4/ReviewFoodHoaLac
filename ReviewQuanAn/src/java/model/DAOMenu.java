package model;

import entity.Menu;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOMenu extends DBConnect{
    public int addMenu(Menu m) throws SQLException{
        String sql = "INSERT INTO [dbo].[Menu]\n" +
                    "           ([menu_id]\n" +
                    "           ,[store_id]\n" +
                    "           ,[menu_image])\n" +
                    "     VALUES(?,?,?)";
        PreparedStatement pre = conn.prepareStatement(sql);
        pre.setInt(1, m.getMenu_id());
        pre.setInt(2, m.getStore_id());
        pre.setString(3, m.getMenu_image());
        return pre.executeUpdate();
    }
    
    public int updateMenu(Menu m) throws SQLException{
        String sql = "UPDATE Menu " +
                    "SET menu_image = ? " +
                    "WHERE menu_id = ? AND store_id = ?";
        PreparedStatement pre = conn.prepareStatement(sql);
        pre.setString(1, m.getMenu_image());
        pre.setInt(2, m.getMenu_id());
        pre.setInt(3, m.getStore_id());
        return pre.executeUpdate();
    }
    
    public int removeMenu(Menu m) throws SQLException{
        String sql = "DELETE FROM Menu " +
                "WHERE menu_id = ? AND store_id = ?";
        PreparedStatement pre = conn.prepareStatement(sql);
        pre.setInt(1, m.getMenu_id());
        pre.setInt(2, m.getStore_id());
        return pre.executeUpdate();
    }
    
    public Vector<Menu> getAll() throws SQLException{
        Vector<Menu> vector = new Vector<>();
        String sql = "SELECT * FROM Menu";
        Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = state.executeQuery(sql);
        
        while(rs.next()){
            Menu m = new Menu();
            m.setMenu_id(rs.getInt(1));
            m.setStore_id(rs.getInt(2));
            m.setMenu_image(rs.getString(3));
            
            vector.add(m);
        }
        return vector;
    }
    
    public Menu getMenu(Menu m) throws SQLException{
        String sql = "SELECT * FROM Menu " +
                "WHERE menu_id = "+m.getMenu_id()+" AND store_id = "+m.getStore_id()+"";
        Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = state.executeQuery(sql);
        
        rs.next();
        Menu _m = new Menu();
        _m.setMenu_id(rs.getInt(1));
        _m.setStore_id(rs.getInt(2));
        _m.setMenu_image(rs.getString(3));
        
        return _m;
    }
}
