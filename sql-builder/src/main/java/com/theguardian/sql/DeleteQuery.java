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

    @Override
    public OrderBy orderBy(String... fields) {
        throw new IllegalStateException("Delete queries don't support order by");
    }

    @Override
    public GroupBy groupBy(String... fields) {
        throw new IllegalStateException("Delete queries don't support group by");
    }
}
