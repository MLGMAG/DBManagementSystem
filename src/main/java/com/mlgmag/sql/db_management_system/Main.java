package com.mlgmag.sql.db_management_system;

import com.mlgmag.sql.db_management_system.service.ConfigService;

public class Main {
    public static void main(String[] args) {
        init();
        new ConsoleHelper();
    }

    public static void init() {
        ConfigService.init();
    }
}
