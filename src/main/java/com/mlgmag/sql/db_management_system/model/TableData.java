package com.mlgmag.sql.db_management_system.model;

import java.util.List;

public class TableData {

    private final List<String> columns;
    private final List<List<String>> rows;

    public List<String> getColumns() {
        return columns;
    }

    public List<List<String>> getRows() {
        return rows;
    }

    public TableData(List<String> columns, List<List<String>> rows) {
        this.columns = columns;
        this.rows = rows;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('\n');

        for (List<String> row : rows) {
            for (int colIdx = 0; colIdx < columns.size(); colIdx++) {
                String column = columns.get(colIdx);
                String value = row.get(colIdx);
                stringBuilder.append(column).append(": ").append(value).append('\n');
            }
            stringBuilder.append('\n');
        }

        return stringBuilder.toString();
    }
}
