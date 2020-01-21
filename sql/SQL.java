
import java.sql.*;
import acm.program.*;

public class SQL extends ConsoleProgram
{

    public void run()
    {
        if (getConnection() != null) {
            println("connected");
        } else {
            println("failure to connect");
        }
    }
    
    public static Connection getConnection() {
        try {
            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://localhost:8888/unityaccess";
            String username = "root";
            String password = "root";
            Class.forName(driver);

            Connection con = DriverManager.getConnection(url, username, password);
            return con;
        } catch (Exception e) {
            
        }
        return null;
    }


}
