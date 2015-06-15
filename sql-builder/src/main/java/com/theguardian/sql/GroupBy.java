package com.theguardian.sql;

import static com.theguardian.sql.QueryConstants.GROUP_BY;
import static com.theguardian.sql.QueryHelper.getList;

public class GroupBy implements StatementBuilder {
    private SelectStatement selectStatement;
    private String[] fields;

    public GroupBy(SelectStatement selectStatement, String[] fields) {
        this.selectStatement = selectStatement;
        this.fields = fields;
    }

    public SelectStatement limit(int limitValue){
        return selectStatement.limit(limitValue);
    }

    @Override
    public String build() {
        return selectStatement.build();
    }

    @Override
    public String toString() {
        return GROUP_BY + getList(fields);
    }

    public OrderBy orderBy(String... fields) {
        return selectStatement.orderBy(fields);
    }
}
