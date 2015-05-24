import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Retrieve {
    
    public static void main(String[] args) {

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        String url="jdbc:postgresql://127.0.0.1:5432/test_for_jdbc";
		String usr="postgres";
		String ps="kokoro123";

		
        try {
            
        	con = DriverManager.getConnection(url,usr,ps);
            pst = con.prepareStatement("SELECT * FROM authors");
            rs = pst.executeQuery();
            
            ResultSetMetaData meta =rs.getMetaData();
			String colname1 = meta.getColumnName(1);
            String colname2 = meta.getColumnName(2);
            Formatter fmt1 = new Formatter();
            fmt1.format("%-9s %s", colname1, colname2);
// "%-18s%s" The first column is 18 characters wide and is aligned to the left.
            System.out.println(fmt1);

           while (rs.next()) {
                System.out.print(rs.getInt(1));
                System.out.print(": ");
                System.out.println(rs.getString(2));
            }

        } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(Retrieve.class.getName());
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
                Logger lgr = Logger.getLogger(Retrieve.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
    }
}