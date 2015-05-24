
//STEP 1. Import required packages
import java.sql.*;


public class JDBC04_InsertRecords {

	public static String configFile = "";

	public static String Db = "";
	public static String Ip = "";
	public static String UserId = "";
	public static String Passwd = "";
	public static String Option = "";
	
	public static String Schema = "";
	public static String Table = "";
	public static String LogTable = "";
	public static Connection conn = null;
	public static Statement stmt = null;
 // JDBC driver name and database URL
	
//	static final String JDBC_DRIVER = "com.teradata.jdbc.TeraDriver";
//	static final String DB_URL = "jdbc:teradata://172.17.32.50/TMODE=TERA,CHARSET=ASCII,CLIENT_CHARSET=MS950";

	static final String JDBC_DRIVER = "org.postgresql.Driver";
	static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/test_for_jdbc";
	
 //  Database credentials
//	static final String USER = "ittiml";
//	static final String PASS = "tim201410";
	static final String USER = "postgres";
	static final String PASS = "kokoro123";
	
 
	public static void main(String[] args) {

		args = new String[] {
				"-table", "tabletest",
			//	"-db", "edata_p",
			//	"-schema", "emart_p",
			//	"-ip", "172.16.5.1",
			//	"-uid", "BTEMART",
			//	"-pwd", "ea79emax",
			//"-logtable", "datelog", //使用者設定放入log的tablename
			};
		if (!checkArgument(args)) {
			printSyntax();
			System.exit(1);
		}
		try{
			//STEP 2: Register JDBC driver
			Class.forName("org.postgresql.Driver");
//			Class.forName("com.teradata.jdbc.TeraDriver");
			
			//STEP 3: Open a connection
			System.out.println("Connecting to a selected database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Connected database successfully...");
			
			//STEP 4: Execute a query
				System.out.println("Create the table...");
			stmt = conn.createStatement();
			/*String sql = "CREATE TABLE " +
		                "PTEMP.DateLog" +
	                   " (SCHEMA_NAME VARCHAR(50), " +
	                   " TABLE_NAME VARCHAR(100), " + 
	                   " ERROR_MSG VARCHAR(4000), " + 
	                   " COMPARE_DATE date ) "; 
			stmt.executeUpdate(sql);
		*/
			String sql = "CREATE TABLE " + Table +
            " (id INTEGER not NULL," +
            " first VARCHAR(255), " + 
            " start_time timestamp, " + 
            " age INTEGER) " ;
        
			stmt.executeUpdate(sql);
			
		}
		catch(SQLException se){
			System.out.println("SQL ERROR");
			}
		catch(Exception e){
			System.out.println("JAVA ERROR");
			}
		
	/*	try{
			//STEP 2: Register JDBC driver
			Class.forName("com.teradata.jdbc.TeraDriver");
			
			//STEP 3: Open a connection
			System.out.println("Connecting to a selected database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Connected database successfully...");
			
			//STEP 4: Execute a query
			System.out.println("Inserting records into the table...");
			stmt = conn.createStatement();
			
				String sql = "INSERT INTO  "+Table+
                  " VALUES (102, 'Ali' , '2014-10-29 09:34:16.522' , 12)";
			stmt.executeUpdate(sql);
			
			sql = "INSERT INTO " +Table +
                  " VALUES (101, 'Mahnaz', 'Fatma', 11)";
			stmt.executeUpdate(sql);
			
			sql = "INSERT INTO " + Table +
                  " VALUES (102, 'Zaid', 'Khan', 55)";
			stmt.executeUpdate(sql);
			
			sql = "INSERT INTO " +Table +
                  " VALUES(103, 'Sumit', 'Mittal', 66)";
			stmt.executeUpdate(sql);
			
		
			System.out.println("Inserted records into the table...");
		}
		catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
			}
		catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
			}*/

		
		finally{
			//finally block used to close resources
			try{
				if(stmt!=null)
					conn.close();
				}
			catch(SQLException se){
				
			}   // do nothing
			try{
				if(conn!=null)
					conn.close();
				}
			catch(SQLException se){
				se.printStackTrace();
				}//end finally try
			}//end try
		System.out.println("Goodbye!");
		}//end main
 
 
	private static boolean checkArgument(String[] args) {
		boolean bret = true;
		
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("-help") || args[i].equals("--help")) {
				printSyntax();
				System.exit(0);				
		    } else if (args[i].equals("-c")) {
				configFile = args[++i];
			} 
			  else if (args[i].equals("-db")) {
				Db = args[++i];
			} else if (args[i].equals("-ip")) {
				Ip = args[++i];
			} else if (args[i].equals("-uid")) {
				UserId = args[++i];
			} else if (args[i].equals("-pwd")) {
				Passwd = args[++i];
			} else if (args[i].equals("-option")) {
				Option = args[++i];
			} else if (args[i].equals("-schema")) {
				Schema = args[++i];
			} else if (args[i].equals("-table")) {
				Table = args[++i];
			} else if (args[i].equals("-logtable")) {
				LogTable = args[++i];           //讀使用者設定放入log的tablename
			} else {
				System.out.println("Unknow argument '" + args[i] + "'");
				System.exit(1);
			}
		}
		
		
		
	/*	if (Db.equals(""))
			bret = false;
		
		if (Ip.equals(""))
			bret = false;
		
		if (UserId.equals(""))
			bret = false;
		
		if (Passwd.equals(""))
			bret = false;
		
		if (LogTable.equals(""))
		    bret = false;         //
		*/
		return bret;
    }

	private static void printSyntax() {
		System.out.println("java -jar dc.jar -c <config>" +
				           "  -db <dbname> [-option <urloption>]\n" +
				           " [-schema <schname>] -ip <ip> -uid <uid> -pwd <passwd> [-table <tablename>]\n" 
				           );
	}
	
}//end JDBC_InsertRecords_Example