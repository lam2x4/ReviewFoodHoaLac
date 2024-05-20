/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TRAN TUAN ANH
 */
public class DBConnect {
    public Connection conn = null;
    public DBConnect(String url, String user, String pass){
        try {
            //Driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //connection
            conn = DriverManager.getConnection(url,user,pass);
            System.out.println("Connected!");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public DBConnect(){
        this("jdbc:sqlserver://localhost:1433;databaseName=SE1825_SWP391_G2_DB1","sa","123456");
    }
    
    public static void main(String[] args){
        new DBConnect();
        
    }
}
