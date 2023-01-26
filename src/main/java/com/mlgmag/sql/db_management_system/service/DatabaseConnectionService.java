package com.mlgmag.sql.db_management_system.service;

import com.mlgmag.sql.db_management_system.constants.DBConnectionStatus;
import com.mlgmag.sql.db_management_system.model.DataBaseConnectionInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.mlgmag.sql.db_management_system.constants.DBConnectionStatus.*;

public class DatabaseConnectionService {

    private static final Logger LOG = LoggerFactory.getLogger(DatabaseConnectionService.class);
    private static final String CONNECTION_STATUS_MESSAGE_PATTERN = "URL: '%s'. Status: '%s'";

    private static DatabaseConnectionService instance;

    private final DataBaseConnectionInfo dataBaseConnectionInfo;

    private Connection connection;

    private DatabaseConnectionService() {
        this.dataBaseConnectionInfo = new DataBaseConnectionInfo();
        establishConnection(dataBaseConnectionInfo);
    }

    private void establishConnection(DataBaseConnectionInfo dataBaseConnectionInfo) {
        String url = dataBaseConnectionInfo.getUrl();
        String username = dataBaseConnectionInfo.getUsername();
        String password = dataBaseConnectionInfo.getPassword();

        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public String getDataBaseName() {
        return dataBaseConnectionInfo.getDatabaseName();
    }

    public Connection getConnection() {
        return connection;
    }

    public void logConnectionStatus() {
        String status = getStatus();
        String dbUrl = dataBaseConnectionInfo.getUrl();
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

    public static void init() {
        instance = new DatabaseConnectionService();
    }

    public static DatabaseConnectionService getInstance() {
        return instance;
    }
}
