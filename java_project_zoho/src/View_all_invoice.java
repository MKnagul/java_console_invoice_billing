import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class View_all_invoice {

    public void show_invoices()
    {
        String query = "SELECT a.invoice_date,a.invoice_id,b.name,a.total_amount FROM invoice a JOIN customer b ON a.customer_id=b.customer_id";
        Connection connect = JDBC.getconnection();
      try{
          PreparedStatement statement = connect.prepareStatement(query);
          ResultSet result = statement.executeQuery();
          System.out.println("DATE | INVOICE_ID | CUS_NAME | BILL_AMOUNT");
          while(result.next())
          {
              System.out.println(result.getDate(1) + " | " + result.getInt(2) + " | " + result.getString(3) + " | " + result.getInt(4));
          }
          System.out.println("------------------------------------------------------------------------------");
      }
      catch(Exception e)
      {
          System.out.println("Error in retreving data from the database");
          System.out.println(e);
      }

    }
}
