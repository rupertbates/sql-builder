package com.theguardian.sql;

public class CreateField implements Builder {
    private String name;
    private String type;
    private boolean primaryKey = false;
    private boolean notNull;

    public CreateField(String name) {
        this.name = name;
    }

    public CreateField integer(){
        type = "INT";
        return this;
    }

    public CreateField text(){
        type = "TEXT";
        return this;
    }

    public CreateField real(){
        type = "REAL";
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

    @Override
    public String build() {
        return name + " " + type + (primaryKey ? " PRIMARY KEY" : "") + (notNull ? " NOT NULL" : "");
    }
}
