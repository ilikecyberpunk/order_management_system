
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BurgerDAO {

    public static void addBurger(Connection con, String name, int price, int stock){
        try{
            con.setAutoCommit(false);
            String sql = "insert into burgers(name, price, stock) values(?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setInt(2, price);
            pstmt.setInt(3, stock);

            int result = pstmt.executeUpdate();
            if(result == 1){
                System.out.println(" ㄴ Added successfully !!");
                con.commit();
            }else{
                System.out.println("Error");
                con.rollback();
            }

            pstmt.close();
        }
        catch(SQLException e){
            System.out.println(e);
            try{
                            con.rollback();
            }
            catch(SQLException ee){
                System.out.println(ee);
            }
        }
    }

    public static void readBurger(Connection con){
        try{
            String sql = "select * from burgers";
            PreparedStatement pstmt = con.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();

            
            boolean found = false;
            while(rs.next()){
                found = true;

                int id = rs.getInt("id");
                String name = rs.getString("name");
                int price = rs.getInt("price");
                int stock = rs.getInt("stock");

                System.out.println(id + " | " + name + " | " + price + " | " + stock);
            }

            if(found == false){
                System.out.println("ts aint no data cuh");
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
            con.setAutoCommit(false);
            String sql = "update burgers set name = ?, price = ?, stock = ? where id = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setString(1, name);
            pstmt.setInt(2, price);
            pstmt.setInt(3, stock);
            pstmt.setInt(4, id);

            int result = pstmt.executeUpdate();

            if(result > 0){
                System.out.println(" ㄴ Updated successfully !!");
                con.commit();
            }
            else{
                System.out.println(" ㄴ failed to update :(");
                con.rollback();
            }

            pstmt.close();
        }
        catch(SQLException e){
            System.out.println(e);
            try{
                con.rollback();
            }
            catch(SQLException ee){
                System.out.println(ee);
            }
        }
    }

    public static void deleteBurger(Connection con, int delete_id){
        String sql = "delete from burgers where id = ?";
        try{
            con.setAutoCommit(false);
            PreparedStatement pstmt = con.prepareStatement(sql);
            int result = pstmt.executeUpdate();

            if(result > 0){
                System.out.println(" ㄴ deleted successfully !!!");
                con.commit();
            }
            else{
                System.out.println(" ㄴ failed to delete :(");
                con.rollback();
            }

            pstmt.close();
        }
        catch(SQLException e){
            System.out.println(e);
            try{
                con.rollback();
            }catch(SQLException ee){
                System.out.println(ee);
            }
        }
        
    }

    
}
