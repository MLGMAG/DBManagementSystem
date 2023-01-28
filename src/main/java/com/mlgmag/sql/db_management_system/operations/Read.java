package com.mlgmag.sql.db_management_system.operations;

import com.mlgmag.sql.db_management_system.dao.DatabaseDAO;
import com.mlgmag.sql.db_management_system.service.DatabaseConnectionService;
import com.mlgmag.sql.db_management_system.util.ListUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Read {

    public Read() {
        DatabaseConnectionService databaseConnectionService = DatabaseConnectionService.getInstance();

        try {
            List<String> allTables = DatabaseDAO.getInstance().getAllTablesFromDB();
            ListUtils.addNumberPrefixToListItems(allTables).forEach(System.out::println);

            System.out.println("Choose table which will be read:");
            Scanner scan = new Scanner(System.in);
            int b = scan.nextInt();
            String table = allTables.get(b);

            Statement statement = databaseConnectionService.getConnection().createStatement();
            int Numbers_of_Columns = 1;
            StringBuilder SQLCommandReadTable = new StringBuilder("SELECT * FROM ");
            StringBuilder SQLCommandReadColumnLabel = new StringBuilder("SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = ");
            System.out.println("Choose table which will be read:");
            SQLCommandReadTable.append(table).append(";");
            SQLCommandReadColumnLabel.append("'").append(table).append("';");
            ResultSet resultSet = statement.executeQuery(String.valueOf(SQLCommandReadColumnLabel));
            Map<Integer, String> columnLabel = new HashMap<>();
            while (resultSet.next()) {
                columnLabel.put(Numbers_of_Columns, resultSet.getString("COLUMN_NAME"));
                Numbers_of_Columns++;
            }
            System.out.println("\n" + "SQLCommand --> " + SQLCommandReadTable);
            System.out.println("\n" + "Table:" + table + "\n");
            resultSet = statement.executeQuery(String.valueOf(SQLCommandReadTable));
            while (resultSet.next()) {
                for (int j = 1; j < Numbers_of_Columns; j++) {
                    System.out.println(columnLabel.get(j) + ":" + resultSet.getString(columnLabel.get(j)));
                }
                System.out.print("\n");
            }
        } catch (SQLException | NumberFormatException e) {
            System.out.println("Error:" + e);
        }
    }
}
