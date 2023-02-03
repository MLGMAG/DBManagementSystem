package com.mlgmag.sql.db_management_system.operations;

import com.mlgmag.sql.db_management_system.dao.DatabaseDAO;
import com.mlgmag.sql.db_management_system.model.TableData;
import com.mlgmag.sql.db_management_system.util.ListUtils;

import java.util.List;
import java.util.Scanner;

public class Read {

    public Read() {
        List<String> allTables = DatabaseDAO.getInstance().getAllTablesFromDB();

        System.out.println("\nTables:");
        ListUtils.addNumberPrefixToListItems(allTables).forEach(System.out::println);

        System.out.print("\nChoose table which will be read: ");
        Scanner scan = new Scanner(System.in);
        int tableIndex = scan.nextInt();

        String table = allTables.get(tableIndex);
        TableData tableData = DatabaseDAO.getInstance().getTableData(table);
        System.out.print(tableData);
    }
}
