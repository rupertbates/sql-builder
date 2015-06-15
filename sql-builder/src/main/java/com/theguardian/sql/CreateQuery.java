package com.theguardian.sql;

import java.util.ArrayList;
import java.util.List;

import static com.theguardian.sql.QueryHelper.trimTwo;

public class CreateQuery implements Builder {
    private String tableName;
    private List<CreateField> fields = new ArrayList<CreateField>();

    public CreateQuery(String tableName) {
        this.tableName = tableName;
    }

    public CreateField field(String name){
        CreateField field = new CreateField(this, name);
        fields.add(field);
        return field;
    }

    @Override
    public String build() {
        StringBuilder stringBuilder = new StringBuilder("CREATE TABLE ");
        stringBuilder.append(tableName);
        stringBuilder.append(" (");

        for (CreateField field : fields) {
            stringBuilder.append(field.buildField());
            stringBuilder.append(", ");
        }
        return trimTwo(stringBuilder)
                .append(")")
                .toString();

    }
}
