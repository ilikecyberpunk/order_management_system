import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        // DB 연결
        Scanner s = new Scanner(System.in);

        System.out.println("===== lets connect database first =====");

        String url = "jdbc:mysql://localhost:3306/burger_shop";

        System.out.print("User ID : ");
        String user = s.next();

        System.out.print("Password : ");
        String password = s.next();

        try (Connection con = DriverManager.getConnection(url, user, password)) {

            System.out.println("===== database connected !! ===== \n\n");

            // DAO 객체 생성
            BurgerDAO burgerDAO = new BurgerDAO();

            while (true) {

                System.out.println("===== Burger Order Management System =====");
                System.out.println("1. Add Burger");
                System.out.println("2. Read Burgers");
                System.out.println("3. Update Burger");
                System.out.println("4. Delete Burger");
                System.out.println("5. Place an Order");
                System.out.println("6. View Orders");
                System.out.println("0. Exit \n");

                System.out.print("Select option : ");

                int option = s.nextInt();

                switch (option) {

                    case 1:
                        System.out.print("burger name : ");
                        String burger_name = s.next();

                        System.out.print("burger price : ");
                        int burger_price = s.nextInt();

                        System.out.print("burger stock : ");
                        int burger_stock = s.nextInt();

                        burgerDAO.addBurger(
                            con,
                            burger_name,
                            burger_price,
                            burger_stock
                        );

                        break;

                    case 2:
                        break;

                    case 3:
                        break;

                    case 4:
                        break;

                    case 5:
                        break;

                    case 6:
                        break;

                    case 0:
                        System.out.println("Goodbye!");
                        return;

                    default:
                        System.out.println("Invalid option.");
                }
            }

        } catch (SQLException e) {

            System.out.println("===== failed to connect with database =====");
            System.out.println(e);
            System.out.println("===========================================");
        } finally {
            s.close();
        }
    }
}