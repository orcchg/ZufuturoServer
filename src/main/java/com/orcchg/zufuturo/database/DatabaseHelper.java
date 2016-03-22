package com.orcchg.zufuturo.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseHelper {

    private static final String URL_DATABASE_DVDRENTAL = "jdbc:postgresql://localhost:9000/dvdrental";

    private Properties mProperties;
    private Connection mConnection;

    public DatabaseHelper() {
        try {
            Class.forName("org.postgresql.Driver");  // load PostgrSQL driver and register it to JDBC
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        mProperties = new Properties();
        mProperties.setProperty("user", "postgres");
        mProperties.setProperty("password", "111222qqq");
    }

    public void openConnection() {
        try {
            mConnection = DriverManager.getConnection(URL_DATABASE_DVDRENTAL, mProperties);
        } catch (SQLException sqle) {
        }
    }

    public void closeConnection() {
        try {
            mConnection.close();
        } catch (SQLException sqle) {
        }
    }
}

