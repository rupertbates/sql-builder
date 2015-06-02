package com.theguardian.sql;
import static com.theguardian.sql.QueryConstants.*;
import static com.theguardian.sql.QueryHelper.getListOfValues;
import static com.theguardian.sql.QueryHelper.getSingleValue;
import static com.theguardian.sql.QueryHelper.trimTwo;


public class WhereParameter implements Builder {
    private final String keyword;
    private final WhereQuery query;
    private final String name;
    private Object value;
    private Operator operator;


    public WhereParameter(WhereQuery query, String keyword, String name) {
        this.query = query;
        this.keyword = keyword;
        this.name = name;
    }

    public WhereQuery equalTo(Object value) {
        return saveValue(value, Operator.EQUALS);
    }

    public WhereQuery greaterThan(Object value) {
        return saveValue(value, Operator.GREATER_THAN);
    }

    public WhereQuery greaterThanOrEqualTo(Object value) {
        return saveValue(value, Operator.GREATER_THAN_OR_EQUAL_TO);
    }

    public WhereQuery lessThan(Object value) {
        return saveValue(value, Operator.LESS_THAN);
    }

    public WhereQuery lessThanOrEqualTo(Object value) {
        return saveValue(value, Operator.LESS_THAN_OR_EQUAL_TO);
    }

    public WhereQuery like(String value) {
        return saveValue(value, Operator.LIKE);
    }

    public WhereQuery notEqualTo(Object value) {
        return saveValue(value, Operator.NOT_EQUAL_TO);
    }

    public WhereQuery in(Object... values) {
        return saveValue(values, Operator.IN);
    }

    public WhereQuery notIn(Object... values) {
        return saveValue(values, Operator.NOT_IN);
    }

    private WhereQuery saveValue(Object value, Operator operator) {
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

