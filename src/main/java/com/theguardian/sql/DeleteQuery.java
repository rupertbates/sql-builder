package com.theguardian.sql;

public class DeleteQuery extends WhereQuery {
    private String tableName;

    public DeleteQuery(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public String build() {
        return "DELETE FROM " + tableName + getWhereClause();
    }
}
