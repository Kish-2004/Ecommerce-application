package dao;
import dao.UserReg;
import model.User;
import java.util.Scanner;
public class TestUserRegisteration {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UserReg userReg = new UserReg();

        while (true) {
            // Register user
            System.out.println("Welcome to My E-Commerce Shop");
            System.out.print("Enter Your Name: ");
            String name = sc.nextLine();
            System.out.print("Enter Your Email: ");
            String email = sc.nextLine();
            System.out.print("Enter Your Password: ");
            String password = sc.nextLine();
            System.out.print("Enter Your Role: ");
            String role = sc.nextLine();

            
            User user = new User(name, email, password, role);

            
            boolean isRegistered = userReg.registerUser(user);

            
            if (isRegistered) {
                System.out.println("User Successfully Registered!");
            } else {
                System.out.println("User Registration Failed.");
            }

            
            System.out.print("Do you want to login or register another user? (login/register): ");
            String action = sc.nextLine().trim().toLowerCase();

            if (action.equals("login")) {
                
                TestUserLogin.loginUser(sc);
                break;  
            } else if (action.equals("register")) {
               
                continue;
            } else {
                System.out.println("Invalid option. Exiting...");
                break;  
            }
        }


        sc.close();
    }
}

class TestUserLogin {
    public static void loginUser(Scanner sc) {
        UserReg userReg = new UserReg();
        while (true) {
            System.out.println();
            System.out.println("Welcome to My Login Page...");
            System.out.print("Enter Your E-mail: ");
            String email = sc.nextLine();
            System.out.print("Enter Your Password: ");
            String password = sc.nextLine();

            
            User user = userReg.loginUser(email, password);

            if (user != null) {
                break; 
            } else {
                System.out.println("Invalid Email or Password!");
            }
            System.out.print("Do you want to try again? (yes/no): ");
            String continueLogin = sc.nextLine();
            if (!continueLogin.equalsIgnoreCase("yes")) {
                break; 
            }
        }
    }
}
