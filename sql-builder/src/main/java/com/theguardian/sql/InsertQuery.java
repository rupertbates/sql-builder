package com.theguardian.sql;

import static com.theguardian.sql.QueryHelper.getListOfValues;
import static com.theguardian.sql.QueryHelper.getListWithBrackets;
import static com.theguardian.sql.QueryConstants.SPACE;

public class InsertQuery implements Builder {
    private String tableName;
    private String[] columns;
    private Object[] values;

    public InsertQuery into(String tableName, String... columns) {
        this.tableName = tableName;
        this.columns = columns;
        return this;
    }

    public InsertQuery into(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public InsertQuery values(Object... values) {
        this.values = values;
        return this;
    }

    @Override
    public String build() {

        StringBuilder stringBuilder = new StringBuilder()
                .append("INSERT INTO ")
                .append(tableName);

        if (columns != null) {
            stringBuilder.append(SPACE).append(getListWithBrackets(columns));
        }

        return stringBuilder
                .append(" VALUES ")
                .append(getListOfValues(values))
                .toString();
    }
}
