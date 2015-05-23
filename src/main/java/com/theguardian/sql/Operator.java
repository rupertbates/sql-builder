package com.theguardian.sql;

public enum Operator {
    EQUALS("="),
    GREATER_THAN(">"),
    LESS_THAN("<"),
    GREATER_THAN_OR_EQUAL_TO(">="),
    LESS_THAN_OR_EQUAL_TO("<="),
    LIKE,
    IN;
    private String sqlString;

    Operator() {
        this.sqlString = this.name();
    }

    Operator(String sqlString) {
        this.sqlString = sqlString;
    }

    @Override
    public String toString() {
        return sqlString;
    }
}
