package com.theguardian.sql;

import static com.theguardian.sql.QueryConstants.CLOSE_BRACKET;
import static com.theguardian.sql.QueryConstants.COMMA;
import static com.theguardian.sql.QueryConstants.EMPTY_STRING;
import static com.theguardian.sql.QueryConstants.OPEN_BRACKET;

class QueryHelper {

    static StringBuilder trimTwo(StringBuilder stringBuilder){
        return stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
    }

    static String getSingleValue(Object value){
        if(value instanceof StatementBuilder) //If this is a subquery then evaluate it
            return QueryConstants.OPEN_BRACKET + ((StatementBuilder) value).build() + QueryConstants.CLOSE_BRACKET;

        return value instanceof String ? "'" + value + "'" : String.valueOf(value);
    }

    static String getList(String[] values){
        if(values == null || values.length == 0)
            return EMPTY_STRING;
        
        StringBuilder stringBuilder = new StringBuilder();
        for (String value : values) {
            stringBuilder.append(value).append(COMMA);
        }
        return trimTwo(stringBuilder).toString();
    }

    static String getListOfValues(Object[] values){
        StringBuilder stringBuilder = new StringBuilder(OPEN_BRACKET);

        for (Object o :  values) {
            stringBuilder.append(getSingleValue(o)).append(COMMA);
        }
        return trimTwo(stringBuilder)
                .append(CLOSE_BRACKET)
                .toString();
    }

    static String getListWithBrackets(String[] values){
        return OPEN_BRACKET + getList(values) + CLOSE_BRACKET;
    }
}
