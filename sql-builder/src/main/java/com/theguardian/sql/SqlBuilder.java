package com.theguardian.sql;

public class SqlBuilder {
    public static SelectQuery select() {
        return select("*");
    }

    public static SelectQuery select(String... fields) {
        return new SelectQuery(fields);
    }

    public static UpdateQuery update(String tableName) {
        return new UpdateQuery(tableName);
    }

    public static InsertQuery insert() {
        return new InsertQuery();
    }

    public static DeleteQuery deleteFrom(String tableName) {
        return new DeleteQuery(tableName);
    }

    public static CreateQuery createTable(String tableName) {
        return new CreateQuery(tableName);
    }
}
