/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dal.DBContext;
import entity.Store;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author Admin
 */
public class DAOStore extends DBContext {

    public Vector<Store> getAllStore() {
        Vector<Store> storelist = new Vector();
        String sql = "select * from Store where 1=1";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Store store = new Store(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getFloat(4));
                storelist.add(store);

            }
            connection.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        if (storelist.isEmpty()) {
            return null;
        } else {
            return storelist;
        }
    }

    public static void main(String[] args) {
        DAOStore dao = new DAOStore();
        System.out.println(dao.getAllStore().get(2).getStore_name());
    }
}
