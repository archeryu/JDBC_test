import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;


public class PreparedStatement_test {

	 public static void main(String[] args) {
	        
	        Connection con = null;
	        PreparedStatement pst = null;
	        ResultSet rs = null;

	        String url="jdbc:postgresql://127.0.0.1:5432/test_for_jdbc";
			String usr="postgres";
			String ps="kokoro123";

			
	        try {

	        	con = DriverManager.getConnection(url,usr,ps);
	        	String query = "SELECT ?,? From authors,books WHERE authors.id=books.author_id";
	        	pst =  con.prepareStatement(query); //先建立一個 SQL 語句並回傳一個 PreparedStatement 物件
	        	pst.setString(1,"name"); //對應到第一個問號
	        	pst.setString(2,"title"); //對應到第二個問號
	        	//pst.setString(3, "password"); //對應到第三個問號
	        	rs = pst.executeQuery();
	        	//rs =pst.executeUpdate();  //真正執行
	        	
	        	
	        //    String query = "SELECT name, title From authors, " +
	        //      "books WHERE authors.id=books.author_id";
	            
	        //    pst = con.prepareStatement(query);

	        //   rs = pst.executeQuery();
	        	
	        	while (rs.next()) {
	        	   // System.out.print("Column 1 returned ");
	        	    System.out.print(rs.getString(1));
	        	    System.out.print(rs.getString(2));
	        	    rs.close();
	        	}
	        	
	        	pst.close();

				//System.out.println(pst);
			//	System.out.println(rs.getString(1));
				//ResultSetMetaData MetaData =rs.getMetaData();
				//String str1=MetaData.getColumnName(1) +"  "+MetaData.getColumnName(2);
			
				//System.out.println(str1);
			
				

				
	        } catch (SQLException ex) {
	           
	        
	    }
	  }
}
