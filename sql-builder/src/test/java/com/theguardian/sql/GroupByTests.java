package com.theguardian.sql;

import org.junit.Test;

import static com.theguardian.sql.SqlBuilder.select;
import static com.theguardian.sql.Tables.USER_TABLE;
import static org.junit.Assert.assertEquals;

public class GroupByTests {
    @Test
    public void testSimpleGroupBy(){
        String expected = "SELECT surname FROM user GROUP BY surname";
        String actual = select(Tables.Fields.SURNAME)
                .from(USER_TABLE)
                .groupBy(Tables.Fields.SURNAME)
                .build();

        assertEquals(expected, actual);
    }

    @Test
    public void testGroupByWithOrder(){
        String expected = "SELECT id, surname FROM user GROUP BY surname ORDER BY surname ASC";
        String actual = select(Tables.Fields.ID, Tables.Fields.SURNAME)
                .from(USER_TABLE)
                .groupBy(Tables.Fields.SURNAME)
                .orderBy(Tables.Fields.SURNAME)
                .build();

        assertEquals(expected, actual);
    }

    @Test
    public void testGroupByWithWhere(){
        String expected = "SELECT id, surname FROM user WHERE age >= 18 GROUP BY surname";
        String actual = select(Tables.Fields.ID, Tables.Fields.SURNAME)
                .from(USER_TABLE)
                .where(Tables.Fields.AGE).greaterThanOrEqualTo(18)
                .groupBy(Tables.Fields.SURNAME)
                .build();

        assertEquals(expected, actual);
    }

    @Test
    public void testGroupByWithCount(){
        String expected = "SELECT surname, COUNT(surname) AS count FROM user GROUP BY surname";
        String actual = select(Tables.Fields.SURNAME)
                .count(Tables.Fields.SURNAME, "count")
                .from(USER_TABLE)
                .groupBy(Tables.Fields.SURNAME)
                .build();

        assertEquals(expected, actual);
    }
}
