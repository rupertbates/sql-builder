package com.theguardian.sql;

import static com.theguardian.sql.QueryHelper.getList;
import static com.theguardian.sql.QueryConstants.*;

public class SelectQuery extends WhereQuery {
    public static final int NO_LIMIT = -1;
    private String table;
    private String[] fields;
    private String functionClause;
    private OrderBy orderBy;
    private int limitValue = NO_LIMIT;

    public SelectQuery(String[] fields) {
        this.fields = fields;
    }

    public SelectQuery count() {
        return count(QueryConstants.STAR);
    }

    public SelectQuery count(String field) {
        this.functionClause = getFunctionClause(QueryConstants.COUNT, field);
        return this;
    }

    public SelectQuery max(String field) {
        this.functionClause = getFunctionClause(QueryConstants.MAX, field);
        return this;
    }

    public SelectQuery min(String field) {
        this.functionClause = getFunctionClause(QueryConstants.MIN, field);
        return this;
    }

    public SelectQuery avg(String field) {
        this.functionClause = getFunctionClause(QueryConstants.AVG, field);
        return this;
    }

    private String getFunctionClause(String function, String field) {
        return function + OPEN_BRACKET + field + CLOSE_BRACKET;
    }

    public SelectQuery from(String table) {
        this.table = table;
        return this;
    }

    @Override
    public OrderBy orderBy(String... fields){
        this.orderBy = new OrderBy(this, fields);
        return this.orderBy;
    }

    public SelectQuery limit(int limitValue){
        this.limitValue = limitValue;
        return this;
    }

    @Override
    public String build() {
        StringBuilder stringBuilder = new StringBuilder(SELECT + SPACE);

        if (functionClause != null && !functionClause.equals(EMPTY_STRING))
            stringBuilder.append(functionClause);
        else
            stringBuilder.append(getList(fields));

        return stringBuilder
                .append(SPACE)
                .append(FROM)
                .append(SPACE)
                .append(table)
                .append(getWhereClause())
                .append(getOrderBy())
                .append(getLimit())
                .toString();
    }

    private String getLimit() {
        return limitValue == NO_LIMIT ? EMPTY_STRING : SPACE + LIMIT + SPACE + limitValue;
    }

    private String getOrderBy() {
        return orderBy != null ? SPACE + orderBy.toString() : EMPTY_STRING;
    }

}
