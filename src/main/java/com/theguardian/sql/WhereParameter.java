package com.theguardian.sql;

public class WhereParameter implements Builder{
    protected String keyword = "WHERE";
    private SelectQuery query;
    private String name;
    private Object value;
    private Operator operator;


    public WhereParameter(SelectQuery query, String name) {
        this.query = query;
        this.name = name;
    }

    public SelectQuery equalTo(Object value){
        this.value = value;
        this.operator = Operator.EQUALS;
        return query;
    }

    public SelectQuery greaterThan(Object value){
        this.value = value;
        this.operator = Operator.GREATER_THAN;
        return query;
    }

    public SelectQuery greaterThanOrEqualTo(Object value){
        this.value = value;
        this.operator = Operator.GREATER_THAN_OR_EQUAL_TO;
        return query;
    }

    public SelectQuery lessThan(Object value){
        this.value = value;
        this.operator = Operator.LESS_THAN;
        return query;
    }

    public SelectQuery lessThanOrEqualTo(Object value){
        this.value = value;
        this.operator = Operator.LESS_THAN_OR_EQUAL_TO;
        return query;
    }

    @Override
    public String build() {
        return keyword + " " + name + " " + operator + " " + getValue();
    }

    private String getValue() {
        return value instanceof String ? "'" + value + "'" : String.valueOf(value);
    }
}
