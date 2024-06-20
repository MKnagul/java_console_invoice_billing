import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Maximum_selling {

    public static void maximum_buying_product(Integer month,Integer year) {
        String query = "SELECT c.customer_id,c.name,SUM(i.total_amount) as total_amount  FROM invoice i JOIN customer c on c.customer_id = i.customer_id\n" +
                " WHERE MONTH(i.invoice_date) = ? AND YEAR(i.invoice_date) = ?\n" +
                "GROUP BY c.customer_id ORDER BY total_amount desc";

        Connection connect = JDBC.getconnection();
        try
        {
            PreparedStatement statement = connect.prepareStatement(query);
            statement.setInt(1,month);
            statement.setInt(2,year);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet == null)
            {
                System.out.println("there is no customer in that month");
            }
            else
            {
            System.out.println("Customer_id | Customer_Name | Total_amount");
            while(resultSet.next())
            {
                System.out.println(resultSet.getInt(1) + " | " + resultSet.getString(2) + " | " + resultSet.getInt(3));
            }
            System.out.println("------------------------------------------------------------------------------");
        }}
        catch (Exception e)
        {
            System.out.println("error in getting output in maximum billing customer");
        }
    }

    public static void maximum_selling_product(Integer month,Integer year)
    {
        String query = "SELECT \n" +
                "    p.product_id,\n" +
                "    p.product_name,\n" +
                "    SUM(ii.quantity) AS total_quantity\n" +
                "FROM \n" +
                "    PRODUCT p\n" +
                "JOIN \n" +
                "    INVOICE_ITEMS ii ON p.product_id = ii.product_id\n" +
                "JOIN \n" +
                "    INVOICE i ON ii.invoice_id = i.invoice_id\n" +
                "WHERE \n" +
                "    MONTH(i.invoice_date) = ? AND YEAR(i.invoice_date) = ?\n" +
                "GROUP BY \n" +
                "    p.product_id,\n" +
                "    p.product_name\n" +
                "ORDER BY \n" +
                "    total_quantity DESC\n" +
                "LIMIT 3;\n";
        Connection connect = JDBC.getconnection();
        try
        {
            PreparedStatement statement = connect.prepareStatement(query);
            statement.setInt(1,month);
            statement.setInt(2,year);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet == null)
            {
                System.out.println("there is no products sold in this month ");
            }
            else {
            System.out.println("Product_id  | Product_Name | Total_quantity");
            while(resultSet.next())
            {
                System.out.println(resultSet.getInt(1) + " | " + resultSet.getString(2) + " | " + resultSet.getInt(3));
            }}
            System.out.println("----------------------------------------------------------------------------------");
        }
        catch (Exception e)
        {
            System.out.println("error in getting output from maximum selling price");
            System.out.println(e);
        }
    }
}
