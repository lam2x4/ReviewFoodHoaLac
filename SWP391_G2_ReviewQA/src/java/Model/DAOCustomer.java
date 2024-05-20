/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Entity.User;
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
public class DAOCustomer extends DBConnect {

    public Vector<User> getAll(String sql) {
        Vector<User> vector = new Vector<>();
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String account = rs.getString(3);
                String password = rs.getString(4);
                int role = rs.getInt(5);
                String address = rs.getString(6);
                String phone = rs.getString(7);

                User user = new User(id, account, password, role, address, phone);
                vector.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public static void main(String[] args) {
        DAOCustomer dao = new DAOCustomer();
        Vector vector = dao.getAll("Select * from [user]");
        for (int i = 0; i < vector.size(); i++) {
            System.out.println(vector.get(i).toString());
        }
    }
}
