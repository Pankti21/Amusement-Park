package database;

import java.util.List;

public interface IDataBase {
    public void connect();
    public void query(String query);
    public List getResults();
    public void close();
}
