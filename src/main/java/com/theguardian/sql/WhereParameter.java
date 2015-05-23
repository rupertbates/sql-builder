package com.theguardian.sql;


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
        return keyword + " " + name + " " + operator + " " + getValue();
    }

    private String getValue() {
        if (value instanceof Object[]) {
            return getListOfValues();
        }
        return getSingleValue(value);
    }

    private String getListOfValues() {
        StringBuilder stringBuilder = new StringBuilder("(");

        for (Object o : ((Object[]) value)) {
            stringBuilder.append(getSingleValue(o)).append(",");
        }
        return stringBuilder.deleteCharAt(stringBuilder.length() - 1)
                .append(")")
                .toString();
    }

    private String getSingleValue(Object value) {
        return value instanceof String ? "'" + value + "'" : String.valueOf(value);
    }
}
