package com.theguardian.sql;

import org.junit.Test;

import static com.theguardian.sql.SqlBuilder.createTable;
import static com.theguardian.sql.Tables.USER_TABLE;
import static org.junit.Assert.assertEquals;

public class CreateTests {
    @Test
    public void testCreate() {
        String expected = "CREATE TABLE user (id INT PRIMARY KEY, firstname TEXT NOT NULL, surname TEXT NOT NULL, age REAL)";
        String actual = createTable(USER_TABLE)
                .field(Tables.Fields.ID).integer().primaryKey()
                .field(Tables.Fields.FIRSTNAME).text().notNull()
                .field(Tables.Fields.SURNAME).text().notNull()
                .field(Tables.Fields.AGE).real()
                .build();

        assertEquals(expected, actual);
    }
}
