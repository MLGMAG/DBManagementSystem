package com.mlgmag.sql_db_management.operations;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * Created by Mag on 17.08.2017.
 *
 */

public class Read {

    public Read() {
        StringBuilder ShowTables = new StringBuilder("SHOW TABLES FROM ");
        ShowTables.append(DataBaseConnection.getDataBaseName());
        try {
            DataBaseConnection DBC = new DataBaseConnection();
            Statement statement = DBC.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(String.valueOf(ShowTables));
            System.out.println("Tables in DataBase:");
            int a = 1;
            Map<Integer, String> tableMap = new HashMap<>();
            while (resultSet.next()) {
                tableMap.put(a, resultSet.getString("Tables_in_MyDataBase"));
                System.out.println(a + ":'" + resultSet.getString("Tables_in_MyDataBase") + "'");
                a++;
            }
            int Numbers_of_Columns = 1;
            StringBuilder SQLCommandReadTable = new StringBuilder("SELECT * FROM ");
            StringBuilder SQLCommandReadColumnLabel = new StringBuilder("SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = ");
            System.out.println("Choose table which will be read:");
            Scanner scan = new Scanner(System.in);
            int b = scan.nextInt();
            SQLCommandReadTable.append(tableMap.get(b)).append(";");
            SQLCommandReadColumnLabel.append("'").append(tableMap.get(b)).append("';");
            resultSet = statement.executeQuery(String.valueOf(SQLCommandReadColumnLabel));
            Map<Integer, String> columnLabel = new HashMap<>();
            while (resultSet.next()) {
                columnLabel.put(Numbers_of_Columns, resultSet.getString("COLUMN_NAME"));
                Numbers_of_Columns++;
            }
            System.out.println("\n" + "SQLCommand --> " + SQLCommandReadTable);
            System.out.println("\n" + "Table:" + tableMap.get(b) + "\n");
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
