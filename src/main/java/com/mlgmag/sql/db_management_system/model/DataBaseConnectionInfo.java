package com.mlgmag.sql.db_management_system.model;

import com.mlgmag.sql.db_management_system.constants.ConfigNames;
import com.mlgmag.sql.db_management_system.service.ConfigService;

public class DataBaseConnectionInfo {

    private final String databaseName;
    private final String url;
    private final String username;
    private final String password;

    public DataBaseConnectionInfo() {
        this.databaseName = ConfigService.getConfig(ConfigNames.DB_NAME_CONFIG);
        this.url = ConfigService.getConfig(ConfigNames.DB_URL_CONFIG);
        this.username = ConfigService.getConfig(ConfigNames.DB_USERNAME_CONFIG);
        this.password = ConfigService.getConfig(ConfigNames.DB_PASSWORD_CONFIG);
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
