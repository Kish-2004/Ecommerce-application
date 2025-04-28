package cartspack;
import cartspack.carts;
import cartspack.Cartscrud;
import java.sql.SQLException;
import java.util.Scanner;
public class Cartsdisplay 
{
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);      
        Cartscrud carts=new Cartscrud();
        boolean exit=false;
        while(!exit)
        {
            System.out.println("----Carts CRUD Operation----");
            System.out.println("1.Add To Cart");
            System.out.println("2.Display All Cart Details");            
            System.out.println("3.Update The Cart");
            System.out.println("4.Delete The Cart");
            System.out.println("5.Exit");
            System.out.println("------------------------------");
            System.out.println("Enter Your Choice :");
            int choices=sc.nextInt();
            sc.nextLine();
            switch(choices)
            {
                case 1:
                    System.out.println("Enter Your Cart ID :");
                    int id=sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter Your User_ID :");
                    int uid=sc.nextInt();                  
                    System.out.println("Enter Your Product_id :");
                    int pid=sc.nextInt();                       
                    System.out.println("Enter Your Quantity:");
                    int quant=sc.nextInt();
                    sc.nextLine();
                    carts cart1=new carts(id,uid,pid,quant);
                    try
                    {
                        boolean results=carts.addCart(cart1);
                        if(results)
                        {
                            System.out.println("Product Added  To Cart Sucessfully...");
                        }
                        else
                        {
                            System.out.println("Product Not Added To Cart Sucessfully...");
                        }
                    }
                    catch(Exception e)
                    {
                        e.getMessage();
                    }
                    break;
                case 2:                 
                    System.out.println("Enter Your CartID :");
                    int cid=sc.nextInt();   
                    carts cart2=new carts(cid);
                    carts.displaycart(cart2);
                    break;
                case 3:
                    System.out.println("Enter The Cart ID :");
                    int cid1=sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter The User_ID :");
                    int uid1=sc.nextInt();                  
                    System.out.println("Enter The Product_id :");
                    int pid1=sc.nextInt();                    
                    System.out.println("Enter The Quantity:");
                    int quant1=sc.nextInt();
                    sc.nextLine();
                    carts cart3=new carts(cid1,uid1,pid1,quant1);                                        
                    try
                    {
                        boolean res1=carts.updatecart(cart3);
                        if(res1)
                        {
                            System.out.println("Product Updated  To Cart Sucessfully...");
                        }
                        else
                        {
                            System.out.println("Product Not Updated To Cart Sucessfully...");
                        }
                    }
                    catch(SQLException e)
                    {
                        e.getMessage();
                    }
                    break;
                case 4:
                    System.out.println("Enter The Cart ID :");
                    int cid4=sc.nextInt();
                    System.out.println("Enter UserId To Delete :");
                    int uid4=sc.nextInt();
                    System.out.println("Enter ProductId To Delete :");
                    int pid4=sc.nextInt();
                    sc.nextLine();
                    carts cart4=new carts(cid4,uid4,pid4);
                    try
                    {
                        boolean res2=carts.Deletecart(cart4);
                        if(res2)
                        {
                            System.out.println("Cart Deleted Successfully...");
                        }
                        else
                        {
                            System.out.println("Cart with ID " + cart4.getid() + " does not exist or Delete failed.");
                        }
                    }
                    catch(SQLException e)
                    {
                        System.out.println(e.getMessage());
                    }                
                    break;                   
               
                case 5:
                    exit=true;
                    System.out.println("Thank You For Using E-Commerce Website...");
                    break;
                                    
                default:
                    System.out.println("Invalid choice! Please select a valid option.");
                    break;
            }
        }
    }
}
