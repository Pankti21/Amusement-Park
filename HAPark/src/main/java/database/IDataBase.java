package database;

import java.util.List;
import java.util.Map;

public interface IDataBase {
    public void connect();
    public List<Map<String, Object>> query(String query);
    public void close();
}
