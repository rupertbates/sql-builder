package com.theguardian.sql;

import static com.theguardian.sql.QueryConstants.COMMA;

class QueryHelper {

    static StringBuilder trimTwo(StringBuilder stringBuilder){
        return stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
    }

    static String getSingleValue(Object value){
        if(value instanceof Builder) //If this is a subquery then evaluate it
            return QueryConstants.OPEN_BRACKET + ((Builder) value).build() + QueryConstants.CLOSE_BRACKET;

        return value instanceof String ? "'" + value + "'" : String.valueOf(value);
    }

    static String getCommaDelimitedList(String[] values){
        StringBuilder stringBuilder = new StringBuilder();
        for (String value : values) {
            stringBuilder.append(value).append(COMMA);
        }
        return trimTwo(stringBuilder).toString();
    }
}
