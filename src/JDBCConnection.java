import java.sql.ResultSet;

import java.util.Date;

public interface JDBCConnection {
    public void connect(String url,String user,String password) throws Exception;
    public ResultSet executeQuery(String sql) throws Exception;
    public void executeUpdate(String sql) throws Exception;
    public void close() throws Exception;
    
    public void begin() throws Exception;
    public void commit() throws Exception;
    public void rollback() throws Exception;
    
    public void prepare(String sql) throws Exception;
    public void setParameter(String sql, int i, Date value) throws Exception;
}