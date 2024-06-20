import java.sql.*;

public class CustomerDB {

    public void addcustomer(Customer cus)
    {

        String query = "INSERT INTO customer(phone_number,name,email) VALUES (?,?,?)";
        Connection connect = JDBC.getconnection();
        try{
            PreparedStatement statement = connect.prepareStatement(query);
            statement.setString(1,cus.getmobile_number());
            statement.setString(2, cus.getCus_name());
            statement.setString(3,cus.getemail());
            statement.executeUpdate();
            System.out.println("Customer Sucessfully added");

        }
        catch (Exception e) {
            System.out.println("there is an issue with adding customer data");
            System.out.println(e);
        }

    }


    public Customer searchcustomer(String cus)
    {
        String query = "SELECT * FROM customer WHERE phone_number = ?";
        Connection connect = JDBC.getconnection();
        try{
            PreparedStatement statement = connect.prepareStatement(query);
            statement.setString(1, cus);
            try {
                ResultSet result = statement.executeQuery();
                if(result.next())
                {
                    Integer id = result.getInt(1);
                    String name = result.getString(2);
                    String phone_num = result.getString(3);
                    String email = result.getString(4);

                    return new Customer(id,name,phone_num,email);
                }
            }
            catch (Exception e)
            {
                System.out.println("issue with retreving data from customer ");
            }

        }
        catch(Exception e)
        {
            System.out.println("issue in searching");
        }
        return null;
    }
}
