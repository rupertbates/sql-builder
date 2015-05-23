package com.theguardian.sql;

import static com.theguardian.sql.QueryHelper.trimTwo;

public class SelectQuery extends WhereQuery {
    private String table;
    private String[] fields;

    public SelectQuery(String[] fields) {
        this.fields = fields;
    }

    public SelectQuery from(String table) {
        this.table = table;
        return this;
    }

    @Override
    public String build() {
        StringBuilder stringBuilder = new StringBuilder("SELECT ");
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
