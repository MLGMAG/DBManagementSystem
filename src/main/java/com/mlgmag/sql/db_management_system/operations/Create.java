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

public class Create {

    public Create() {
        System.out.println("Chose Table to insert:");
        StringBuilder ShowTables = new StringBuilder("SHOW TABLES FROM ");
        ShowTables.append(DataBaseConnection.getDataBaseName());
        try {
            DataBaseConnection DBC = new DataBaseConnection();
            Statement statement = DBC.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(String.valueOf(ShowTables));
            int a = 1;
            Map<Integer, String> tableMap = new HashMap<>();
            while (resultSet.next()) {
                tableMap.put(a, resultSet.getString("Tables_in_MyDataBase"));
                System.out.println(a + ":'" + resultSet.getString("Tables_in_MyDataBase") + "'");
                a++;
            }
            Scanner scan = new Scanner(System.in);
            int ChosenTable = scan.nextInt();
            StringBuilder SQLCommandInsert = new StringBuilder("INSERT INTO ");
            StringBuilder SQLCommandReadColumnLabel = new StringBuilder("SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = ");
            SQLCommandReadColumnLabel.append("'").append(tableMap.get(ChosenTable)).append("';");
            SQLCommandInsert.append(tableMap.get(ChosenTable)).append(" ");
            resultSet = statement.executeQuery(String.valueOf(SQLCommandReadColumnLabel));
            int Numbers_of_Columns = 1;
            Map<Integer, String> columnLabel = new HashMap<>();
            while (resultSet.next()) {
                columnLabel.put(Numbers_of_Columns, resultSet.getString("COLUMN_NAME"));
                Numbers_of_Columns++;
            }
            SQLCommandInsert.append("(");
            for (int i = 1; i < Numbers_of_Columns - 1; i++) {
                SQLCommandInsert.append(columnLabel.get(i)).append(",");
            }
            SQLCommandInsert.append(columnLabel.get(Numbers_of_Columns - 1)).append(") ").append("VALUES (");
            for (int i = 1; i < Numbers_of_Columns; i++) {
                System.out.println("Enter " + columnLabel.get(i) + ":");
                String Answer = scan.next();
                if (i == Numbers_of_Columns - 1) {
                    SQLCommandInsert.append("'").append(Answer).append("');");
                } else {
                    SQLCommandInsert.append("'").append(Answer).append("',");
                }
            }
            System.out.println("SQLCommand --> " + SQLCommandInsert);
            statement.execute(String.valueOf(SQLCommandInsert));
        } catch (SQLException | NumberFormatException e) {
            System.out.println("Error:" + e);
        }
    }
}
