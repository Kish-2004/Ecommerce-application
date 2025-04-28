package productpack;
import productpack.Products;
import utils.testConnection;
import java.sql.*;

public class Productscrud 
{
    public boolean addproduct(Products products) throws SQLException
    {
        boolean results=false;
        String sql="INSERT INTO products(id,name,description,price,stock) VALUES(?,?,?,?,?)";
        try(Connection conn=testConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql))
        {
             stmt.setInt(1,products.getid());
             stmt.setString(2,products.getname());
             stmt.setString(3,products.getdescription());
             stmt.setFloat(4,products.getprice());
             stmt.setInt(5,products.getstock());
             int rowins=stmt.executeUpdate();
             if(rowins>0)
             {
                 results=true;
             }
        }
        catch(SQLException e)
        {
        }
        return results;
    }
    public void displayproduct() 
    {
        String sql = "SELECT * FROM products";
        try (Connection conn = testConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) 
        {

            ResultSet rs = stmt.executeQuery();
            System.out.println("Product List :");
            System.out.println("--------------------------------------------------------------------------------");
            System.out.printf("|%-5s | %-15s | %-10s | %-6s | %-20s%n", "ID", "Name", "Price", "Stock", "Description");
            System.out.println("--------------------------------------------------------------------------------");
            while (rs.next())
            {
                try 
                    {
                        System.out.printf("|%-5d | %-15s | %-10.2f | %-6d | %-20s%n", 
                                   rs.getInt(1),                                     
                                   rs.getString(2), 
                                   rs.getFloat(4),  
                                   rs.getInt(5),  
                                   rs.getString(3));
                    } 
                    catch (SQLException e) 
                    {
                        System.out.println("Error fetching data: " + e.getMessage());
                    }
            }
            System.out.println("--------------------------------------------------------------------------------");
        } 
        catch (SQLException e) 
        {
        }
    }
    public void displayproductid(Products products)
    {
        String sql="SELECT * FROM products WHERE id=?";
        try(Connection conn=testConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1,products.getid());
            try(ResultSet rs = stmt.executeQuery();)
            {
                if(rs.next())
                {
                    System.out.println("Product Details :");
                    System.out.println("------------------------------------------------");                         
                    System.out.println("ID :"+rs.getInt(1));
                    System.out.println("Name :"+rs.getString(2));
                    System.out.println("Price :"+rs.getFloat(4));
                    System.out.println("Stock :"+rs.getInt(5));
                    System.out.println("Decription :"+rs.getString(3));
                    System.out.println("------------------------------------------------");

                 }
                 else
                 {
                    System.out.println("No Data Is In The Products......");
                 }
            }
            catch(SQLException e)
            {
            }                     
        }
        catch(SQLException e)
        {
        }          
    }
    public boolean Updateproduct(Products productss) throws SQLException
    {
        boolean res=false;
        String sql="UPDATE products SET name=?, description=?, price=?, stock=? WHERE id=?";
        try(Connection conn=testConnection.getConnection();
                PreparedStatement stmt=conn.prepareStatement(sql))
        {                       
            stmt.setString(1,productss.getname());
            stmt.setString(2,productss.getdescription());
            stmt.setFloat(3,productss.getprice());
            stmt.setInt(4,productss.getstock());
            stmt.setInt(5,productss.getid());
            
            int ri=stmt.executeUpdate();
            
            if(ri>0)
            {                
                res=true;
            }
        }
        catch(Exception e)
        {
            System.out.println("Error updating product: " + e.getMessage());
        }
        return res;
    }
    public boolean Deleteproduct(Products product1) throws SQLException
    {
        boolean res1=false;
        String sql="DELETE FROM products WHERE id=?";
        try(Connection conn=testConnection.getConnection();
                PreparedStatement stmt=conn.prepareStatement(sql))
        {
            stmt.setInt(1,product1.getid());
            int ins=stmt.executeUpdate();
            if(ins>0)
            {
                res1=true;
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());           
        }
        return res1;               
    }

    public static Products getProductById(int productId) {
    Products product = null;
    String query = "SELECT * FROM products WHERE id = ?";
    
    try (Connection conn = testConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(query)) {
        
        // Debugging: Check if the connection is valid
        if (conn != null) {
            System.out.println("Connection established.");
        } else {
            System.out.println("Connection failed.");
        }
        
        ps.setInt(1, productId);
        System.out.println("Executing query: " + query + " with productId = " + productId);
        
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            float price = rs.getFloat("price");
            int stock = rs.getInt("stock");
            String description = rs.getString("description");
            
            product = new Products(id, name, description, price, stock);
            System.out.println("Product found: " + product);
        } else {
            System.out.println("No product found with ID: " + productId);
        }
    } catch (SQLException e) {
        System.out.println("Error occurred while fetching product: " + e.getMessage());
        e.printStackTrace();
    }
    
    return product;
}




}
