package com.theguardian.sql;

import static com.theguardian.sql.QueryHelper.trimTwo;

public class SelectQuery extends WhereQuery {
    public static final String STAR = "*";
    private String table;
    private String[] fields;
    private String functionClause;

    public SelectQuery(String[] fields) {
        this.fields = fields;
    }

    public SelectQuery count() {
        return count(STAR);
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
        return function + "(" + field + ")";
    }

    public SelectQuery from(String table) {
        this.table = table;
        return this;
    }

    @Override
    public String build() {
        StringBuilder stringBuilder = new StringBuilder("SELECT ");

        if (functionClause != null && !functionClause.equals(""))
            stringBuilder.append(functionClause).append("  ");
        else
            for (String field : fields) {
                stringBuilder.append(field).append(", ");
            }

        return trimTwo(stringBuilder)
                .append(" FROM ")
                .append(table)
                .append(getWhereClause())
                .toString();
    }

}
