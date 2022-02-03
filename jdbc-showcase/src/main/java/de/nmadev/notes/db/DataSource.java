package de.nmadev.notes.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {

    private static HikariConfig config;
    private static HikariDataSource hikariDataSource;

    static {
        config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/jdbc-showcase");
        config.setUsername("quarkus");
        config.setPassword("quarkus");
        config.setConnectionTimeout(1000);

        hikariDataSource = new HikariDataSource(config);
    }

    public static Connection getConnection() throws SQLException {
        return hikariDataSource.getConnection();
    }
}
