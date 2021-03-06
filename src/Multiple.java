import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Multiple {


    public static void main(String[] args) {

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        String url="jdbc:postgresql://127.0.0.1:5432/test_for_jdbc";
		String usr="postgres";
		String ps="kokoro123";

		
        try {

        	con = DriverManager.getConnection(url,usr,ps);

            String query = "SELECT id, name FROM authors WHERE Id=1;"
                         + "SELECT id, name FROM authors WHERE Id=2;"
                         + "SELECT id, name FROM authors WHERE Id=3";

            pst = con.prepareStatement(query);
            boolean isResult = pst.execute();

            do {
                rs = pst.getResultSet();

                while (rs.next()) {
                    System.out.print(rs.getInt(1));
                    System.out.print(": ");
                    System.out.println(rs.getString(2));
                }

                isResult = pst.getMoreResults();
            } while (isResult);


        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Multiple.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);

        } finally {

            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {

                Logger lgr = Logger.getLogger(Multiple.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
    }
}