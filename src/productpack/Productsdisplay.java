package productpack;

import productpack.Productscrud;
import productpack.Products;
import java.sql.SQLException;
import java.util.Scanner;
public class Productsdisplay 
{
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        Productscrud crud=new Productscrud();
        boolean exit=false;
        while(!exit)
        {
            System.out.println("----Products CRUD Operation----");
            System.out.println("1.Add Products");
            System.out.println("2.Display All Products");
            System.out.println("3.Display Specific Products Using Id");
            System.out.println("4.Update The Product");
            System.out.println("5.Delete The Product");
            System.out.println("6.Exit");
             System.out.println("------------------------------");
            System.out.println("Enter Your Choice :");
            int choice=sc.nextInt();
            sc.nextLine();
            switch(choice)
            {
                case 1:
                    System.out.println("Enter Your ID :");
                    int id=sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter Your Name :");
                    String name=sc.nextLine();
                    System.out.println("Enter Your Description :");
                    String description=sc.nextLine();
                    System.out.println("Enter Your Price :");
                    float price=sc.nextFloat();
                    System.out.println("Enter Your Stock :");
                    int stock=sc.nextInt();
                    sc.nextLine();
                    Products products=new Products(id,name,description,price,stock);
                    try
                    {
                        boolean results=crud.addproduct(products);
                        if(results)
                        {
                            System.out.println("Product Added Sucessfully...");
                        }
                        else
                        {
                            System.out.println("Product Not Added Sucessfully...");
                        }
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }
                    break;
                    
                case 2:
                    crud.displayproduct();
                    break;
                    
                case 3:
                    System.out.println("Enter The Product ID :");
                    int id1=sc.nextInt();
                    sc.nextLine();
                    Products product=new Products(id1);
                    crud.displayproductid(product);
                    break;
                    
                case 4:
                    System.out.println("Enter Product ID :");
                    int id2=sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter Updated Name :");
                    String names=sc.nextLine();
                    System.out.println("Enter Updated Description :");
                    String descriptions=sc.nextLine();
                    System.out.println("Enter Updated Price :");
                    float prices=sc.nextFloat();
                    System.out.println("Enter Updated Stock :");
                    int stocks=sc.nextInt();
                    sc.nextLine();
                    Products productss=new Products(id2,names,descriptions,prices,stocks);
                    try
                    {
                        boolean res=crud.Updateproduct(productss);
                        if(res)
                        {
                            System.out.println("Product Updated Successfully...");
                        }
                        else
                        {
                            System.out.println("Product with ID " + productss.getid() + " does not exist or update failed.");
                        }
                    }
                    catch(Exception e)
                    {
                    }
                    break;
                    
                case 5:
                    System.out.println("Enter Product Id To Delete :");
                    int id3=sc.nextInt();
                    sc.nextLine();
                    Products product1=new Products(id3);
                    try
                    {
                        boolean res1=crud.Deleteproduct(product1);
                        if(res1)
                        {
                            System.out.println("Product Deleted Successfully...");
                        }
                        else
                        {
                            System.out.println("Product with ID " + product1.getid() + " does not exist or Delete failed.");
                        }
                    }
                    catch(SQLException e)
                    {
                        System.out.println(e.getMessage());
                    }                
                    break;                   
                case 6:
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
