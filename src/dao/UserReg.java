package dao;
import model.User;
import utils.testConnection;
import java.sql.*;
public class UserReg 
{
    
    // Method to register a new user
    public boolean registerUser(User user) {
        boolean result = false;
        String sql = "INSERT INTO users (name, email, password, role) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = testConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, user.getname()); 
            stmt.setString(2, user.getemail());
            stmt.setString(3, user.getpassword());
            stmt.setString(4, user.getrole());

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                result = true; 
            }
        } catch (SQLException e) {
        }
        return result;
    }

    public User loginUser(String email, String password) {
    User user = null;
    String sql = "SELECT * FROM users WHERE LOWER(email) = LOWER(?) AND password = ?";
    
    try (Connection conn = testConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
         
        stmt.setString(1, email);
        stmt.setString(2, password);
        
        System.out.println("Attempting login with email: " + email + " and password: " + password);
        
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {                    
                user = new User(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("role")
                );
                System.out.println("Login Successful! Welcome " + user.getname());
            } else {
                System.out.println("Invalid email or password.");
            }
        }
    } catch (SQLException e) 
    {
        System.err.println("Error during login: " + e.getMessage());
        e.printStackTrace();
    }
    return user;
}
}

