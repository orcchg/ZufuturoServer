package com.orcchg.zufuturo.test;

import java.sql.*;
import java.util.Properties;
import org.junit.Test;
import junit.framework.*;

public class DbQueryTest {
    private static final String URL_DATABASE_DVDRENTAL = "jdbc:postgresql://localhost:9000/dvdrental";

    @Test
    public void testConnection() {
        try {
            Class.forName("org.postgresql.Driver");  // load PostgrSQL driver and register it to JDBC
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Properties properties = new Properties();
        properties.setProperty("user", "postgres");
        properties.setProperty("password", "111222qqq");

        StringBuilder builder = new StringBuilder("Data: \n");
        Statement statement = null;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL_DATABASE_DVDRENTAL, properties);
            String query = "SELECT first_name,last_name FROM customer;";
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                builder.append(result.getString("first_name")).append(", ").append(result.getString("last_name")).append("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) { statement.close(); }
                if (connection != null) { connection.close(); }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        System.out.println(builder.toString());
    }
}

