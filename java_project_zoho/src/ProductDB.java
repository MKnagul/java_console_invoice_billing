import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDB {

    public void addproducts(Product product)
    {
        try {

            Connection connect = JDBC.getconnection();
            String query = "Insert into product(product_id,product_name,Rate) VALUES(?,?,?)";

            PreparedStatement statement = connect.prepareStatement(query);
            statement.setInt(1, product.getProduct_id());
            statement.setString(2, product.getName());
            statement.setInt(3,product.getRate());
            statement.executeUpdate();
            System.out.println("Product Sucessfully added");

        }
        catch(Exception e)
        {
            System.out.println("error in productdb");
            System.out.println(e);
        }
    }

    public List<Product> getproducts()
    {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM Product";
        try{
                Connection connect = JDBC.getconnection();
                PreparedStatement statement = connect.prepareStatement(query);
                try
                {
                    ResultSet result = statement.executeQuery();
                    while(result.next())
                    {
                        Integer id = result.getInt(1);
                        String name = result.getString(2);
                        Integer rate = result.getInt(3);
                        products.add(new Product(id,name,rate));
                    }
                }
                catch (Exception e)
                {
                    System.out.println("error in product getting");
                }

        }
        catch(Exception e)
        {
            System.out.println("error in getting products from the data base");
        }
        return products;
    }

    public Product getproductbyId(Integer id)
    {
        String query = "SELECT * FROM Product WHERE product_id = ?";
        try
        {
            Connection connect = JDBC.getconnection();
            PreparedStatement statement = connect.prepareStatement(query);
            statement.setInt(1,id);
            ResultSet result = statement.executeQuery();
            while(result.next())
            {
                Integer product_id = result.getInt(1);
                String product_name = result.getString(2);
                Integer product_rate = result.getInt(3);
                return new Product(product_id,product_name,product_rate);
            }
        }
        catch (Exception e)
        {
            System.out.println("there is an error in getting the product by id from database");
        }
        return null;
    }




}
