package com.team5.HAPark;

import database.IDataBase;
import database.MySQLDatabase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController

public class TestDBController {

    @GetMapping(value = "/testdb")
    public List<Map<String, Object>> dbTest() throws SQLException {
        IDataBase db = new MySQLDatabase();
        db.connect();
        List<Map<String, Object>> rows = db.query("SELECT*FROM user");
        db.connect();
        db.close();
        return rows;

    }
}
