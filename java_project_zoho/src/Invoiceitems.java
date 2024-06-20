public class Invoiceitems {
    private Integer invoice_no;
    private Integer  Invoice_id;
    private Integer Product_id;
    private Integer Qty;
    private Integer  unit_price;
    private Integer  total_price;

    Invoiceitems(Integer product_id,Integer qty,Integer unit_price,Integer total_price)
    {
        this.Product_id = product_id;
        this.Qty = qty;
        this.unit_price = unit_price;
        this.total_price = total_price;
    }



    Invoiceitems(Integer invoice_id,Integer product_id,Integer qty,Integer unit_price,Integer total_price)
    {
        this.Invoice_id = invoice_id;
        this.Product_id = product_id;
        this.Qty = qty;
        this.unit_price = unit_price;
        this.total_price = total_price;
    }


    public void setInvoice_no(Integer num){this.invoice_no = num;}
    public void setInvoice_id(Integer id){this.Invoice_id = id;}
    public void setProduct_id(Integer p_id){this.Product_id = p_id;}
    public void setQty(Integer qt){this.Qty = qt;}
    public void setUnit_price(Integer a){this.unit_price = a;}
    public void setTotal_price(Integer a){this.total_price = a;}

    public Integer getInvoice_no(){return this.invoice_no;}
    public Integer getInvoice_id(){return this.Invoice_id;}
    public Integer getProduct_id(){return this.Product_id;}
    public Integer getQty(){return this.Qty;}
    public Integer getUnit_price(){return this.unit_price;}
    public Integer getTotal_price(){return this.total_price;}
}
