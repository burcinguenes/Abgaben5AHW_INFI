import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;


public class MyConnectionProvider {
	
	static Connection con;
	private static final MyConnectionProvider connection =  new MyConnectionProvider();
	private DataSource dataSource = null; 

	private MyConnectionProvider() {}
	 
	
	public void init(DataSource dataSource){
	  		this.dataSource = dataSource;
	  }
	  
	// Connection wird für Transaktions-Handling benötigt
	
	public Connection getConnection() throws SQLException {
	 		Connection con = dataSource.getConnection();
	 		return con; 
	 }
	
	
	
	public static void releaseConnection() {
		
		try { if(con != null) con.close();
			
		}catch(SQLException e) { e.printStackTrace(); }
	}

	public static MyConnectionProvider getInstance() {
		return connection;
	}
		

}
