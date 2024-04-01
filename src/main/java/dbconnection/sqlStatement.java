package dbconnection;

import javax.sql.rowset.CachedRowSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class sqlStatement {
    public static void executeQuery(String Query) throws SQLException {
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(Query);
             ResultSet rs = pst.executeQuery();) {
            while (rs.next()) {
                System.out.println(rs.getString("text"));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
