package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class conexion {
    public static Connection getConexion(){
        
        Connection connection = null;
        
        try {
            String driverClassName = "com.mysql.jdbc.Driver";
            String driverUrl = "jdbc:mysql://localhost/rrhh_bd";
            Class.forName(driverClassName);
            connection = DriverManager.getConnection(driverUrl, "root","");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return connection;
    }    
}