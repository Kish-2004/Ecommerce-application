package ecommerce;
import cartspack.Cartscrud;
import cartspack.carts;
import dao.UserReg;
import productpack.Products;
import dao.TestUserRegisteration;
import java.sql.SQLException;
import java.util.Scanner;
import model.User;
import orderspack.Ordercrud;
import orderspack.Orderdetails;
import orderspack.Orders;
import productpack.Products;
import productpack.Productscrud;

public class ECommerceWebsite 
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        UserReg userReg = new UserReg();
        Productscrud productCrud = new Productscrud();
        Cartscrud cartCrud = new Cartscrud();
        Ordercrud orderCrud = new Ordercrud();

        User currentUser = null;  

        boolean exit = false;
        while (!exit) 
        {
            if (currentUser == null) 
            {               
                System.out.println("----Welcome to My E-Commerce Shop----");
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.println("------------------------------------");
                System.out.print("Enter Your Choice: ");
                int choice = sc.nextInt();
                sc.nextLine();  

                switch (choice) 
                {
                    case 1:                        
                        System.out.println("User Registration Selected");
                        registerUser(sc, userReg);
                        break;
                    case 2:                       
                        System.out.println("User Login Selected");
                        currentUser=loginUser(sc,userReg);
                        break;

                    case 3:
                        exit = true;
                        System.out.println("Exiting... Thank you for visiting.");
                        break;

                    default:
                        System.out.println("Invalid option. Please choose a valid option.");
                        break;
                }
            } 
            else 
            {                
                System.out.println("----E-Commerce Main Menu----");
                System.out.println("1. Product CRUD Operations");
                System.out.println("2. Cart CRUD Operations");
                System.out.println("3. Order CRUD Operations");
                System.out.println("4. Logout");
                System.out.println("5. Exit");
                System.out.println("----------------------------");
                System.out.print("Enter Your Choice: ");
                int mainMenuChoice = sc.nextInt();
                sc.nextLine(); 

                switch (mainMenuChoice) 
                {
                    case 1:                        
                        productCRUDOperations(sc,productCrud);
                        break;

                    case 2:                        
                        cartCRUDOperations(sc,cartCrud,currentUser);
                        break;

                    case 3:
                        orderCRUDOperations(sc,orderCrud,currentUser);
                        break;

                    case 4:
                        currentUser = null;
                        System.out.println("You have logged out successfully.");
                        break;

                    case 5:
                        exit = true;
                        System.out.println("Exiting... Thank you for using our service.");
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }
        }

        sc.close();
    }   
    private static void productCRUDOperations(Scanner sc, Productscrud productCrud) 
    {
        boolean exit = false;
        while (!exit) 
        {
            System.out.println("----Products CRUD Operations----");
            System.out.println("1. Add Product");
            System.out.println("2. Display All Products");
            System.out.println("3. Display Product by ID");
            System.out.println("4. Update Product");
            System.out.println("5. Delete Product");
            System.out.println("6. Exit");
            System.out.println("------------------------------");
            System.out.print("Enter Your Choice: ");
            int choice = sc.nextInt();
            sc.nextLine();  

            switch (choice) 
            {
                case 1:                    
                    addProduct(sc, productCrud);
                    break;
                case 2:
                    productCrud.displayproduct();
                    break;
                case 3:
                    displayProductById(sc, productCrud);
                    break;
                case 4:
                    updateProduct(sc, productCrud);
                    break;
                case 5:
                    deleteProduct(sc, productCrud);
                    break;
                case 6:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void addProduct(Scanner sc, Productscrud productCrud) 
    {
        System.out.print("Enter Product ID: ");
        int id = sc.nextInt();
        sc.nextLine();  
        System.out.print("Enter Product Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Product Description: ");
        String description = sc.nextLine();
        System.out.print("Enter Product Price: ");
        float price = sc.nextFloat();
        System.out.print("Enter Product Stock: ");
        int stock = sc.nextInt();
        sc.nextLine();  

        Products product = new Products(id, name, description, price, stock);
        try 
        {
            boolean result = productCrud.addproduct(product);
            System.out.println(result ? "Product Added Successfully!" : "Product Not Added.");
        } 
        catch (SQLException e) 
        {
            System.out.println("Error while adding product. Please check your input and try again.");
        }
    }

    private static void displayProductById(Scanner sc, Productscrud productCrud) 
    {
        System.out.print("Enter Product ID to display: ");
        int id = sc.nextInt();
        sc.nextLine();  
        Products product = new Products(id);
        productCrud.displayproductid(product);
    }

    private static void updateProduct(Scanner sc, Productscrud productCrud) 
    {
        System.out.print("Enter Product ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();  // Consume newline
        System.out.print("Enter Updated Product Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Updated Product Description: ");
        String description = sc.nextLine();
        System.out.print("Enter Updated Product Price: ");
        float price = sc.nextFloat();
        System.out.print("Enter Updated Product Stock: ");
        int stock = sc.nextInt();
        sc.nextLine();  

        Products product = new Products(id, name, description, price, stock);
        try 
        {
            boolean result = productCrud.Updateproduct(product);
            System.out.println(result ? "Product Updated Successfully!" : "Product Not Updated.");
        } catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }

    private static void deleteProduct(Scanner sc, Productscrud productCrud) {
        System.out.print("Enter Product ID to delete: ");
        int id = sc.nextInt();
        sc.nextLine();  
        Products product = new Products(id);
        try 
        {
            boolean result = productCrud.Deleteproduct(product);
            System.out.println(result ? "Product Deleted Successfully!" : "Product Not Found.");
        } catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }
    private static void cartCRUDOperations(Scanner sc, Cartscrud cartCrud,User currentUser) 
    {
        boolean exit = false;
        while (!exit) {
            System.out.println("----Carts CRUD Operations----");
            System.out.println("1. Add to Cart");
            System.out.println("2. Display All Cart Details");
            System.out.println("3. Update Cart");
            System.out.println("4. Delete Cart");
            System.out.println("5. Exit");
            System.out.println("----------------------------");
            System.out.print("Enter Your Choice: ");
            int choice = sc.nextInt();
            sc.nextLine();  

            switch (choice) 
            {
                case 1:
                    addToCart(sc,cartCrud,currentUser);
                    break;
                case 2:
                    displayCartDetails(sc,cartCrud,currentUser);
                    break;
                case 3:
                    updateCart(sc,cartCrud,currentUser);
                    break;
                case 4:
                    deleteCart(sc,cartCrud);
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void addToCart(Scanner sc, Cartscrud cartCrud, User currentUser) {
    if (currentUser == null) {
        System.out.println("Please log in to add items to your cart.");
        return;
    }

    int userId = currentUser.getid(); 
    System.out.print("Enter Product ID: ");
    int productId = sc.nextInt();
    System.out.print("Enter Quantity: ");
    int quantity = sc.nextInt();
    sc.nextLine();  

    // Check if product exists
    Products product = Productscrud.getProductById(productId);
    if (product == null) {
        System.out.println("Product not found.");
        return;
    }

    if (product.getstock() < quantity) {
        System.out.println("Insufficient stock for this product.");
        return;
    }

    //  Use constructor WITHOUT ID now!
    carts cart = new carts(userId, productId, quantity);

    try {
        boolean result = cartCrud.addCart(cart);
        System.out.println(result ? "Product Added to Your Cart!" : "Failed to Add Product to Cart.");
    } catch (SQLException e) {
        e.printStackTrace();
    }
}



    private static void displayCartDetails(Scanner sc, Cartscrud cartCrud, User currentUser) {
    if (currentUser == null) {
        System.out.println("Please log in to view your cart.");
        return;
    }

    int userId = currentUser.getid(); 
    try {
        cartCrud.displayCartForUser(userId); 
    } 
    catch (SQLException e) 
    {
        e.printStackTrace();
    }
}


    private static void updateCart(Scanner sc, Cartscrud cartCrud, User currentUser) {
    if (currentUser == null) {
        System.out.println("Please log in to update your cart.");
        return;
    }

    System.out.print("Enter Cart ID to update: ");
    int cartId = sc.nextInt();
    System.out.print("Enter Product ID: ");
    int productId = sc.nextInt();
    System.out.print("Enter Quantity: ");
    int quantity = sc.nextInt();
    sc.nextLine();  // Consume newline

    int userId = currentUser.getid();
    carts cart = new carts(cartId, userId, productId, quantity);
    try {
        boolean result = cartCrud.updatecart(cart);
        System.out.println(result ? "Cart Updated Successfully!" : "Failed to Update Cart.");
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    private static void deleteCart(Scanner sc, Cartscrud cartCrud) 
    {
        System.out.print("Enter Cart ID to delete: ");
        int cartId = sc.nextInt();
        System.out.print("Enter User ID to delete: ");
        int uid = sc.nextInt();
        System.out.print("Enter Product ID to delete: ");
        int pid = sc.nextInt();
        sc.nextLine();  

        carts cart = new carts(cartId, uid, pid);
        try 
        {
            boolean result = cartCrud.Deletecart(cart);
            System.out.println(result ? "Cart Deleted Successfully!" : "Failed to Delete Cart.");
        } catch (SQLException e) 
        {
        }
    }

    private static void orderCRUDOperations(Scanner sc, Ordercrud orderCrud,User currentUser) 
    {
        boolean exit = false;
        while (!exit) {
            System.out.println("----Orders CRUD Operations----");
            System.out.println("1. Create Order");
            System.out.println("2. Add Order Details");
            System.out.println("3. Display All Orders");
            System.out.println("4. Display Order Details");
            System.out.println("5. Update Order");
            System.out.println("6. Delete Order");
            System.out.println("7. Exit");
            System.out.println("------------------------------");
            System.out.print("Enter Your Choice: ");
            int choice = sc.nextInt();
            sc.nextLine();  

            switch (choice) {
                case 1:
                    createOrder(sc,orderCrud,currentUser);
                    break;
                case 2:
                    addOrderDetails(sc,orderCrud,currentUser);
                    break;
                case 3:
                    displayAllOrders(sc,orderCrud,currentUser);
                    break;
                case 4:
                    displayOrderDetails(sc,orderCrud,currentUser);
                    break;
                case 5:
                    updateOrder(sc,orderCrud,currentUser);
                    break;
                case 6:
                    deleteOrder(sc,orderCrud,currentUser);
                    break;
                case 7:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

   private static void createOrder(Scanner sc, Ordercrud orderCrud, User currentUser) {
    if (currentUser == null) {
        System.out.println("Please log in to create an order.");
        return;
    }

    // Check if user exists in the users table
    System.out.println("Checking if user exists with ID: " + currentUser.getid());

    if (orderCrud.checkIfUserExists(currentUser.getid()) == false) {
        System.out.println("User does not exist in the database. Please try again.");
        return;
    }

    int userId = currentUser.getid(); 
    System.out.print("Enter Total Amount: ");
    double totalAmount = sc.nextDouble();
    sc.nextLine();  

    Orders order = new Orders(userId, totalAmount, "pending");
    try {
        boolean result = orderCrud.createorder(order);
        System.out.println(result ? "Order Created Successfully!" : "Failed to Create Order.");
    } catch (SQLException e) {
        e.printStackTrace();
    }
}



    private static void addOrderDetails(Scanner sc, Ordercrud orderCrud, User currentUser) {
    if (currentUser == null) {
        System.out.println("Please log in to add order details.");
        return;
    }

    System.out.print("Enter Order ID: ");
    int orderId = sc.nextInt();
    System.out.print("Enter Product ID: ");
    int productId = sc.nextInt();
    System.out.print("Enter Quantity: ");
    int quantity = sc.nextInt();
    System.out.print("Enter Price: ");
    double price = sc.nextDouble();
    sc.nextLine();  // Consume newline

    Orderdetails orderDetails = new Orderdetails(orderId, productId, quantity, price);
    try {
        boolean result = orderCrud.addorderdetail(orderDetails);
        System.out.println(result ? "Order Details Added Successfully!" : "Failed to Add Order Details.");
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    private static void displayAllOrders(Scanner sc, Ordercrud orderCrud, User currentUser) {
    if (currentUser == null) {
        System.out.println("Please log in to view your orders.");
        return;
    }

    int userId = currentUser.getid(); 
    try {
        orderCrud.displayOrdersForUser(userId);
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    private static void displayOrderDetails(Scanner sc, Ordercrud orderCrud, User currentUser) {
    System.out.print("Enter Order ID: ");
    int orderId = sc.nextInt();
    try 
    {
        orderCrud.displayOrderDetails(orderId, currentUser.getid());
    } 
    catch (SQLException e) 
    {
        e.printStackTrace();
    }
}


    private static void updateOrder(Scanner sc, Ordercrud orderCrud,User currentUser) 
    {
        System.out.print("Enter Order ID to update: ");
        int orderId = sc.nextInt();
        System.out.print("Enter new Product ID: ");
        int productId = sc.nextInt();
        System.out.print("Enter new Quantity: ");
        int quantity = sc.nextInt();
        System.out.print("Enter new Price: ");
        double price = sc.nextDouble();
        sc.nextLine(); 

        try 
        {
            boolean result = orderCrud.updateOrderDetail(orderId, productId, quantity, price);
            System.out.println(result ? "Order Detail Updated!" : "Failed to Update Order Detail.");
        }
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }

    private static void deleteOrder(Scanner sc, Ordercrud orderCrud,User currentUser) 
    {
        System.out.print("Enter Order ID to delete: ");
        int orderId = sc.nextInt();
        sc.nextLine();  

        try 
        {
            boolean result = orderCrud.deleteOrder(orderId);
            System.out.println(result ? "Order Deleted Successfully!" : "Failed to Delete Order.");
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }
    public static void showUserOrders(User currentUser) 
    {
   
        System.out.println("Fetching orders for: " + currentUser.getname());
    }
    public static void showUserCart(User currentUser) 
    {
   
        System.out.println("Fetching cart for: " + currentUser.getname());
    }
    public static User registerUser(Scanner sc, UserReg userReg) 
    {
        System.out.println("Welcome to My Registration Page...");

        // Collect user details for registration
        String name = getUserInput(sc, "Enter Your Name: ");
        String email = getUserInput(sc, "Enter Your Email: ");
        String password = getUserInput(sc, "Enter Your Password: ");
        String role = getUserInput(sc, "Enter Your Role: ");

        User user = new User(name, email, password, role);
        boolean isRegistered = userReg.registerUser(user);

        if (isRegistered) 
        {
            System.out.println("User Successfully Registered!");
            System.out.print("Do you want to log in now? (yes/no): ");
            String loginChoice = sc.nextLine().toLowerCase();
            if (loginChoice.equals("yes")) 
            {
                return loginUser(sc, userReg);  
            }
        }
        return null;
    }
    // Method to handle user login
    public static User loginUser(Scanner sc, UserReg userReg) {
    System.out.println("Welcome to My Login Page...");

    String email = getUserInput(sc, "Enter Your E-mail: ");
    String password = getUserInput(sc, "Enter Your Password: ");

    User user = userReg.loginUser(email, password);  

    if (user != null) {
        System.out.println("Login Successfully! Welcome " + user.getname());

        // Set the session user ID and the currentUser here
        Session.loggedInUserId = user.getid();  
        Session.loggedInUser = user;   

        return user;  
    } else {
        System.out.println("Invalid Email or Password!");
    }

    System.out.print("Do you want to try again? (yes/no): ");
    String continueLogin = sc.nextLine().trim().toLowerCase();

    if (continueLogin.equals("yes")) {
        return loginUser(sc, userReg); 
    } else {
        System.out.println("Exiting login process.");
    }
    return null;  
}

    // Reusable method to handle user input for common fields (email, password, etc.)
    public static String getUserInput(Scanner sc, String prompt) 
    {
        System.out.print(prompt);
        return sc.nextLine();
    }
    
}
