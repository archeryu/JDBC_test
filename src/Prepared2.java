import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Prepared2 {

    public static void main(String[] args) {

        Connection con = null;
        PreparedStatement pst = null;
        
        String url="jdbc:postgresql://127.0.0.1:5432/test_for_jdbc";
		String usr="postgres";
		String ps="kokoro123";

		
        try {

        	con = DriverManager.getConnection(url,usr,ps);

            pst = con.prepareStatement("INSERT INTO Testing(Id) VALUES(?)");

            for (int i = 1; i <= 1000; i++) {
                pst.setInt(1, i * 2);
                pst.executeUpdate();
            }

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Prepared2.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);

        } finally {

            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(Prepared2.class.getName());
                lgr.log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
    }
}