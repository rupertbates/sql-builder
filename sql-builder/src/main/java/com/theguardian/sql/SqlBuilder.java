package com.theguardian.sql;

public class SqlBuilder {
    public static SelectStatement select() {
        return select("*");
    }

    public static SelectStatement select(String... fields) {
        return new SelectStatement(fields);
    }

    public static UpdateStatement update(String tableName) {
        return new UpdateStatement(tableName);
    }

    public static InsertStatement insert() {
        return new InsertStatement();
    }

    public static DeleteStatement deleteFrom(String tableName) {
        return new DeleteStatement(tableName);
    }

    public static CreateStatement createTable(String tableName) {
        return new CreateStatement(tableName);
    }
}
