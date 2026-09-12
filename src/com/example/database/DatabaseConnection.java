package com.example.database;

import java.sql.*;

public class DatabaseConnection {
    DatabaseConfig config = new DatabaseConfig();

    private String url = config.getUrl();
    private String username = config.getUsername();
    private String password = config.getPassword();

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
