import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
public class JDBC01_Loggin {
 
	public static void main(String[] argv) {
 
		System.out.println("-------- PostgreSQL JDBC Connection Testing ---------");
 
		try {
 
			Class.forName("org.postgresql.Driver");
 
		} catch (ClassNotFoundException e) {
 
			System.out.println("Where is your PostgreSQL JDBC Driver? "
					+ "Include in your library path!");
			e.printStackTrace();
			return;
 
		}
 
		System.out.println("PostgreSQL JDBC Driver Registered!");
 
		Connection con = null;
		Statement st = null;
	    ResultSet rs = null;
	
 
		try {
			String url="jdbc:postgresql://127.0.0.1:5432/test_for_jdbc";
			String usr="postgres";
			String ps="kokoro123";
 
			con = DriverManager.getConnection(url,usr,ps);
			st = con.createStatement();
            rs = st.executeQuery("SELECT VERSION()");
            
    
            con = DriverManager.getConnection(url, usr,ps);

            if (rs.next()) {
                System.out.println(rs.getString(1));
            }
			
			
 
		} catch (SQLException e) {
 
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
 
		}
 
		if (con != null) {
			System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		       }
	}
 
}