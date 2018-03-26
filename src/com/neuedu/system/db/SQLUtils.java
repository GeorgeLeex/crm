package com.neuedu.system.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SQLUtils {

    private static String sql;
    private static List<Object> values;

    private SQLUtils(){}

    public static String getSql() {
        return sql;
    }

    public static List<Object> getValues() {
        return values;
    }

    public static Object[] getValuesForArray () {
        return values.toArray();
    }

    public static void createInsertSQL(String tableName, Map<String, Object> dto) {
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuilder column1 = new StringBuilder("INSERT INTO ").append(tableName).append(" (");
        StringBuilder values1 = new StringBuilder(" VALUES (");
        List<Object> valueList = new ArrayList<>();
        for (Map.Entry<String, Object> entry : dto.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            column1.append(key).append(",");
            values1.append("?,");
            valueList.add(value);
        }
        String column = column1.substring(0, column1.length() - 1) + ")";
        String columnValue = values1.substring(0, values1.length() - 1) + ")";
        sql =  column + columnValue;
        values =  valueList;
    }

    public static void createSelectSQL(String tableName) {
        createSelectSQL0(tableName, null, null);
    }
    public static void createSelectSQL(String tableName, String[] columns) {
        createSelectSQL0(tableName, columns, null);
    }
    public static void createSelectSQL(String tableName, String[] columns, Map<String, Object> dto) {
        createSelectSQL0(tableName, columns, dto);
    }
    /**
     *
     * @param tableName 表名
     * @param columns 查询的列名
     * @param dto 条件的列/值
     * @return
     */
    private static void createSelectSQL0(String tableName, String[] columns, Map<String, Object> dto) {
        Map<String, Object> map = new HashMap<>();
        //List<Object> values = new ArrayList<>();
        StringBuilder sb = new StringBuilder("SELECT ");
        if (columns == null) {
            sb.append("* FROM ").append(tableName);
        } else if (dto == null) {
            for (int i = 0; i < columns.length; i++) {
                String column = columns[i];
                sb.append(column).append(",");
            }
            sb = new StringBuilder(sb.substring(0, sb.length() - 1));
            sb.append(" FROM ").append(tableName);
        } else {
            for (int i = 0; i < columns.length; i++) {
                String column = columns[i];
                sb.append(column).append(",");
            }
            sb = new StringBuilder(sb.substring(0, sb.length() - 1));
            sb.append(" FROM ").append(tableName).append(" WHERE ");
            for (Map.Entry<String, Object> entry : dto.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                sb.append(key).append(" = ? AND ");
                values.add(value);
            }
            sb = new StringBuilder(sb.substring(0, sb.length() - 4));
        }
        sql = sb.toString();
    }
}
