package com.mlgmag.sql.db_management_system.service;

import com.mlgmag.sql.db_management_system.constants.ConfigNames;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class DatabaseConnectionService {

    private static final Logger LOG = LoggerFactory.getLogger(DatabaseConnectionService.class);

    private static DatabaseConnectionService instance;

    private final String databaseName;
    private final String url;
    private final String username;
    private final String password;

    private Connection connection;

    private DatabaseConnectionService() {
        this.databaseName = ConfigService.getConfig(ConfigNames.DB_NAME_CONFIG);
        this.url = ConfigService.getConfig(ConfigNames.DB_URL_CONFIG);
        this.username = ConfigService.getConfig(ConfigNames.DB_USERNAME_CONFIG);
        this.password = ConfigService.getConfig(ConfigNames.DB_PASSWORD_CONFIG);

        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public String getDataBaseName() {
        return databaseName;
    }

    public String getURL() {
        return url;
    }

    public Connection getConnection() {
        return connection;
    }

    public void logConnectionStatus() {
        try {
            if (!connection.isClosed()) {
                LOG.info("Connection is open");
            } else {
                LOG.info("Connection is closed");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static DatabaseConnectionService getInstance() {
        if (Objects.isNull(instance)) {
            instance = new DatabaseConnectionService();
        }
        return instance;
    }
}
