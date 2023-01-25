package com.mlgmag.sql.db_management_system.operations;

import com.mlgmag.sql.db_management_system.constants.ConfigNames;
import com.mlgmag.sql.db_management_system.service.ConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {

    private static final Logger LOG = LoggerFactory.getLogger(DataBaseConnection.class);

    private final String databaseName;
    private final String url;
    private final String username;
    private final String password;

    private Connection connection;

    public DataBaseConnection() {
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

    public static String getDataBaseName() {
        return ConfigService.getConfig(ConfigNames.DB_NAME_CONFIG);
    }

    public static String getURL() {
        return ConfigService.getConfig(ConfigNames.DB_URL_CONFIG);
    }

    Connection getConnection() {
        return connection;
    }

    public void logConnectionStatus() {
        try {
            if (!connection.isClosed()) {
                LOG.info("Connection successfully");
            } else {
                LOG.info("Connection is closed");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
