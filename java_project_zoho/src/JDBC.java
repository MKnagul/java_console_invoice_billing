import java.sql.DriverManager;
import java.sql.Connection;


public class JDBC
{
    public static Connection getconnection()
            {
                Connection connect = null;
                try{
                        connect = DriverManager.getConnection(
                            "jdbc:mysql://127.0.0.1:3306/invoice",
                            "root",
                            "Appa@0987"
                    );
                }
                catch(Exception e)
                {
                    System.out.print("error occured in connection");
                }

                return connect;
            }
}
