package com.mlgmag.sql.db_management_system;

import com.mlgmag.sql.db_management_system.service.ConfigService;
import com.mlgmag.sql.db_management_system.service.DatabaseConnectionService;

public class Main {
    public static void main(String[] args) {
        init();
        new ConsoleHelper();
    }

    public static void init() {
        ConfigService.init();
        DatabaseConnectionService.init();
    }
}
