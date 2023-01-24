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
public class Update {

    public Update() {
        System.out.println("Which table you want update:");
        StringBuilder ShowTables = new StringBuilder("SHOW TABLES FROM ");
        StringBuilder SQLCommandUpdate = new StringBuilder("UPDATE ");
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
            SQLCommandUpdate.append(tableMap.get(b)).append(" ").append("SET ");
            System.out.println("Chose column:");
            System.out.println("Columns in table:");
            int Numbers_of_Columns = 0;
            StringBuilder SQLCommandReadColumnLabel = new StringBuilder("SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = ");
            SQLCommandReadColumnLabel.append("'").append(tableMap.get(b)).append("';");
            resultSet = statement.executeQuery(String.valueOf(SQLCommandReadColumnLabel));
            Map<Integer, String> columnLabel = new HashMap<>();
            while (resultSet.next()) {
                columnLabel.put(Numbers_of_Columns, resultSet.getString("COLUMN_NAME"));
                Numbers_of_Columns++;
            }
            for (int i = 1; i < Numbers_of_Columns; i++) {
                System.out.println(i + "." + columnLabel.get(i) + ";");
            }
            b = scan.nextInt();
            SQLCommandUpdate.append(columnLabel.get(b)).append(" = '");
            System.out.println("Enter Updated " + columnLabel.get(b) + ":");
            String NewInfo = scan.next();
            SQLCommandUpdate.append(NewInfo).append("' WHERE ").append(columnLabel.get(0)).append(" = ");
            System.out.println("Enter id which will be updated:");
            b = scan.nextInt();
            SQLCommandUpdate.append(b).append(";");
            System.out.println("SQLCommand -->" + SQLCommandUpdate);
            statement.execute(String.valueOf(SQLCommandUpdate));
        } catch (SQLException | NumberFormatException e) {
            System.out.println("Error:" + e);
            e.printStackTrace();
        }
    }
}
