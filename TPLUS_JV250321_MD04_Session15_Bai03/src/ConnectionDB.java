


import java.sql.Connection;
import java.sql.DriverManager;
import com.mysql.cj.jdbc.Driver;

public class ConnectionDB {
    private static Connection connection = null;
    private static final String url = "jdbc:mysql://localhost:3306/btvn_2?createDatabaseIfNotExist=true&useSSL=false";

    private static final String username = "root";
    private static final String password = "12345678";


    public static Connection openConnection() throws Exception {
        if (connection == null || connection.isClosed()) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection =  DriverManager.getConnection(url, username, password);
        }
        return connection;
    }
}


