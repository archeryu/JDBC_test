import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class NotPrepared {

    public static void main(String[] args) {

        Connection con = null;
        Statement st = null;

        String url="jdbc:postgresql://127.0.0.1:5432/test_for_jdbc";
		String usr="postgres";
		String ps="kokoro123";

		

        try {

        	con = DriverManager.getConnection(url,usr,ps);

            st = con.createStatement();

            for (int i=1; i<=1000; i++) {
                String query = "INSERT INTO Testing(Id) VALUES(" + 2*i + ")";
                st.executeUpdate(query);
            }

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(NotPrepared.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);

        } finally {

            try {
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(NotPrepared.class.getName());
                lgr.log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
    }
}