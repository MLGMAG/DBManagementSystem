package com.mlgmag.sql.db_management_system.service;

import com.mlgmag.sql.db_management_system.constants.ConfigNames;
import com.mlgmag.sql.db_management_system.constants.DBConnectionStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

import static com.mlgmag.sql.db_management_system.constants.DBConnectionStatus.*;

public class DatabaseConnectionService {

    private static final Logger LOG = LoggerFactory.getLogger(DatabaseConnectionService.class);
    private static final String CONNECTION_STATUS_MESSAGE_PATTERN = "URL: '%s'. Status: '%s'";

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
        String status = getStatus();
        String dbUrl = getURL();
        String message = String.format(CONNECTION_STATUS_MESSAGE_PATTERN, dbUrl, status);
        LOG.info(message);
    }

    private String getStatus() {
        try {
            boolean isValid = connection.isValid(0);
            DBConnectionStatus dbConnectionStatus = isValid ? VALID : INVALID;
            return dbConnectionStatus.getStatus();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return UNDEFINED.getStatus();
        }
    }

    public static DatabaseConnectionService getInstance() {
        if (Objects.isNull(instance)) {
            instance = new DatabaseConnectionService();
        }
        return instance;
    }
}
