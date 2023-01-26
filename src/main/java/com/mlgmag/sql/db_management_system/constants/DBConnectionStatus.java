package com.mlgmag.sql.db_management_system.constants;

public enum DBConnectionStatus {

    UNDEFINED("undefined"),
    VALID("valid"),
    INVALID("invalid");

    private final String status;

    DBConnectionStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
