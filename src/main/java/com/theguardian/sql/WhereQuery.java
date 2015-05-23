package com.theguardian.sql;

import java.util.List;

public class WhereQuery implements Builder {
    private SelectQuery selectQuery;

    public WhereQuery(SelectQuery selectQuery) {
        this.selectQuery = selectQuery;
    }

    @Override
    public String build() {
        return selectQuery.build();
    }
}
