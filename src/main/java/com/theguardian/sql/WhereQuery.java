package com.theguardian.sql;

import java.util.ArrayList;
import java.util.List;

public abstract class WhereQuery implements Builder{

    protected List<WhereParameter> whereParameters = new ArrayList<>();

    public WhereParameter where(String name) {
        return createWhereParameter("WHERE", name);
    }

    public WhereParameter and(String name) {
        return createWhereParameter("AND", name);
    }

    public WhereParameter or(String name) {
        return createWhereParameter("OR", name);
    }

    private WhereParameter createWhereParameter(String keyword, String name) {
        WhereParameter where = new WhereParameter(this, keyword, name);
        whereParameters.add(where);
        return where;
    }

    protected String getWhereClause() {
        StringBuilder stringBuilder = new StringBuilder(" ");
        for (WhereParameter whereParameter : whereParameters) {
            stringBuilder.append(whereParameter.build()).append(" ");
        }
        return stringBuilder
                .deleteCharAt(stringBuilder.length() - 1)
                .toString();
    }
}
