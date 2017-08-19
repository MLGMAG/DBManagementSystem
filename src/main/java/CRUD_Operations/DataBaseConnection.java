package CRUD_Operations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * Created by Mag on 17.08.2017.
 *
 */
public class DataBaseConnection {
    private static  final String DataBaseName = "MyDataBase";
    private static final String URL = "jdbc:mysql://localhost:3306/MyDataBase?autoReconnect=true&useSSL=false";
    private static final String UserName = "root";
    private static final String Password = "root";

    private Connection connection;

    public DataBaseConnection(){
        try {
            connection = DriverManager.getConnection(URL,UserName,Password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static String getDataBaseName() {
        return DataBaseName;
    }

    public static String getURL() {
        return URL;
    }

     Connection getConnection(){
        return connection;
    }

    public void ConnectionStatus(){
        try {
            if (!connection.isClosed()){
                System.out.println("Connection successfully");
            }else {
                System.out.println("Connection down");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
