package com.theguardian.sql;

public class DeleteStatement extends WhereClause {
    private String tableName;

    public DeleteStatement(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public String build() {
        return "DELETE FROM " + tableName + getWhereClause();
    }

    @Override
    public OrderBy orderBy(String... fields) {
        throw new IllegalStateException("Delete queries don't support order by");
    }

    @Override
    public GroupBy groupBy(String... fields) {
        throw new IllegalStateException("Delete queries don't support group by");
    }
}
