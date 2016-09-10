package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnector {
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost/cupcakes?allowMultiQueries=true";
    private static final String user = "cupcakes";
    private static final String password = "cupcakes";
    
    
    private Connection conn = null;
    
    private static DBConnector instance;
    private DBConnector(){

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    
    public static DBConnector getInstance() {
        if(instance == null) {
            instance = new DBConnector();
        }
        return instance;
    }
    
    public Connection getConnection() {
        return conn;
    }
}
