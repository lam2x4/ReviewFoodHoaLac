
package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;


public class DBContext {
    protected Connection connection;
    public DBContext()
    {
        try {
            // Edit URL , username, password to authenticate with your MS SQL Server
            String url = "jdbc:sqlserver://localhost:1433;databaseName=SE1825_SWP391_G2_DB3";
            String username = "sa";
            String password = "123";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
    }
    public static void main(String[] args) {
        new DBContext();
    }
    
}
