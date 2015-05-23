package com.theguardian.sql;

import java.util.Map;
import java.util.TreeMap;

import static com.theguardian.sql.QueryHelper.trimTwo;

public class UpdateQuery extends WhereQuery {
    private String tableName;
    Map<String, Object> fields = new TreeMap<>();

    public UpdateQuery(String tableName) {
        this.tableName = tableName;
    }

    public UpdateQuery set(String field, Object value) {
        fields.put(field, value);
        return this;
    }

    @Override
    public String build() {
        StringBuilder stringBuilder = new StringBuilder("UPDATE ");
        stringBuilder.append(tableName).append(" SET ");

        for (String field : fields.keySet()) {
            stringBuilder
                    .append(field)
                    .append("=")
                    .append(getValue(field))
                    .append(", ");
        }
        return trimTwo(stringBuilder)
                .append(getWhereClause())
                .toString();
    }

    private String getValue(String key) {
        Object value = fields.get(key);
        return value instanceof String ? "'" + value + "'" : String.valueOf(value);
    }
}
