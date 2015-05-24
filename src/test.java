import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Formatter;


public class test {


    public static void main(String[] args) {
        
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        String url="jdbc:postgresql://127.0.0.1:5432/Edas";
		String usr="postgres";
		String ps="kokoro123";

		
        try {

        	con = DriverManager.getConnection(url,usr,ps);
            String query = "SELECT jobuid,jobname,description From job where jobname like '%PRD_OFF%'" ;
            pst = con.prepareStatement(query);      

            rs = pst.executeQuery();

			//System.out.println(pst);
			
			ResultSetMetaData meta =rs.getMetaData();
			String colname1 = meta.getColumnName(1);
            String colname2 = meta.getColumnName(2);
            String colname3 = meta.getColumnName(3);

            Formatter fmt1 = new Formatter();
            fmt1.format("%-36s %-35s %s", colname1, colname2, colname3);
// "%-18s%s" The first column is 18 characters wide and is aligned to the left.
            System.out.println(fmt1);
     
                int colunm =3;
                //只跑三行資料
			    while(colunm!=0){
		    	rs.next();
		    	String jobuid=rs.getString(1);
		    	String jobname=rs.getString(2);
		    	String description=rs.getString(3);
		    	
		    	Formatter fmt2 = new Formatter();
		    	fmt2.format("%-36s %-35s %s", jobuid,jobname,description);
		    	System.out.print(fmt2);
		    	System.out.print('\n');	
		    	colunm--;
		    	
		    }
		    
		  //  rs.close();
	        pst.close();
			
        } catch (SQLException ex) {
        	System.out.print("SQL error");
        
    }
  }
}