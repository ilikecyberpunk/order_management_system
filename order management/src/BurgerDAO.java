
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BurgerDAO {

    public static void addBurger(Connection con, String name, int price, int stock){
        try{
            String sql = "insert into burgers(name, price, stock) values(?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setInt(2, price);
            pstmt.setInt(3, stock);

            int result = pstmt.executeUpdate();
            System.out.println(" ㄴ Added successfully !!");

            pstmt.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }

    public static void readBurger(Connection con){
        try{
            String sql = "select * from burgers";
            PreparedStatement pstmt = con.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int price = rs.getInt("price");
                int stock = rs.getInt("stock");

                System.out.println(id + " | " + name + " | " + price + " | " + stock);
            }

            rs.close();
            pstmt.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }

    public static void updateBurger(Connection con, int id, String name, int price, int stock){
        try{
            String sql = "update burgers set name = ?, price = ?, stock = ? where id = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setString(1, name);
            pstmt.setInt(2, price);
            pstmt.setInt(3, stock);
            pstmt.setInt(4, id);

            int result = pstmt.executeUpdate();

            if(result > 0){
                System.out.println(" ㄴ Updated successfully !!");
            }
            else{
                System.out.println(" ㄴ failed to update :(");
            }

            pstmt.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }

    public static void deleteBurger(Connection con, int delete_id){
        String sql = "delete from burgers where id = ?";
        try{
            PreparedStatement pstmt = con.prepareStatement(sql);
            int result = pstmt.executeUpdate();

            if(result == 1){
                System.out.println(" ㄴ deleted successfully !!!");
            }
            else{
                System.out.println(" ㄴ failed to delete :(");
            }

            pstmt.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        
    }

    
}
