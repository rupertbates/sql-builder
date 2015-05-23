package com.theguardian.sql;

class QueryHelper {
    static StringBuilder trimTwo(StringBuilder stringBuilder){
        return stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
    }
}
