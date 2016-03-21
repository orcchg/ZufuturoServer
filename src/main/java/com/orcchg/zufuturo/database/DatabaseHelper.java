package com.orcchg.zufuturo.database;

import java.sql.*;

public class DatabaseHelper {

    public DatabaseHelper() {
        try {
            Class.forName("org.postgresql.Driver");  // load PostgrSQL driver and register it to JDBC
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void openConnection() {

    }

    public void closeConnection() {

    }
}

