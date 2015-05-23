package com.theguardian.sql;

public class SqlBuilder {
    public SelectQuery select() {
        return select("*");
    }

    public SelectQuery select(String... fields) {
        return new SelectQuery(fields);
    }

    public UpdateQuery update(String tableName){
        return new UpdateQuery(tableName);
    }

    public CreateQuery create(String tableName){
        return new CreateQuery(tableName);
    }
}
