package com.example.database;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseConfig {
    
    private final Properties properties = new Properties();

    public DatabaseConfig() {
        try (InputStream input = getClass()
                .getClassLoader()
                .getResourceAsStream("database.properties")) {
                    
                    if (input == null) {
                        throw new RuntimeException("database.properties not found");
                    }

                    properties.load(input);

                } catch (IOException e) {
                    throw new RuntimeException("Failed to load database.properties", e);
                }

            }

                public String getUrl() {
                    return properties.getProperty("db.url");
                }

                public String getUsername() {
                    return properties.getProperty("db.username");
                }

                public String getPassword() {
                    return properties.getProperty("db.password");
                }

    }
