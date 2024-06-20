import java.time.format.DateTimeFormatter;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner scan = new Scanner(System.in);

    public static void main(String args[])
    {
        boolean loop = true;
        while(loop)
        {
            System.out.println("1.Add New Product");
            System.out.println("2.Create Invoice");
            System.out.println("3.View All Invoices");
            System.out.println("4.Maximum selling product in this month");
            System.out.println("5.Maximum buying customer in this month");
            int inp = scan.nextInt();
            System.out.println("----------------------------------------------------------------------");
            switch(inp)
            {
                case 1 -> addnewproducts();
                case 2 -> addnewinvoice();
                case 3 -> viewallinvoices();
                case 4 -> maximumsellingproduct();
                case 5 -> maximumbuyingcustomer();
            }
        }

    }
    private static void maximumbuyingcustomer()
    {
        Maximum_selling ms = new Maximum_selling();
        System.out.println("Enter the month you want the report:");
        Integer month = scan.nextInt();
        System.out.println("Enter the year you want the report : ");
        Integer year = scan.nextInt();
        ms.maximum_buying_product(month,year);
    }
    private static  void maximumsellingproduct()
    {
        Maximum_selling ms = new Maximum_selling();
        System.out.println("Enter the month you want your report:");
        Integer month = Integer.valueOf(scan.nextInt());
        System.out.println("Enter the year you want your report");
        Integer year = Integer.valueOf(scan.nextInt());
        ms.maximum_selling_product(month,year);
    }
    private static void viewallinvoices()
    {
        View_all_invoice view = new View_all_invoice();
        view.show_invoices();

    }


    private static void addnewproducts()
    {
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Product id : ");
        Integer id = Integer.valueOf(scan.next());
        System.out.println("Product name : ");
        String name = scan.next();
        System.out.println("Rate");
        Integer rate = Integer.valueOf((scan.next()));

        ProductDB pdb = new ProductDB();
        pdb.addproducts(new Product(id,name,rate));
        System.out.println("----------------------------------------------------------------------");

    }

    private static Integer addnewcustomer()
    {
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Customer Name : ");
        String name = scan.next();
        System.out.println("Mobile Number : " );
        String mobile_number =scan.next();
        System.out.println("Email : ");
        String email = scan.next();
        CustomerDB cus = new CustomerDB();
        Customer customer = cus.searchcustomer(mobile_number);
        if(customer == null)
        {
            System.out.println("New Customer");
            cus.addcustomer(new Customer(name,mobile_number,email));
            System.out.println("----------------------------------------------------------------------");

        }
        else
        {

            System.out.println("Customer already existed");
            System.out.println("----------------------------------------------------------------------");
            return customer.getCus_id();

        }
        Customer customer1 = cus.searchcustomer(mobile_number);
        return customer1.getCus_id();

    }

    private static void addnewinvoice()
    {
        Integer cus_id = addnewcustomer();
        System.out.println("Date : ");
        String Date = scan.next();
        List<Invoiceitems> it = new ArrayList<>();


        boolean loop = true;
        while(loop)
        {
            System.out.println("----------------------------------------------------------------------");
            System.out.println("Select any of the products : ");
            Product sel_prod = displayproducts();
            if(sel_prod != null)
            {
                System.out.println("Type the quantity : ");
                Integer Qty = scan.nextInt();
                it.add(new Invoiceitems(sel_prod.getProduct_id(),Qty, sel_prod.getRate(), sel_prod.getRate()*Qty));
                System.out.println("Shopping Ended Press 3");
                int exit = scan.nextInt();
                if(exit == 3)
                {
                    loop = false;
                }
            }
        }
        Invoice invoice =  new Invoice(changetodate(Date),cus_id,it);
        Integer invoice_id = addinvoicetodb(invoice);
        add_invoice_items_to_db(invoice_id,it);
        System.out.println("to Print invoice press 1");
        int a = scan.nextInt();
        if(a == 1)
        {
            printinvoice(invoice,it);
        }
    }

    private static void printinvoice(Invoice inv,List<Invoiceitems> items)
    {
        int s_no = 1;
        System.out.println("S.No | Items | Rate | Qty | Amount");
        for(Invoiceitems item : items)
        {
            ProductDB pd = new ProductDB();
            Product pr = pd.getproductbyId(item.getProduct_id());
            System.out.println((s_no++) + " | " +  pr.getName() + " | " + pr.getRate() + " | " + item.getQty() + " | " + item.getTotal_price());

        }

        System.out.println("Tax percentage: " + inv.getTax_perc()*100);
        System.out.println("Total amount : "+ calculate_tax_amount(inv,calculate_total_amount(items)));
        System.out.println("Total amount : " + calculate_total_amount(items));
        System.out.println("----------------------------------------------------------------------");
    }

    private static void add_invoice_items_to_db(Integer invoice_id,List<Invoiceitems> items)
    {
        InvoiceitemsDB it = new InvoiceitemsDB();
        for(Invoiceitems item : items)
        {
            it.addinvoiceitemsdb(new Invoiceitems(invoice_id,item.getProduct_id(),item.getQty(),item.getUnit_price(),item.getTotal_price()));
        }
    }

    private static Date changetodate(String date)
    {
        DateTimeFormatter input = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        LocalDate date1 = LocalDate.parse(date,input);

        return Date.valueOf(date1);

    }

    private static Product displayproducts()
    {
        ProductDB pd = new ProductDB();
        List<Product> product = pd.getproducts();
        Integer last_id = -1;
        Integer selected_id = -1;
        for(Product p : product)
        {
            System.out.println(p.getProduct_id()+"|"+p.getName()+"|" + p.getRate() + "|");
            last_id = p.getProduct_id();
        }

        if(last_id > 0)
        {
            selected_id = scan.nextInt();
            if(selected_id <= last_id && selected_id >0)
            {
                return pd.getproductbyId(selected_id);
            }
            else {
                System.out.println("Please select the correct product_id");

            }
        }
        return null;
    }

    private static Integer addinvoicetodb(Invoice in)
    {
        Date date = in.getdate();
        Integer cus_id = in.getCus_id();
        List<Invoiceitems> items = in.getItems();
        Integer sub_total = calculate_total_amount(items);
        Integer tax_amount  = calculate_tax_amount(in,sub_total);
        Integer total_amount = sub_total + tax_amount;

        InvoiceDB db = new InvoiceDB();
        Integer invoice_id = db.addInvoice(new Invoice(cus_id,date,sub_total,tax_amount,total_amount));
        return invoice_id;
    }

    private static Integer calculate_total_amount(List<Invoiceitems> items)
    {
        Integer total = 0;
        for(Invoiceitems item : items)
        {
            total = total + item.getQty() * item.getUnit_price();
        }
        return total;
    }

    private static Integer calculate_tax_amount(Invoice in , Integer total_amount)
    {
        Integer tax_amount = Integer.valueOf((int)(in.getTax_perc()*total_amount));
        return tax_amount;
    }
}
