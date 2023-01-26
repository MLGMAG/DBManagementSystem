package com.mlgmag.sql.db_management_system.model;

import com.mlgmag.sql.db_management_system.constants.ConfigNames;
import com.mlgmag.sql.db_management_system.service.ConfigService;

public class DataBaseConnectionInfo {

    private final String databaseName;
    private final String url;
    private final String username;
    private final String password;

    public DataBaseConnectionInfo() {
        ConfigService configService = ConfigService.getInstance();

        this.databaseName = configService.getConfig(ConfigNames.DB_NAME_CONFIG);
        this.url = configService.getConfig(ConfigNames.DB_URL_CONFIG);
        this.username = configService.getConfig(ConfigNames.DB_USERNAME_CONFIG);
        this.password = configService.getConfig(ConfigNames.DB_PASSWORD_CONFIG);
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
