package productpack;

public class Products 
{
    private static int id;
    private String name;
    private String description;
    private float price;
    private int stock;
    public Products(int id)
    {
        this.id=id;
    }
    public Products(int id,String name,String description,float price,int stock)
    {
        this.id=id;
        this.name=name;
        this.description=description;
        this.price=price;
        this.stock=stock;
    }
    @Override
     public String toString() 
    {
        return "ID: " + id + ", Name: " + name + ", Price: " + price + ", Stock: " + stock + ", Description: " + description;
    }

    //setter and getter
    public int getid()
    {
        return id;
    }
    public void setid(int id)
    {
        Products.id=id;
    }
    //
    public String getname()
    {
        return name;
    }
    public void setname(String name)
    {
        this.name=name;
    }
    //
    public String getdescription()
    {
        return description;
    }
    public void setdescrption(String description)
    {
        this.description=description;
    }
    //
    public float getprice()
    {
        return price;
    }
    public void setprice(float price )
    {
        this.price=price;
    }
    public int getstock()
    {
        return stock;
    }
    //
    public void setstock(int stock )
    {
        this.stock=stock;
    }
}
