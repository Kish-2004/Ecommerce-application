package utils;
import java.sql.*;


public class testConnection {

    private static String URL = "jdbc:mysql://localhost:3306/ecommerce_db";
    private static String USER = "root";
    private static String PASSWORD = "Kishore@2004";

    public static Connection getConnection() {
        Connection connection = null; // 
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Database connection failed.");
            e.printStackTrace();
        }
        return connection; 
    }

    public static void main(String[] args) {
        // Testing the connection
        Connection conn = getConnection();
        if (conn != null) {
            System.out.println("Connection established successfully!");
        } else {
            System.out.println("Failed to establish connection.");
        }
    }

    public static Connection getconnection() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
