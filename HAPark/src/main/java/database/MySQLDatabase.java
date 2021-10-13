package database;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDatabase implements IDataBase{

    String connectionURL = "";
    String user = "";
    String password = "";

    @Override
    public void connect() {
        try {
            Connection conn = DriverManager.getConnection(connectionURL, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ResultSet query(String query) {
        //TODO
        return null;
    }
}
