import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Prepared {

    public static void main(String[] args) {

        Connection con = null;
        PreparedStatement pst = null;

        String url="jdbc:postgresql://127.0.0.1:5432/test_for_jdbc";
		String usr="postgres";
		String ps="kokoro123";

		

        try {

            int id = 6;
            String author = "Trygve Gulbranssen";
            con = DriverManager.getConnection(url,usr,ps);

            String stm = "INSERT INTO authors(id, name) VALUES(?, ?)";
            pst = con.prepareStatement(stm);
            pst.setInt(1, id);
            pst.setString(2, author);                    
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Prepared.class.getName());
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
                Logger lgr = Logger.getLogger(Prepared.class.getName());
                lgr.log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
    }
}