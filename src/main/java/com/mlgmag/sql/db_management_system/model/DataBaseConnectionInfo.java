package com.mlgmag.sql.db_management_system.model;

import com.mlgmag.sql.db_management_system.service.ConfigService;

import static com.mlgmag.sql.db_management_system.constants.ConfigNames.*;

public class DataBaseConnectionInfo {

    private final String databaseName;
    private final String urlSchema;
    private final String host;
    private final String port;
    private final String username;
    private final String password;

    private static final String URL_PATTERN = "%s://%s:%s/%s";

    public DataBaseConnectionInfo() {
        ConfigService configService = ConfigService.getInstance();

        this.databaseName = configService.getConfig(DB_NAME_CONFIG);
        this.urlSchema = configService.getConfig(DB_URL_SCHEMA_CONFIG);
        this.host = configService.getConfig(DB_HOST_CONFIG);
        this.port = configService.getConfig(DB_PORT_CONFIG);
        this.username = configService.getConfig(DB_USERNAME_CONFIG);
        this.password = configService.getConfig(DB_PASSWORD_CONFIG);
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public String getUrl() {
        return String.format(URL_PATTERN, urlSchema, host, port, databaseName);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
