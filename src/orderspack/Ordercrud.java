package orderspack;
import ecommerce.Session;
import orderspack.Orders;
import orderspack.Orderdetails;
import utils.testConnection;
import java.sql.*;
public class Ordercrud 
{
    // Create Order
    public boolean createorder(Orders order1) throws SQLException {
    boolean results = false;
    String sql = "INSERT INTO orders (user_id, total_amount, status) VALUES (?, ?, ?)";
    try (Connection conn = testConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        
        stmt.setInt(1, order1.getuserid());
        stmt.setDouble(2, order1.gettotalamount());
        stmt.setString(3, order1.getstatus());
        
        int rowins = stmt.executeUpdate();
        
        if (rowins > 0) {
            results = true;
            
            // Fetch the generated keys
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    order1.setordersid(generatedId); 
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return results;
}


    // Add Order Details
    public boolean addorderdetail(Orderdetails orderd1) throws SQLException {
    boolean results1 = false;
    
    // Step 1: Check if the order ID exists in the 'orders' table
    String checkOrderSql = "SELECT id FROM orders WHERE id = ? AND user_id = ?";  
    try (Connection conn = testConnection.getConnection();
         PreparedStatement checkStmt = conn.prepareStatement(checkOrderSql)) {
        checkStmt.setInt(1, orderd1.getid());  
        checkStmt.setInt(2, Session.loggedInUserId);  
        
        try (ResultSet rs = checkStmt.executeQuery()) {
            if (!rs.next()) {
                // If the order ID does not exist or doesn't belong to the user, return false
                System.out.println("Order ID does not exist for this user.");
                return false;
            }
        }
    }
    
    // If the order ID exists and belongs to the user, insert the order details
    String sql = "INSERT INTO order_details (order_id, user_id, product_id, quantity, price) VALUES (?, ?, ?, ?, ?)";
    try (Connection conn = testConnection.getConnection();
         PreparedStatement stmt1 = conn.prepareStatement(sql)) {
        stmt1.setInt(1, orderd1.getid());  
        stmt1.setInt(2, Session.loggedInUserId);  
        stmt1.setInt(3, orderd1.getproductid());  
        stmt1.setInt(4, orderd1.getquantity());  
        stmt1.setDouble(5, orderd1.getprice()); 
        
        int rowins = stmt1.executeUpdate();
        if (rowins > 0) {
            results1 = true;
            System.out.println("Order details added successfully.");
        } else {
            System.out.println("Failed to add order details.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return results1;
}


    // Display All Orders
   public void displayOrders(int userId) throws SQLException {
    String sql = "SELECT id, user_id, total_amount, status FROM orders WHERE user_id = ?";
    try (Connection conn = testConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, userId);  
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            int orderId = rs.getInt("id");
            int userIdFromDB = rs.getInt("user_id");
            double totalAmount = rs.getDouble("total_amount");
            String status = rs.getString("status");

            System.out.println("Order ID: " + orderId);
            System.out.println("User ID: " + userIdFromDB);
            System.out.println("Total Amount: " + totalAmount);
            System.out.println("Status: " + status);
            System.out.println("--------------------------");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


     
    // Display Order Details for a specific order
    
   public void displayOrderDetails(int orderId, int userId) throws SQLException {
    String sql = "SELECT od.id, od.product_id, od.quantity, od.price, p.name AS product_name " +
                 "FROM order_details od " +
                 "JOIN products p ON od.product_id = p.id " +
                 "WHERE od.order_id = ? AND od.user_id = ?";  

    try (Connection conn = testConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, orderId);
        stmt.setInt(2, userId);  

        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                int productId = rs.getInt("product_id");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                String productName = rs.getString("product_name");
                System.out.println("--------------------------");   
                System.out.println("Order Detail ID: " + id);
                System.out.println("Product ID: " + productId);
                System.out.println("Product Name: " + productName);
                System.out.println("Quantity: " + quantity);
                System.out.println("Price: " + price);
                System.out.println("--------------------------");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}



    // Update Order Details
    public boolean updateOrderDetail(int orderId, int productId, int quantity, double price) throws SQLException {
    boolean results = false;
    String sql = "UPDATE order_details SET quantity = ?, price = ? WHERE order_id = ? AND product_id = ?";
    try (Connection conn = testConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) 
    {
        stmt.setInt(1, quantity);   
        stmt.setDouble(2, price);  
        stmt.setInt(3, orderId);   
        stmt.setInt(4, productId);  

        int rowsUpdated = stmt.executeUpdate();
        if (rowsUpdated > 0) {
            results = true;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return results;
}

    // Delete Order Details
    public boolean deleteOrder(int orderId) throws SQLException {
    boolean result = false;

    // First, delete the order details that belong to this order
    String deleteOrderDetailsSql = "DELETE FROM order_details WHERE order_id = ?";
    try (Connection conn = testConnection.getConnection();
         PreparedStatement stmtDetails = conn.prepareStatement(deleteOrderDetailsSql)) {

        stmtDetails.setInt(1, orderId);
        int rowsDeleted = stmtDetails.executeUpdate();

        if (rowsDeleted > 0) {
            System.out.println("Order details deleted successfully.");
        } else {
            System.out.println("No order details found for this order.");
        }

        // Now, delete the order itself
        String deleteOrderSql = "DELETE FROM orders WHERE id = ?";
        try (PreparedStatement stmtOrder = conn.prepareStatement(deleteOrderSql)) {
            stmtOrder.setInt(1, orderId);
            int rowsDeletedOrder = stmtOrder.executeUpdate();
            if (rowsDeletedOrder > 0) {
                
                result = true;
            } else {
                System.out.println("Order not found.");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return result;
}
    public void displayOrdersForUser(int userId) throws SQLException {
    String query = "SELECT * FROM orders WHERE user_id = ?";
    
    try (Connection conn = testConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) 
    {
        stmt.setInt(1, userId);
        
        try (ResultSet rs = stmt.executeQuery()) 
        {
            boolean found = false;
            
            System.out.println("-----------------------------------------------------");
            System.out.printf("| %-10s | %-15s | %-12s |\n", "Order ID", "Order Date", "Total Amount");
            System.out.println("-----------------------------------------------------");

            while (rs.next()) 
            {
                found = true;
                int orderId = rs.getInt("id");
                Date orderDate = rs.getDate("order_date");
                double totalAmount = rs.getDouble("total_amount");

                System.out.printf("| %-10d | %-15s | %-12.2f |\n", orderId, orderDate.toString(), totalAmount);
            }
            
            if (!found) {
                System.out.println("|        No orders found for User ID: " + userId + "          |");
            }
            
            System.out.println("-----------------------------------------------------");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    // In the Ordercrud class
    public boolean checkIfUserExists(int userid) 
    {
        String query = "SELECT COUNT(*) FROM users WHERE id = ?";
        try (Connection conn = testConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, userid);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;  
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false; 
    }


}
