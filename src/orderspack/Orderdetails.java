package orderspack;


public class Orderdetails 
{
    private int id;
    private int userid;
    private int productid;
    private int quantity;
    private double price;  
    public Orderdetails(int id,int userid,int productid,int quantity,double price) 
    {
        this.id=id;
        this.userid=userid;
        this.productid=productid;
        this.quantity=quantity; 
        this.price=price;
    }
    public Orderdetails(int id,int productid,int quantity,double price) 
    {
        this.id=id;       
        this.productid=productid;
        this.quantity=quantity; 
        this.price=price;
    }
    // setter and getter
    public int getid()
    {
        return id;
    }
    public void setid(int id)
    {
        this.id=id;
    }
    //
    public int getuserid()
    {
        return userid;
    }
    public void setuserid(int userid)
    {
        this.userid=userid;
    }
    //
    public int getproductid()
    {
        return productid;
    }
    public void setproductid(int productid)
    {
        this.productid=productid;
    }
    //
    public int getquantity()
    {
        return quantity;
    }
    public void setquantity(int quantity)
    {
        this.quantity=quantity;
    }
    //
    public double getprice()
    {
        return price;
    }
    public void setprice(double price)
    {
        this.price=price;
    }
}
