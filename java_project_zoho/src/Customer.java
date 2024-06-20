public class Customer {
    private Integer cus_id;
    private String Cus_name;
    private String mobile_number;
    private String email;

    public Customer(String Cus_name,String mobile_number,String email)
    {

        this.Cus_name = Cus_name;
        this.mobile_number = mobile_number;
        this.email = email;
    }

    public Customer(Integer id,String Cus_name,String mobile_number,String email)
    {
        this.cus_id = id;
        this.Cus_name = Cus_name;
        this.mobile_number = mobile_number;
        this.email = email;
    }


    public Integer getCus_id(){return cus_id;}
    public String getCus_name(){return Cus_name;}
    public String getmobile_number(){return mobile_number;}
    public String getemail(){return email;}

}
