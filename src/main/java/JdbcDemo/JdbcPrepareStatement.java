package JdbcDemo;
import JdbcDemo.utils.JdbcConfig;
import java.sql.*;

public class JdbcPrepareStatement {
    public static void main(String[] args) {
        try(
                Connection conn = DriverManager.getConnection(
                        JdbcConfig.getUrl(),
                        JdbcConfig.getPassword(),
                        JdbcConfig.getUser()
                );
                PreparedStatement pstmt = conn.prepareStatement(
                        "insert into book values (?, ?, ?)"
                );
                PreparedStatement pstmtSelect = conn.prepareStatement("select * from books");
        ){
            pstmt.setString(1, "Apple");
            pstmt.setInt(2, 200);
            pstmt.setInt(3, 300);
            int rowsInserted = pstmt.executeUpdate();
            System.out.println(rowsInserted + " records are inserted");

            ResultSet rset = pstmtSelect.executeQuery();
            while (rset.next()) {
                String title = rset.getString("title");
                String price = rset.getString("price");
                int qty = rset.getInt("qty");
                System.out.println(title + ", " + price + ", " + qty);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
