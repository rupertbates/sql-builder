package com.theguardian.sql;

import static com.theguardian.sql.QueryHelper.trimTwo;
import static com.theguardian.sql.QueryConstants.*;

public class SelectQuery extends WhereQuery {
    private String table;
    private String[] fields;
    private String functionClause;

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
    public String build() {
        StringBuilder stringBuilder = new StringBuilder(SELECT + SPACE);

        if (functionClause != null && !functionClause.equals(EMPTY_STRING))
            stringBuilder.append(functionClause).append("  ");
        else
            for (String field : fields) {
                stringBuilder.append(field).append(COMMA);
            }

        return trimTwo(stringBuilder)
                .append(SPACE)
                .append(FROM)
                .append(SPACE)
                .append(table)
                .append(getWhereClause())
                .toString();
    }

}
