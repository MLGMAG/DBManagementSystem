package com.mlgmag.sql.db_management_system.service;

import com.mlgmag.sql.db_management_system.dao.DatabaseDAO;
import com.mlgmag.sql.db_management_system.model.TableData;
import com.mlgmag.sql.db_management_system.util.ListUtils;

import java.util.List;
import java.util.Scanner;

public class OperationService {

    private final DatabaseDAO databaseDAO;

    private static OperationService instance;

    private OperationService() {
        databaseDAO = DatabaseDAO.getInstance();
    }

    public void read() {
        List<String> allTables = databaseDAO.getAllTablesFromDB();

        System.out.println("\nTables:");
        ListUtils.addNumberPrefixToListItems(allTables).forEach(System.out::println);

        System.out.print("\nChoose table which will be read: ");
        Scanner scan = new Scanner(System.in);
        int tableIndex = scan.nextInt();

        String table = allTables.get(tableIndex);
        TableData tableData = databaseDAO.getTableData(table);
        System.out.print(tableData);
    }

    public static void init() {
        instance = new OperationService();
    }

    public static OperationService getInstance() {
        return instance;
    }

}
