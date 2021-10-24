package database;

import java.util.List;
import java.util.Map;

public interface IDataBase {

    void connect();

    List<Map<String, Object>> query(String query);

    void close();

}
