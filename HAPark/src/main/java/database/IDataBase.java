package database;

import java.util.LinkedHashMap;
import java.util.List;

public interface IDataBase {
    public void connect();
    public List<LinkedHashMap<String, Object>> query(String query);
    public void close();
}
