package com.theguardian.sql;

import org.junit.Test;

import static com.theguardian.sql.TestData.USER_TABLE;
import static org.junit.Assert.assertEquals;

public class CreateTests {
    @Test
    public void testCreate(){
        String expected = "CREATE TABLE user (id INT PRIMARY KEY, firstname TEXT NOT NULL, surname TEXT NOT NULL, age REAL)";
        String actual = new SqlBuilder()
                .createTable(USER_TABLE)
                .field("id").integer().primaryKey()
                .field("firstname").text().notNull()
                .field("surname").text().notNull()
                .field("age").real()
                .build();

        assertEquals(expected, actual);
    }
}
