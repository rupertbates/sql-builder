package com.theguardian.sql;

public class SqlBuilder {
    public SelectQuery select(){
        return select("*");
    }

    public SelectQuery select(String... fields){
        return new SelectQuery(fields);
    }
}
