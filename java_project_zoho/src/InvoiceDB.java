import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class InvoiceDB {

    public Integer addInvoice(Invoice inv)
    {
        Connection connect = JDBC.getconnection();
        String query = "INSERT INTO invoice(customer_id,invoice_date,total_without_tax,tax_amount,total_amount,tax) VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement statement = connect.prepareStatement(query);
            statement.setInt(1,inv.getCus_id());
            statement.setDate(2,inv.getdate());
            statement.setInt(3,inv.getSub_total());
            statement.setInt(4,inv.gettax());
            statement.setInt(5,inv.get_total_amount());
            statement.setDouble(6,inv.getTax_perc() * 100);
            statement.executeUpdate();
            System.out.println("Invoice added sucessfully");
        }
        catch (Exception e)
        {
            System.out.println("Error in adding invoice to the database");
            System.out.println(e);
        }
        return search_Invoice_id(inv);
    }

    public Integer search_Invoice_id(Invoice in)
    {
        String query = "SELECT invoice_id FROM invoice WHERE customer_id = ? AND invoice_date = ? AND total_without_tax = ? AND tax_amount = ? AND total_amount = ? AND tax = ?;";
        Connection connect = JDBC.getconnection();
        Integer id = -1;
        try {
            PreparedStatement statement = connect.prepareStatement(query);
            statement.setInt(1,in.getCus_id());
            statement.setDate(2,in.getdate());
            statement.setInt(3,in.getSub_total());
            statement.setInt(4,in.gettax());
            statement.setInt(5,in.get_total_amount());
            statement.setDouble(6,in.getTax_perc()*100);

            try
            {
                ResultSet result = statement.executeQuery();
                while(result.next())
                {
                     id = result.getInt(1);
                }
            }
            catch(Exception e)
            {
                System.out.println("Error in getting id");
                System.out.println(e);
            }

        }
        catch(Exception e)
        {
            System.out.println("Error in retreving invoice_id");
        }
        return id;

    }




}
