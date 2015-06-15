package com.theguardian.sql;

import static com.theguardian.sql.QueryConstants.*;
import static com.theguardian.sql.QueryConstants.SPACE;
import static com.theguardian.sql.QueryHelper.getList;

public class OrderBy implements StatementBuilder {
    private SelectStatement selectStatement;
    private String[] fields;
    private String order = ASC;

    public OrderBy(SelectStatement selectStatement, String[] fields) {
        this.selectStatement = selectStatement;
        this.fields = fields;
    }

    public SelectStatement asc(){
        this.order = ASC;
        return selectStatement;
    }

    public SelectStatement desc(){
        this.order = DESC;
        return selectStatement;
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
        return ORDER_BY + getList(fields) + SPACE + order;
    }
}
