package com.theguardian.sql;

import java.security.cert.CertPathBuilder;

import static com.theguardian.sql.QueryConstants.GROUP_BY;
import static com.theguardian.sql.QueryHelper.getList;

public class GroupBy implements Builder{
    private SelectQuery selectQuery;
    private String[] fields;

    public GroupBy(SelectQuery selectQuery, String[] fields) {
        this.selectQuery = selectQuery;
        this.fields = fields;
    }

    public SelectQuery limit(int limitValue){
        return selectQuery.limit(limitValue);
    }

    @Override
    public String build() {
        return selectQuery.build();
    }

    @Override
    public String toString() {
        return GROUP_BY + getList(fields);
    }

    public OrderBy orderBy(String... fields) {
        return selectQuery.orderBy(fields);
    }
}
