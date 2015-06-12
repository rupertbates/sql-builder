package com.theguardian.sql;

import static com.theguardian.sql.QueryConstants.*;

public class CreateField implements Builder {
    private CreateQuery createQuery;
    private String name;
    private String type;
    private boolean primaryKey = false;
    private boolean notNull;
    private boolean autoIncrement;

    public CreateField(CreateQuery createQuery, String name) {
        this.createQuery = createQuery;
        this.name = name;
    }

    public CreateField integer(){
        type = INT;
        return this;
    }

    public CreateField text(){
        type = TEXT;
        return this;
    }

    public CreateField real(){
        type = REAL;
        return this;
    }

    public CreateField primaryKey(){
        primaryKey = true;
        return this;
    }

    public CreateField notNull(){
        notNull = true;
        return this;
    }

    public CreateField autoIncrement(){
        autoIncrement = true;
        return this;
    }

    public CreateField field(String name){
        return createQuery.field(name);
    }

    @Override
    public String build() {
        return createQuery.build();
    }

    String buildField(){
        return name + SPACE + type + (primaryKey ? PRIMARY_KEY : EMPTY_STRING) + (autoIncrement ? AUTOINCREMENT : EMPTY_STRING) + (notNull ? NOT_NULL : EMPTY_STRING);
    }
}
