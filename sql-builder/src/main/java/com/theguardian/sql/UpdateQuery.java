package com.theguardian.sql;

import java.util.Map;
import java.util.TreeMap;

import static com.theguardian.sql.QueryHelper.getSingleValue;
import static com.theguardian.sql.QueryHelper.trimTwo;
import static com.theguardian.sql.QueryConstants.*;

public class UpdateQuery extends WhereQuery {
    private String tableName;
    Map<String, Object> fields = new TreeMap<String, Object>();

    public UpdateQuery(String tableName) {
        this.tableName = tableName;
    }

    public UpdateQuery set(String field, Object value) {
        fields.put(field, value);
        return this;
    }

    @Override
    public String build() {
        StringBuilder stringBuilder = new StringBuilder(UPDATE);
        stringBuilder
                .append(SPACE)
                .append(tableName)
                .append(SPACE)
                .append(SET)
                .append(SPACE);

        for (String field : fields.keySet()) {
            stringBuilder
                    .append(field)
                    .append(QueryConstants.EQUALS)
                    .append(getValue(field))
                    .append(QueryConstants.COMMA);
        }
        return trimTwo(stringBuilder)
                .append(getWhereClause())
                .toString();
    }

    private String getValue(String key) {
        Object value = fields.get(key);
        return getSingleValue(value);
    }

    @Override
    public OrderBy orderBy(String... fields) {
        throw new IllegalStateException("Update queries don't support order by");
    }

    @Override
    public GroupBy groupBy(String... fields) {
        throw new IllegalStateException("Update queries don't support group by");
    }
}
