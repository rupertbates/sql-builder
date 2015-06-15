package com.theguardian.sql;

import static com.theguardian.sql.QueryHelper.getListOfValues;
import static com.theguardian.sql.QueryHelper.getListWithBrackets;
import static com.theguardian.sql.QueryConstants.SPACE;

public class InsertStatement implements StatementBuilder {
    private String tableName;
    private String[] columns;
    private Object[] values;

    public InsertStatement into(String tableName, String... columns) {
        this.tableName = tableName;
        this.columns = columns;
        return this;
    }

    public InsertStatement into(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public InsertStatement values(Object... values) {
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
