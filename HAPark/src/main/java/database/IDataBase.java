package database;

import java.sql.ResultSet;

public interface IDataBase {
    public void connect();
    public ResultSet query(String query); //what should return type be??
}
