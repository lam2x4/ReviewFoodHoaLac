package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    public Connection conn = null;
    
    public DBConnect(String url, String username, String password){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("connected");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public DBConnect(){
        this("jdbc:sqlserver://localhost:1433;databaseName=SE1825_SWP391_G2_DB2", "sa", "123456");
    }
    
    public static void main(String[] args){
        new DBConnect();
    }
}
