package com.theguardian.sql;
import static com.theguardian.sql.QueryConstants.*;
import static com.theguardian.sql.QueryHelper.getListOfValues;
import static com.theguardian.sql.QueryHelper.getSingleValue;


public class WhereParameter implements StatementBuilder {
    private final String keyword;
    private final WhereClause query;
    private final String name;
    private Object value;
    private Operator operator;


    public WhereParameter(WhereClause query, String keyword, String name) {
        this.query = query;
        this.keyword = keyword;
        this.name = name;
    }

    public WhereClause equalTo(Object value) {
        return saveValue(value, Operator.EQUALS);
    }

    public WhereClause greaterThan(Object value) {
        return saveValue(value, Operator.GREATER_THAN);
    }

    public WhereClause greaterThanOrEqualTo(Object value) {
        return saveValue(value, Operator.GREATER_THAN_OR_EQUAL_TO);
    }

    public WhereClause lessThan(Object value) {
        return saveValue(value, Operator.LESS_THAN);
    }

    public WhereClause lessThanOrEqualTo(Object value) {
        return saveValue(value, Operator.LESS_THAN_OR_EQUAL_TO);
    }

    public WhereClause like(String value) {
        return saveValue(value, Operator.LIKE);
    }

    public WhereClause notEqualTo(Object value) {
        return saveValue(value, Operator.NOT_EQUAL_TO);
    }

    public WhereClause in(Object... values) {
        return saveValue(values, Operator.IN);
    }

    public WhereClause notIn(Object... values) {
        return saveValue(values, Operator.NOT_IN);
    }

    private WhereClause saveValue(Object value, Operator operator) {
        this.value = value;
        this.operator = operator;
        return query;
    }



    @Override
    public String build() {
        return keyword + SPACE + name + SPACE + operator + SPACE + getValue();
    }

    private String getValue() {
        if (value instanceof Object[]) {
            return getListOfValues((Object[]) value);
        }
        return getSingleValue(value);
    }
}

