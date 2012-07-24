import java.sql.*;

public class TestJDBC {
  public static void main(String[] args) 
  throws SQLException, ClassNotFoundException {
	
	  // la conex de odbc la tienen q crear x el adm de fuentes odbc
	// ahi tienen q crear el nombre del dns q en ese casos es dns_access
	  
	String dbUrl = "jdbc:odbc:dns_access"; 
    String user = "";
    String password = "";
  
    // Load the driver (registers itself)
     Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
   
    Connection c = DriverManager.getConnection(
      dbUrl, user, password);
    Statement s = c.createStatement();
    

    // SQL code:
    ResultSet r = 
      s.executeQuery(
        "SELECT * " +
        "FROM movimientos " +        
        "ORDER BY fecha");
    
    while(r.next()) {
      // Capitalization doesn't matter:
      System.out.println(
        r.getString(1) + "- " 
        + r.getString(2)
         );
    }
    
    
    
    s.close(); // Also closes ResultSet
  }
} 