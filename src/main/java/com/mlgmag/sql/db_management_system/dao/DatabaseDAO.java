package com.mlgmag.sql.db_management_system.dao;

import com.mlgmag.sql.db_management_system.model.TableData;
import com.mlgmag.sql.db_management_system.service.DatabaseConnectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseDAO {

    private static final Logger LOG = LoggerFactory.getLogger(DatabaseDAO.class);

    private static final String GET_TABLES_QUERY_PATTERN =
            "SELECT TABLE_NAME AS tableName FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = '%s'";

    private static final String GET_TABLE_DATA_QUERY_PATTERN = "SELECT * FROM %s";

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

    public TableData getTableData(String table) {
        String selectGetTableDataQuery = String.format(GET_TABLE_DATA_QUERY_PATTERN, table);
        LOG.info("SQL Query: {}", selectGetTableDataQuery);

        try (Statement statement = DatabaseConnectionService.getInstance().getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(selectGetTableDataQuery)
        ) {
            List<String> tableColumns = getTableColumns(resultSet);
            List<List<String>> tableRows = getTableRows(resultSet);
            return new TableData(tableColumns, tableRows);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private List<String> getTableColumns(ResultSet resultSet) {
        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            List<String> columns = new ArrayList<>();
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                columns.add(columnName);
            }

            return columns;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private List<List<String>> getTableRows(ResultSet resultSet) {
        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            List<List<String>> rows = new ArrayList<>();
            while (resultSet.next()) {
                List<String> row = new ArrayList<>();
                for (int i = 1; i <= columnCount; i++) {
                    String columnValue = resultSet.getString(i);
                    row.add(columnValue);
                }
                rows.add(row);
            }

            return rows;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void init() {
        instance = new DatabaseDAO();
    }

    public static DatabaseDAO getInstance() {
        return instance;
    }

}
