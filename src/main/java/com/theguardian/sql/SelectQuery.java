package com.theguardian.sql;

import java.util.ArrayList;
import java.util.List;

public class SelectQuery implements Builder {

    private String table;
    private String[] fields;
    private List<WhereParameter> whereParameters = new ArrayList<>();

    public SelectQuery(String[] fields) {
        this.fields = fields;
    }

    public SelectQuery from(String table) {
        this.table = table;
        return this;
    }

    public WhereParameter where(String name) {
        return createWhereParameter("WHERE", name);
    }

    public WhereParameter and(String name) {
        return createWhereParameter("AND", name);
    }

    public WhereParameter or(String name) {
        return createWhereParameter("OR", name);
    }

    private WhereParameter createWhereParameter(String keyword, String name) {
        WhereParameter where = new WhereParameter(this, keyword, name);
        whereParameters.add(where);
        return where;
    }

    @Override
    public String build() {
        StringBuilder stringBuilder = new StringBuilder("SELECT ");
        for (String field : fields) {
            stringBuilder.append(field).append(",");
        }
        return stringBuilder.deleteCharAt(stringBuilder.length() - 1)
                .append(" FROM ")
                .append(table)
                .append(getWhereClause())
                .toString();
    }

    private String getWhereClause() {
        StringBuilder stringBuilder = new StringBuilder(" ");
        for (WhereParameter whereParameter : whereParameters) {
            stringBuilder.append(whereParameter.build()).append(" ");
        }
        return stringBuilder
                .deleteCharAt(stringBuilder.length() - 1)
                .toString();
    }

}
