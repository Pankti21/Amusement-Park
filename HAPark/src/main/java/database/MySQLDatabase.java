package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MySQLDatabase implements IDataBase{

    private String connectionURL = "";
    private String user = "";
    private String password = "";
    private ResultSet resultSet;

    @Override
    public void connect() {
        try {
            Connection conn = DriverManager.getConnection(connectionURL, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void query(String query) {

    }

    @Override
    public List getResults() {
        return null;
    }

    @Override
    public void close() {

    }
}
