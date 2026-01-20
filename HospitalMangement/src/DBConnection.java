import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
 private static Connection Con;

 public static Connection getConnection() {
    try {
        if (Con==null || Con.isClosed()) {           
           String url = "jdbc:mysql://localhost:3306/hospital_management";
           String username = "root";
           String password = "ruthran@2718";
           Con=DriverManager.getConnection(url, username, password);
           return Con;
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return Con;
 }
  
}
