package cartspack;
public class carts 
{
    private int id;
    private int user_id;
    private int product_id;
    private int quantity;
    public carts(int user_id,int product_id,int quantity) 
    {
        this.user_id=user_id;
        this.product_id=product_id;
        this.quantity=quantity;        
    }
    public carts(int id,int user_id,int product_id,int quantity) 
    {
        this.id=id;
        this.user_id=user_id;
        this.product_id=product_id;
        this.quantity=quantity;        
    }
    public carts(int user_id,int product_id) 
    {
        this.user_id=user_id;
        this.product_id=product_id;              
    }
    public carts(int id)
    {
        this.id=id;
    }

    public carts() {
       
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
    public int getuser_id()
    {
        return user_id;
    }
    public void setuser_id(int user_id)
    {
        this.user_id=user_id;
    }
    //
    public int getproduct_id()
    {
        return product_id;
    }
    public void setproduct_id(int product_id)
    {
        this.product_id=product_id;
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
}
