package com.mlgmag.sql.db_management_system.dao;

import com.mlgmag.sql.db_management_system.service.DatabaseConnectionService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseDAO {

    private static final String GET_TABLES_QUERY_PATTERN =
            "SELECT TABLE_NAME AS tableName FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = '%s'";

    private static DatabaseDAO instance;

    private DatabaseDAO() {
    }

    public List<String> getAllTablesFromDB() {
        DatabaseConnectionService databaseConnectionService = DatabaseConnectionService.getInstance();
        String dataBaseName = databaseConnectionService.getDataBaseName();
        String showTablesQuery = String.format(GET_TABLES_QUERY_PATTERN, dataBaseName);
        String resultSetTableColumn = "tableName";

        List<String> tables = new ArrayList<>();
        try (
                Statement statement = databaseConnectionService.getConnection().createStatement();
                ResultSet resultSet = statement.executeQuery(showTablesQuery)
        ) {
            while (resultSet.next()) {
                String table = resultSet.getString(resultSetTableColumn);
                tables.add(table);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return tables;
    }

    public static void init() {
        instance = new DatabaseDAO();
    }

    public static DatabaseDAO getInstance() {
        return instance;
    }

}
