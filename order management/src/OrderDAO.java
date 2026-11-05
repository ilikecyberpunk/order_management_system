import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class OrderDAO{
    public static void placeOrder(Connection con, int order_id, int order_quantity){
        String sql1 = "select price from burgers where id = ?";
        String sql2 = "insert into orders(burger_id, quantity, total_price) values(?, ?, ?)";

        try{
            
            con.setAutoCommit(false);

            PreparedStatement pstmt = con.prepareStatement(sql1);
            pstmt.setInt(1, order_id);
            ResultSet rs = pstmt.executeQuery();

            int price = 0;

            if(rs.next()){
                price = rs.getInt("price");
            }else{
                System.out.println("failed to read price");
                return; //메소드 끝내버리기
            }

            PreparedStatement pstmt2 = con.prepareStatement(sql2);
            pstmt2.setInt(1, order_id);
            pstmt2.setInt(2, order_quantity);
            pstmt2.setInt(3, price * order_quantity);
            
            int result = pstmt2.executeUpdate();


            if(result > 0){
                con.commit();
                System.out.println("===== ordered successfully !!! =====");
            }
            else{
                System.out.println("===== failed to order =====");
                con.rollback();
            }




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

    public static void showOrders(Connection con){
        String sql = "select * from orders";

        try{
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                int burger_id = rs.getInt("burger_id");
                int quantity = rs.getInt("quantity");
                int total_price = rs.getInt("total_price");

                System.out.println("order id : " + id + " | burger's id : " + burger_id + " | quantity : " + quantity + " | total price : " + total_price);

            }
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }
}