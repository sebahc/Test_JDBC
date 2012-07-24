import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.Date;
import java.util.Hashtable;
import java.util.Map;


public class GeneralConnection implements JDBCConnection {
    private Connection connection = null;
    private Statement statement = null;
    private Map<String, PreparedStatement> prepared = new Hashtable<String, PreparedStatement>();
    private ResultSet result = null;

    public GeneralConnection(String driver) throws Exception {
        try {
            Class.forName(driver);
        } catch (Exception e) {
            throw new Exception("Error al cargar el Driver: " + e.getMessage());
        }
    }

    public void connect(String url, String user, String password) throws Exception {
        try {
            connection = DriverManager.getConnection(url, user, password);
            connection.setAutoCommit(true);
            statement = connection.createStatement();
        } catch (Exception e) {
            throw new Exception("Error al establecer conexion, verifique los parametros: " + e.getMessage());
        }
    }

    public ResultSet executeQuery(String sql) throws Exception {
        if (result != null) {
            result.close();
        }
        result = statement.executeQuery(sql);
        return result;
    }

    public void executeUpdate(String sql) throws Exception {
        PreparedStatement statement=prepared.get(sql);
        if(statement!=null){
            statement.executeUpdate();
        }else{
            this.statement.executeUpdate(sql);
        }
    }

    public void close() throws Exception {
        if (result != null) {
            result.close();
        }
        
        if (statement != null) {
            statement.close();
        }

        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    public void begin() throws Exception {
        connection.setAutoCommit(false);
    }

    public void commit() throws Exception {
        connection.commit();
    }
   
    public void rollback() throws Exception {
        connection.rollback();
    }

    public void prepare(String sql) throws Exception {
        PreparedStatement statement=connection.prepareStatement(sql);
        prepared.put(sql, statement);
    }

    public void setParameter(String sql, int i, Date value) throws Exception {
        PreparedStatement statement=prepared.get(sql);
        if(value!=null){
            statement.setDate(i, new java.sql.Date(value.getTime()));
        }else{
            statement.setDate(i, null);
        }
    }
}
