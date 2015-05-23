package com.theguardian.sql;


public class WhereParameter implements Builder {
    private final String keyword;
    private final SelectQuery query;
    private final String name;
    private Object value;
    private Operator operator;


    public WhereParameter(SelectQuery query, String keyword, String name) {
        this.query = query;
        this.keyword = keyword;
        this.name = name;
    }

    public SelectQuery equalTo(Object value) {
        return saveValue(value, Operator.EQUALS);
    }

    public SelectQuery greaterThan(Object value) {
        return saveValue(value, Operator.GREATER_THAN);
    }

    public SelectQuery greaterThanOrEqualTo(Object value) {
        return saveValue(value, Operator.GREATER_THAN_OR_EQUAL_TO);
    }

    public SelectQuery lessThan(Object value) {
        return saveValue(value, Operator.LESS_THAN);
    }

    public SelectQuery lessThanOrEqualTo(Object value) {
        return saveValue(value, Operator.LESS_THAN_OR_EQUAL_TO);
    }

    public SelectQuery like(String value) {
        return saveValue(value, Operator.LIKE);
    }

    public SelectQuery notEqualTo(Object value) {
        return saveValue(value, Operator.NOT_EQUAL_TO);
    }

    public SelectQuery in(Object... values) {
        return saveValue(values, Operator.IN);
    }

    public SelectQuery notIn(Object... values) {
        return saveValue(values, Operator.NOT_IN);
    }

    private SelectQuery saveValue(Object value, Operator operator) {
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
