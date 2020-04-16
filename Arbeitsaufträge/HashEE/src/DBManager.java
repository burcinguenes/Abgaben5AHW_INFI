
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DBManager {

	static PreparedStatement ps;
	static Connection con;
	
	
//--------------------------------Benutzer für Login
	public boolean existsUsername(String u) throws SQLException{
		
		try {
			con=MyConnectionProvider.getInstance().getConnection();
			ps=con.prepareStatement("select COUNT(*) from Benutzer where username=?");
			ps.setString(1, u);
			
			ResultSet rs = ps.executeQuery();
			boolean ok = true;
			if(rs.next()) 			
			{
				ok=rs.getInt(1)>0;
			}
			return ok;
			
		}catch(Exception e) {
			System.out.println(e);
			return true;
		}
		finally {
			if(ps != null)ps.close();
			MyConnectionProvider.releaseConnection();
		}
	}
	
	public void insertBenutzer(Benutzer c) throws SQLException {
	
		try {
			
			con=MyConnectionProvider.getInstance().getConnection();			
			ps = con.prepareStatement("insert into Benutzer(username, password) values (?,?)");
			ps.setString(1, c.getUsername());
			ps.setString(3, c.getPassword());
			
			ps.executeUpdate();
			
			MyConnectionProvider.releaseConnection();
			
		}catch(Exception e) {
			System.out.println(e);
		}finally {
			if(ps != null)ps.close();
			MyConnectionProvider.releaseConnection();
		}
		
        System.out.println("Neuer Benutzer angelegt.");

		
	}

	
	public Benutzer getCostumer(String username) throws SQLException {

		Benutzer c = new Benutzer();
		
		try {
			con=MyConnectionProvider.getInstance().getConnection();
			ps=con.prepareStatement("select * from Benutzer where username=?");
			ps.setString(1, username);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) 			
			{
				c.setPassword(rs.getString(2));
			}
			
		}catch(Exception e) {
			System.out.println(e);
		}
		finally {
			if(ps != null)ps.close();
			MyConnectionProvider.releaseConnection();
		}
		
		MyConnectionProvider.releaseConnection();
		return c;
	}
	
	
}
