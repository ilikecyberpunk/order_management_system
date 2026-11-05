
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.PreparedStatement;

public class Main {
    public static void main(String[] args) throws Exception {

        //db연결
        Scanner s = new Scanner(System.in);

        System.out.println("===== lets connect database first =====");

        String url = "jdbc:mysql://localhost:3306/burger_shop";
        System.out.print("User ID : ");
        String user = s.next();
        System.out.print("Password : ");
        String password = s.next();

        Connection con = DriverManager.getConnection(url, user, password);
        
                try(con) {

                    
                    System.out.println("===== datebase connected !! ===== \n\n");

                    //dao
                    System.out.println("===== Burger Order Management System =====");
                    System.out.println("1. Add Burger");
                    System.out.println("2. Read Burgers");
                    System.out.println("3. Update Burger");
                    System.out.println("4. delete burger");
                    System.out.println("5. place an order");
                    System.out.println("6. view orders");
                    System.out.println("0. Exit \n");
                    System.out.print("select option : ");

                    int option = s.nextInt();

                    switch(option){
                        case 1:
                            System.out.print("burger name : ");
                            String burger_name = s.next();

                            System.out.print("burger price : ");
                            int burger_price = s.nextInt();

                            System.out.print("burger stock : ");
                            int burger_stock = s.nextInt();

                            BurgerDAO.addBurger(con, burger_name, burger_price, burger_stock);
                            break;

                        case 2:
                            BurgerDAO.readBurger(con);
                            break;

                        case 3:
                            System.out.print("which burger's id that u wanna update? : ");
                            int burger_id = s.nextInt();

                            System.out.print("new burger name : ");
                            String new_name = s.next();

                            System.out.print("new burger price : ");
                            int new_price = s.nextInt();

                            System.out.print("new burger stock : ");
                            int new_stock = s.nextInt();

                            BurgerDAO.updateBurger(con, burger_id, new_name, new_price, new_stock);
                            break;

                        case 4:
                            System.out.print("which id of burger do u wanna delete : ");
                            int delete_id = s.nextInt();
                            BurgerDAO.deleteBurger(con, delete_id);

                        case 5:
                            System.out.print("which burger's id that u wanna order : ");
                            int order_id = s.nextInt();
                            System.out.print("how many? : ");
                            int order_quantity = s.nextInt();
                            OrderDAO.placeOrder(con, order_id, order_quantity);
                            

                        case 6:
                            OrderDAO.showOrders(con);

                        case 0:
                            break;
                    }
                }
            
            catch(SQLException e){
                System.out.println("===== failed to connect with database =====");
                System.out.println(e);
                System.out.println("===========================================");
            }
        
        finally{
            con.close();
        }
    
            
        
    }
}
