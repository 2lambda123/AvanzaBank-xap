package com.gigaspaces.jdbc;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public class DumpUtils {
    public static List<Row> dump(ResultSet rs) throws SQLException {
        List<Row> result = new ArrayList<>();
        ResultSetMetaData metaData = rs.getMetaData();
        String[] columnNames = getHeader(metaData);
        System.out.println(getHeaderString(metaData));
        while (rs.next()) {
            Object[] rowValues = getRow(rs, metaData);
            result.add(new Row(columnNames, rowValues));
        }

        int counter = 0;
        for (Row row : result) {
            System.out.println(rowToString(row.columnValues));
            if (++counter == 10)
                break;
        }
//        System.out.println(rowToString(result.stream().map(x -> x.columnValues).findAny().orElse(new Object[columnNames.length])));
//        System.out.println(result.stream().map(x->x.columnValues).findAny().map(JdbcTestUtils::rowToString).orElse("| Query returned no data! |"));

        return result;
    }

    public static String getHeaderString(ResultSetMetaData metaData) throws SQLException {
        StringJoiner sj = new StringJoiner(" | ", "| ", " |");
        Arrays.stream(getHeader(metaData)).forEach(sj::add);
        return sj.toString();
    }

    public static String[] getHeader(ResultSetMetaData metaData) throws SQLException {
        StringJoiner sj = new StringJoiner(" | ", "| ", " |");
        int columnCount = metaData.getColumnCount();
        String[] result = new String[columnCount];
        int Cs=1;
        for (int i = 1; i <= columnCount; i++) {
            String label = metaData.getColumnLabel(i);
            if (label.endsWith(")")) { //replace aggr column without alias to CX
                label = "C"+(Cs++);
            }
            result[i-1] = label;
        }
        return result;
    }

    public static String rowToString(Object[] rowValues) {
        StringJoiner sj = new StringJoiner(" | ", "| ", " |");
        for (Object rowValue : rowValues) {
            sj.add(String.valueOf(rowValue));
        }
        return sj.toString();
    }

    public static Object[] getRow(ResultSet rs, ResultSetMetaData metaData) throws SQLException {
        Object[] result = new Object[metaData.getColumnCount()];
        for (int i = 0; i < result.length; i++) {
            result[i] = rs.getObject(i + 1);
        }
        return result;
    }

    public static class Row {
        private final String[] columnNames;
        private final Object[] columnValues;

        public Row(String[] columnNames, Object[] columnValues) {
            this.columnNames = columnNames;
            this.columnValues = columnValues;
        }

        public Object getColumnValue(String columnName) {
            int pos = -1;
            for (int i = 0; i < columnNames.length; i++) {
                String column = columnNames[i];
                if (column.equalsIgnoreCase(columnName)) {
                    pos = i;
                    break;
                }
            }
            return pos == -1 ? null : columnValues[pos];
        }

        @Override
        public boolean equals(Object o) {
            Row otherRow = (Row) o;
            for (String column : columnNames) {
                Object value = getColumnValue(column);
                Object otherValue = otherRow.getColumnValue(column);
                if (!value.equals(otherValue))
                    return false;
            }
            return true;
        }
    }

}
