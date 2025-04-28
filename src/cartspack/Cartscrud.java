package cartspack;
import ecommerce.Session;
import utils.testConnection;
import java.sql.*;
public class Cartscrud 
{
   public boolean addCart(carts cart) throws SQLException {
    boolean result = false;
    
    int userId = cart.getuser_id(); 
    if (userId == 0) {
        System.out.println("Invalid user ID, user not logged in.");
        return false;
    }

    try (Connection conn = testConnection.getConnection()) {

        // Check if product already exists in cart
        String sql = "SELECT * FROM cart WHERE user_id = ? AND product_id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, userId);
        stmt.setInt(2, cart.getproduct_id());
        ResultSet rs = stmt.executeQuery();
        
        if (rs.next()) {
            // Product exists, update quantity
            String updateQuery = "UPDATE cart SET quantity = quantity + ? WHERE user_id = ? AND product_id = ?";
            PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
            updateStmt.setInt(1, cart.getquantity());
            updateStmt.setInt(2, userId);
            updateStmt.setInt(3, cart.getproduct_id());
            int rows = updateStmt.executeUpdate();
            if (rows > 0) result = true;
            updateStmt.close();
        } else {
            // Product does not exist, insert new cart
            String insertQuery = "INSERT INTO cart (user_id, product_id, quantity) VALUES (?, ?, ?)";
            PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
            insertStmt.setInt(1, Session.loggedInUserId);
            insertStmt.setInt(2, cart.getproduct_id());
            insertStmt.setInt(3, cart.getquantity());
            int rows = insertStmt.executeUpdate();
            if (rows > 0) result = true;
            insertStmt.close();
        }
        
        rs.close();
        stmt.close();
    } catch (SQLException e) {
        System.out.println("SQL Error in addCart: " + e.getMessage());
        e.printStackTrace();
    }
    
    return result;
}

    public void displaycart(carts cart2) 
    {
        String sql2="SELECT c.id,p.name,p.price,c.quantity,(p.price*c.quantity) AS total FROM cart c JOIN products p ON c.product_id=p.id WhERE c.user_id=?";       
        String sql3="SELECT SUM(p.price*c.quantity) AS cart_total FROM cart c JOIN products p ON c.product_id=p.id WHERE user_id=?";
        try (Connection conn = testConnection.getConnection();) 
        {
            PreparedStatement stmt = conn.prepareStatement(sql2);
            stmt.setInt(1,cart2.getuser_id());
            ResultSet rs = stmt.executeQuery();
            System.out.println("Cart Summary :");
            System.out.println("-----------------------------------------------------------------------------------------------------------");
            System.out.printf("|%-15s | %-10s | %-10s | %-10s%n", "Poduct Name","Price", "Quantity", "Total");
            System.out.println("-----------------------------------------------------------------------------------------------------------");           
            while (rs.next())
            {
                try 
                    {
                        System.out.printf("|%-15s | %-10.2f | %-10d | %-10.2f%n",                                                                         
                                   rs.getString("name"), 
                                   rs.getFloat("price"),  
                                   rs.getInt("quantity"),                                  
                                   rs.getFloat("total"));
                                   
                                   
                    } 
                    catch (SQLException e) 
                    {
                        System.out.println("Error fetching data: " + e.getMessage());
                         e.printStackTrace();
                    }
            }
            System.out.println("--------------------------------------------------------------------------------");
            PreparedStatement stmt3 = conn.prepareStatement(sql3);
            stmt3.setInt(1,cart2.getuser_id());
             ResultSet rs3 = stmt3.executeQuery();
            System.out.print("Total :");
            while(rs3.next())
            {
                try
                {
                    System.out.println(rs3.getInt("cart_total"));
                }
                catch(SQLException e)
                {
                    System.out.println("Error fetching data: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        } 
        
        catch (SQLException e) 
        {
            System.out.println("Error fetching data: " + e.getMessage());
        }
    }
    public boolean updatecart(carts cart3) throws SQLException
    {
        boolean res1=false;
        String sql="UPDATE cart SET user_id=?, product_id=?, quantity=? WHERE id=?";
        try(Connection conn=testConnection.getConnection();
                PreparedStatement stmt4=conn.prepareStatement(sql))
        {   
            stmt4.setInt(1,cart3.getuser_id());
            stmt4.setInt(2,cart3.getproduct_id());
            stmt4.setInt(3,cart3.getquantity());
            stmt4.setInt(4,cart3.getid());
            
            int ri1=stmt4.executeUpdate();
            
            if(ri1>0)
            {                
                res1=true;
            }
        }
        catch(Exception e)
        {
            System.out.println("Error updating product: " + e.getMessage());
            e.printStackTrace();
        }
        return res1;
    }
    public boolean Deletecart(carts cart4) throws SQLException
    {
        boolean res2=false;
        String sql="DELETE FROM cart WHERE user_id=? AND product_id=?";
        try(Connection conn=testConnection.getConnection();
                PreparedStatement stmt4=conn.prepareStatement(sql))
        {
            stmt4.setInt(1,cart4.getuser_id());
            stmt4.setInt(2,cart4.getproduct_id());
            int ins1=stmt4.executeUpdate();
            if(ins1>0)
            {
                res2=true;
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage()); 
            e.printStackTrace();
        }
        return res2;               
    
    }

    public void displayCartForUser(int userId) throws SQLException {
    String query = "SELECT c.id, p.name, p.price, c.quantity, (p.price * c.quantity) AS total " +
                   "FROM cart c JOIN products p ON c.product_id = p.id WHERE c.user_id = ?";
    
    try (Connection conn = testConnection.getConnection()) {
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, userId);
        ResultSet rs = stmt.executeQuery();
        
        System.out.println("Cart Summary for User ID: " + userId);
        System.out.println("--------------------------------------------------");
        System.out.printf("|%-10s | %-20s | %-10s | %-10s | %-10s%n", "Cart ID", "Product Name", "Price", "Quantity", "Total");
        System.out.println("--------------------------------------------------");
        
        while (rs.next()) {
            System.out.printf("|%-10d | %-20s | %-10.2f | %-10d | %-10.2f%n",
                              rs.getInt("id"),
                              rs.getString("name"),
                              rs.getFloat("price"),
                              rs.getInt("quantity"),
                              rs.getFloat("total"));
        }
        System.out.println("--------------------------------------------------");
    } catch (SQLException e) {
        System.out.println("SQL Error: " + e.getMessage());
        e.printStackTrace();
    }
}

}
