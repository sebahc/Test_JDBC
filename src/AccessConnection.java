import java.sql.ResultSet;

/*
 * Ejemplo de Conexion Access en JDBC a traves de ODBC version 2.0
 */

public class AccessConnection extends GeneralConnection {
	public AccessConnection() throws Exception {
		super("sun.jdbc.odbc.JdbcOdbcDriver");
	}
	
	public void connect(String server, String usuario, String password) throws Exception {
		String url="jdbc:odbc:"+server;
		super.connect(url, usuario, password);
	}
	
		
	public static void main(String[] args) throws Exception {
		try{
		AccessConnection connection=new AccessConnection();
		connection.connect("dns_access","","");
		
		connection.begin();
		
		ResultSet result=connection.executeQuery("select * from bancos order by codigo");
		while(result.next ()){
			System.out.println(result.getString (1)+" - "+result.getString (2));
		}
		
		System.out.println("\n============================================\n");
		
		
		
		//connection.executeUpdate("update bancos set banco='Ban Rio' where codigo=3");
		
		
		connection.commit();
		connection.close();
		
		
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
