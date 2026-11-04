import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BurgerDAO {

    public void addBurger(Connection con,String burger_name,int burger_price,int burger_stock) {

        String sql = "INSERT INTO burgers(name, price, stock) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, burger_name);
            pstmt.setInt(2, burger_price);
            pstmt.setInt(3, burger_stock);

            int result = pstmt.executeUpdate();

            if (result > 0) {
                System.out.println("Burger added successfully!");
            } else {
                System.out.println("Failed to add burger.");
            }

        } catch (SQLException e) {
            System.out.println("Database error while adding burger.");
            e.printStackTrace();
        }
    }
}