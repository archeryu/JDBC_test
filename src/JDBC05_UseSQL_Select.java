
//STEP 1. Import required packages
import java.sql.*;

public class JDBC05_UseSQL_Select {
   // JDBC driver name and database URL
   static final String JDBC_DRIVER = "org.postgresql.Driver";  
   static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/test_for_jdbc";

   //  Database credentials
   static final String USER = "postgres";
   static final String PASS = "kokoro123";
   
   public static void main(String[] args) {
   Connection conn = null;
   Statement stmt = null;
   try{
      //STEP 2: Register JDBC driver
      Class.forName("org.postgresql.Driver");

      //STEP 3: Open a connection
      System.out.println("Connecting to database...");
      conn = DriverManager.getConnection(DB_URL,USER,PASS);

      //STEP 4: Execute a query
      System.out.println("Creating statement...");
      stmt = conn.createStatement();
     //SQL in Select      
      String sql;
      sql = "SELECT * FROM books";
      ResultSet rs = stmt.executeQuery(sql);
      System.out.println("select ���\");
      //STEP 5: Extract data from result set
      while(rs.next()){
      //   Retrieve by column name
         int id  = rs.getInt("id");
         int author_id = rs.getInt("author_id");
     //   String first = rs.getString("first");
     //    String last = rs.getString("last");

      //   Display values
         System.out.print("(ID: " + id+" ,");
         System.out.print(" author_id: " + author_id+" ),\n");
      //   System.out.print(", First: " + first);
      //   System.out.println(", Last: " + last);
      }
      //STEP 6: Clean-up environment
      rs.close();
      stmt.close();
      conn.close();
   }catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
   }finally{
      //finally block used to close resources
      try{
         if(stmt!=null)
            stmt.close();
      }catch(SQLException se2){
      }// nothing we can do
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }//end finally try
   }//end try
   System.out.println("Goodbye!");
}//end main
}//end FirstExample

