package model;

import entity.Store;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOStore extends DBConnect{
    public int addStore(Store st) throws SQLException{
        String sql = "INSERT INTO [dbo].[Store]\n" +
                    "           ([store_name]\n" +
                    "           ,[manager_id]\n" +
                    "           ,[avg_rating])\n" +
                    "     VALUES(?,?,?)";
        PreparedStatement pre = conn.prepareStatement(sql);
        pre.setString(1, st.getStore_name());
        pre.setInt(2, st.getManager_id());
        pre.setFloat(3, st.getAvg_rating());
        return pre.executeUpdate();
    }
    
    public int updateStore(Store st) throws SQLException{
        String sql = "UPDATE [dbo].[Store]\n" +
                    "   SET [store_name] = ?\n" +
                    "      ,[manager_id] = ?\n" +
                    "      ,[avg_rating] = ?\n" +
                    " WHERE store_id = ?";
        PreparedStatement pre = conn.prepareStatement(sql);
        pre.setString(1, st.getStore_name());
        pre.setInt(2, st.getManager_id());
        pre.setFloat(3, st.getAvg_rating());
        pre.setInt(4, st.getStore_id());
        
        return pre.executeUpdate();
    }
    
    public int removeStore(Store st) throws SQLException{ //not working
        String sql = "DELETE FROM Store WHERE store_id = "+st.getStore_id()+" " +
                    "AND "+st.getStore_id()+" NOT IN ((SELECT store_id FROM Comment WHERE store_id = "+st.getStore_id()+") " +
                    "AND (SELECT store_id FROM Menu WHERE store_id = "+st.getStore_id()+"))";
        Statement state = conn.createStatement();
        
        return state.executeUpdate(sql);
    }
    
    public Vector<Store> getAll() throws SQLException{
        Vector vector = new Vector<>();
        String sql = "SELECT * FROM Store";
        Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = state.executeQuery(sql);
        
        while(rs.next()){
            Store st = new Store();
            st.setStore_id(rs.getInt(1));
            st.setStore_name(rs.getString(2));
            st.setManager_id(rs.getInt(3));
            st.setAvg_rating(rs.getFloat(4));
            
            vector.add(st);
        }
        return vector;
    }
    
    public static void main(String[] args){
        DAOStore dao = new DAOStore();
        
        try {
            dao.removeStore(new Store( 4,"My New Store", 1, 1));
        } catch (SQLException ex) {
            Logger.getLogger(DAOStore.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
