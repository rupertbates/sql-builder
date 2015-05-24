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

    public DeleteQuery deleteFrom(String tableName){
        return new DeleteQuery(tableName);
    }

    public CreateQuery createTable(String tableName){
        return new CreateQuery(tableName);
    }
}
