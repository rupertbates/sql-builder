package com.theguardian.sql;

import static com.theguardian.sql.QueryConstants.CLOSE_BRACKET;
import static com.theguardian.sql.QueryConstants.COMMA;
import static com.theguardian.sql.QueryConstants.EMPTY_STRING;
import static com.theguardian.sql.QueryConstants.FROM;
import static com.theguardian.sql.QueryConstants.LIMIT;
import static com.theguardian.sql.QueryConstants.OPEN_BRACKET;
import static com.theguardian.sql.QueryConstants.SELECT;
import static com.theguardian.sql.QueryConstants.SPACE;
import static com.theguardian.sql.QueryHelper.getList;

public class SelectStatement extends WhereClause {
    public static final int NO_LIMIT = -1;
    private String table;
    private String[] fields;
    private String functionClause;
    private OrderBy orderBy;
    private int limitValue = NO_LIMIT;
    private GroupBy groupBy;

    public SelectStatement(String[] fields) {
        this.fields = fields;
    }

    public SelectStatement count(String field) {
        return count(field, null);
    }

    public SelectStatement count(String field, String alias) {
        this.functionClause = getFunctionClause(QueryConstants.COUNT, field, alias);
        return this;
    }

    public SelectStatement max(String field) {
        return max(field, null);
    }

    public SelectStatement max(String field, String alias) {
        this.functionClause = getFunctionClause(QueryConstants.MAX, field, alias);
        return this;
    }

    public SelectStatement min(String field) {
        return min(field, null);
    }

    public SelectStatement min(String field, String alias) {
        this.functionClause = getFunctionClause(QueryConstants.MIN, field, alias);
        return this;
    }

    public SelectStatement avg(String field) {
        return avg(field, null);
    }

    public SelectStatement avg(String field, String alias) {
        this.functionClause = getFunctionClause(QueryConstants.AVG, field, alias);
        return this;
    }

    private String getFunctionClause(String function, String field, String alias) {
        return function + OPEN_BRACKET + field + CLOSE_BRACKET + getAlias(alias);
    }

    private String getAlias(String alias) {
        return alias != null ? QueryConstants.AS + alias : EMPTY_STRING;
    }

    public SelectStatement from(String table) {
        this.table = table;
        return this;
    }

    @Override
    public OrderBy orderBy(String... fields) {
        this.orderBy = new OrderBy(this, fields);
        return this.orderBy;
    }

    @Override
    public GroupBy groupBy(String... fields) {
        this.groupBy = new GroupBy(this, fields);
        return this.groupBy;
    }

    public SelectStatement limit(int limitValue) {
        this.limitValue = limitValue;
        return this;
    }

    @Override
    public String build() {
        StringBuilder stringBuilder = new StringBuilder(SELECT + SPACE);

        stringBuilder.append(getList(fields));

        if (functionClause != null && !functionClause.equals(EMPTY_STRING)) {
            stringBuilder.append(fields.length > 0 ? COMMA : EMPTY_STRING);
            stringBuilder.append(functionClause);
        }

        return stringBuilder
                .append(SPACE)
                .append(FROM)
                .append(SPACE)
                .append(table)
                .append(getWhereClause())
                .append(getGroupBy())
                .append(getOrderBy())
                .append(getLimit())
                .toString();
    }

    private String getGroupBy() {
        return groupBy != null ? SPACE + groupBy.toString() : EMPTY_STRING;
    }

    private String getLimit() {
        return limitValue == NO_LIMIT ? EMPTY_STRING : SPACE + LIMIT + SPACE + limitValue;
    }

    private String getOrderBy() {
        return orderBy != null ? SPACE + orderBy.toString() : EMPTY_STRING;
    }

}
