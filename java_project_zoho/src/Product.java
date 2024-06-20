public class Product {
    private Integer product_id;
    private Integer rate;
    private String Name;

    public Product(Integer id,String Name,Integer rate)
    {
        this.product_id = id;
        this.rate = rate;
        this.Name = Name;

    }
    public void setProduct_id(Integer id)
    {
        this.product_id = id;
    }

    public void setRate(Integer rate)
    {
        this.rate = rate;
    }

    public void setName(String name)
    {
        this.Name = name;
    }

    public Integer getProduct_id(){return product_id;}
    public Integer getRate(){return rate;}
    public String getName(){return Name;}
}
