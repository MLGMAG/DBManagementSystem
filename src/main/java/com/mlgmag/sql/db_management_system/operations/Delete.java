package com.mlgmag.sql.db_management_system.operations;

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
public class Delete {

    public Delete() {
        System.out.println("Note in which table you want delete:");
        StringBuilder ShowTables = new StringBuilder("SHOW TABLES FROM ");
        StringBuilder SQLCommandReadColumnLabel = new StringBuilder("SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = ");
        StringBuilder SQLCommandDelete = new StringBuilder("DELETE FROM ");
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
            Scanner scan = new Scanner(System.in);
            int b = scan.nextInt();
            SQLCommandDelete.append(tableMap.get(b)).append(" WHERE ");
            SQLCommandReadColumnLabel.append("'").append(tableMap.get(b)).append("';");
            Map<Integer, String> columnLabel = new HashMap<>();
            resultSet = statement.executeQuery(String.valueOf(SQLCommandReadColumnLabel));
            int Numbers_of_Columns = 1;
            while (resultSet.next()) {
                columnLabel.put(Numbers_of_Columns, resultSet.getString("COLUMN_NAME"));
                Numbers_of_Columns++;
            }
            System.out.println("Enter " + columnLabel.get(1) + ":");
            SQLCommandDelete.append(columnLabel.get(1)).append(" = '");
            b = scan.nextInt();
            SQLCommandDelete.append(b).append("';");
            System.out.println("SQLCommand -->" + SQLCommandDelete);
            statement.execute(String.valueOf(SQLCommandDelete));
        } catch (SQLException | NumberFormatException e) {
            System.out.println("Error:" + e);
        }
    }
}
