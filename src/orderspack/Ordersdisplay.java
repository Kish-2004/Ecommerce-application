package orderspack;

import orderspack.Ordercrud;
import orderspack.Orders;
import orderspack.Orderdetails;
import java.sql.SQLException;
import java.util.Scanner;
public class Ordersdisplay 
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        Ordercrud ocrud = new Ordercrud();
        boolean exit = false;
        while (!exit)
        {
            System.out.println("----Orders CRUD Operation----");
            System.out.println("1. Create Order");
            System.out.println("2. Add Orders Details");
            System.out.println("3. Display All Orders");
            System.out.println("4. Display Order Details");
            System.out.println("5. Update The Product");
            System.out.println("6. Delete The Product");
            System.out.println("7. Exit");
            System.out.println("------------------------------");
            System.out.println("Enter Your Choice :");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice)
            {
                case 1:
                    // Create Order
                    System.out.println("Enter User ID :");
                    int uid = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter Your TotalAmount :");
                    Double ta = sc.nextDouble();
                    // Creating the order object
                    Orders order1 = new Orders(uid, ta, "pending");
                    try
                    {
                        boolean results = ocrud.createorder(order1);
                        if (results)
                        {
                            System.out.println("Order Created with ID: " + order1.getordersid());
                        }
                        else
                        {
                            System.out.println("Order Not Created Successfully...");
                        }
                    }
                    catch (SQLException e)
                    {
                        e.printStackTrace();
                    }
                    break;

                case 2:
                    // Add Order Details
                    System.out.println("Enter Order ID :");
                    int oid = sc.nextInt();
                    System.out.println("Enter Product ID :");
                    int pid = sc.nextInt();
                    System.out.println("Enter The Quantity :");
                    int quant = sc.nextInt();
                    System.out.println("Enter The Price :");
                    Double price = sc.nextDouble();
                    sc.nextLine();
                    
                    // Creating the order details object
                    Orderdetails orderd1 = new Orderdetails(oid, pid, quant, price);
                    try
                    {
                        boolean results1 = ocrud.addorderdetail(orderd1);
                        if (results1)
                        {
                            System.out.println("Order details added!");
                        }
                        else
                        {
                            System.out.println("Order Details Not Added Successfully...");
                        }
                    }
                    catch (SQLException e)
                    {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    try
                    {
                        System.out.println("Enter User ID to display their orders:");
                        int uidForDisplay = sc.nextInt();
                        ocrud.displayOrders(uidForDisplay);

                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    try 
                    { 
                        System.out.println("Enter User ID:");
                        int userIdForDetail = sc.nextInt();
                        System.out.println("Enter Order ID to display details:");
                        int orderId = sc.nextInt();
                        ocrud.displayOrderDetails(orderId, userIdForDetail);

                    } 
                    catch(SQLException e) 
                    { 
                        e.printStackTrace(); 
                    } 
                    break;
                case 5:
                    // Update the Product
                    System.out.println("Enter Order ID to update:");
                    int orderIdToUpdate = sc.nextInt();
                    System.out.println("Enter new Product ID :");
                    int newProductId = sc.nextInt();
                    System.out.println("Enter new Quantity :");
                    int newQuantity = sc.nextInt();
                    System.out.println("Enter new Price :");
                    Double newPrice = sc.nextDouble();
                    sc.nextLine();
                    
                    try
                    {
                        boolean updateResult = ocrud.updateOrderDetail(orderIdToUpdate, newProductId, newQuantity, newPrice);
                        if (updateResult)
                        {
                            System.out.println("Order Detail Updated Successfully.");
                        }
                        else
                        {
                            System.out.println("Failed to update Order Detail.");
                        }
                    }
                    catch (SQLException e)
                    {
                        e.printStackTrace();
                    }
                    break;

                case 6:  // Delete Product
                    System.out.println("Enter Order ID to delete:");
                    int orderIdToDelete = sc.nextInt();
                    sc.nextLine();  // consume newline
                    try 
                    {
                        boolean deleteSuccess = ocrud.deleteOrder(orderIdToDelete);
                        if (deleteSuccess) 
                        {
                            System.out.println("Order deleted successfully!");
                        } 
                        else 
                        {
                            System.out.println("Failed to delete the order.");
                        }
                    } 
                    catch (SQLException e) 
                    {
                        e.printStackTrace();
                    }
                    break;

                case 7:
                    // Exit
                    exit = true;
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid Choice, Try again.");
            }
        }
        sc.close();
    }
}
