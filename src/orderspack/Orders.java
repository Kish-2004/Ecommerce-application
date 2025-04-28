package orderspack;

import java.util.Date;


public class Orders 
{
    private int ordersid;
    private int userid;
    private Date orderdate;
    private double totalamount;
    private String status;
    public Orders(int ordersid,int userid,Date orderdate,double totalamount,String status) 
    {
       this.ordersid=ordersid;
       this.userid=userid;
       this.orderdate=orderdate;
       this.totalamount=totalamount; 
       this.status=status;
    }
     public Orders(int userid,double totalamount,String status) 
    {  
       this.userid=userid;     
       this.totalamount=totalamount; 
       this.status=status;
    }
// getters and setters
    public int getordersid()
    {
        return ordersid;
    }
    public void setordersid(int ordersid)
    {
       this.ordersid=ordersid;
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
    public Date getorderdate()
    {
        return orderdate;
    }
    public void setorderdate(Date orderdate)
    {
      this.orderdate=orderdate;
    }
    //
    public double gettotalamount()
    {
        return totalamount;
    }
    public void settotalamount(double totalamount)
    {
       this.totalamount=totalamount;
    }
    //
    public String getstatus()
    {
        return status;
    }
    public void setstatus(String status)
    {
       this.status=status;
    }
}