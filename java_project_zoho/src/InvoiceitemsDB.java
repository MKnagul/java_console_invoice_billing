import java.sql.Connection;
import java.sql.PreparedStatement;

public class InvoiceitemsDB {

    public void addinvoiceitemsdb(Invoiceitems a)
    {
        String query = "INSERT INTO invoice_items(invoice_id,product_id,quantity,unit_price,total_price) VALUES(?,?,?,?,?)";

        Connection connect = JDBC.getconnection();
        try{
            PreparedStatement statement = connect.prepareStatement(query);
            statement.setInt(1,a.getInvoice_id());
            statement.setInt(2,a.getProduct_id());
            statement.setInt(3,a.getQty());
            statement.setInt(4,a.getUnit_price());
            statement.setInt(5,a.getTotal_price());
            statement.executeUpdate();
        }
        catch (Exception e)
        {
            System.out.println("there is an error in  inserting elemnts to invoice_item table");
            System.out.println(e);

        }
    }
}
