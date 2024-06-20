import java.util.List;
import java.sql.Date;
public class Invoice {

        private Integer Invoice_id;
        private Integer Cus_id;
        private Date Date ;
        private Integer sub_total;
        private Double tax_perc  = 0.18;
        private Integer tax ;
        private Integer total_amount;
        private List<Invoiceitems> items;


        public Invoice(Date Date,Integer cus_id,List<Invoiceitems> items)
        {
                this.Cus_id = cus_id;
                this.Date = Date;
                this.items = items;
        }

        public Invoice(Integer Cus_id,Date date,Integer sub_total,Integer tax,Integer total_amount){
                this.Cus_id =Cus_id;
                this.Date = date;
                this.sub_total = sub_total;
                this.tax = tax;
                this.total_amount = total_amount;
        }


        public Double getTax_perc() {return tax_perc;}

        public Integer getSub_total(){return sub_total;}
        public Integer getCus_id(){return Cus_id;}
        public Integer getInvoice_id(){return Invoice_id;}
        public Integer gettax(){return tax;}
        public Date getdate(){return Date;}
        public Integer get_total_amount(){return total_amount;}

        public List<Invoiceitems> getItems() {
                return items;
        }
}
