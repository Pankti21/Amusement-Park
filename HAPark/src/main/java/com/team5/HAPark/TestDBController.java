package com.team5.HAPark;


import database.IDataBase;
import database.MySQLDatabase;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;

@RestController

public class TestDBController {

    @GetMapping(value = "/testdb")
    public List<LinkedHashMap<String, Object>> dbTest() throws SQLException {
        IDataBase db = new MySQLDatabase();
        db.connect();
        List<LinkedHashMap<String, Object>> rows = db.query("SELECT*FROM users");
        System.out.println("method called");
        db.connect();
        db.close();
        return rows;

    }
}
