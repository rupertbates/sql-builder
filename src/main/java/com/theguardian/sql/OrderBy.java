package com.theguardian.sql;

import static com.theguardian.sql.QueryConstants.*;
import static com.theguardian.sql.QueryConstants.SPACE;
import static com.theguardian.sql.QueryHelper.getCommaDelimitedList;

public class OrderBy implements Builder{
    private SelectQuery selectQuery;
    private String[] fields;
    private String order = ASC;

    public OrderBy(SelectQuery selectQuery, String[] fields) {
        this.selectQuery = selectQuery;
        this.fields = fields;
    }

    public SelectQuery asc(){
        this.order = ASC;
        return selectQuery;
    }

    public SelectQuery desc(){
        this.order = DESC;
        return selectQuery;
    }

    @Override
    public String build() {
        return selectQuery.build();
    }

    @Override
    public String toString() {
        return ORDER_BY + getCommaDelimitedList(fields) + SPACE + order;
    }
}
