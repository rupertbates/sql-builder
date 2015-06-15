package com.theguardian.sql;

import org.junit.Test;

import static com.theguardian.sql.SqlBuilder.select;
import static com.theguardian.sql.Tables.*;
import static org.junit.Assert.assertEquals;

public class OrderByTests {
    @Test
    public void testOrderByImplicitAsc(){
        String expected = "SELECT * FROM user ORDER BY age ASC";
        String actual = select(QueryConstants.STAR)
                .from(USER_TABLE)
                .orderBy(Fields.AGE)
                .build();

        assertEquals(expected, actual);
    }

    @Test
    public void testOrderByExplicitAsc(){
        String expected = "SELECT * FROM user ORDER BY age ASC";
        String actual = select(QueryConstants.STAR)
                .from(USER_TABLE)
                .orderBy(Fields.AGE)
                .asc()
                .build();

        assertEquals(expected, actual);
    }

    @Test
    public void testOrderByWithWhere(){
        String expected = "SELECT * FROM user WHERE surname = 'bates' ORDER BY age DESC";
        String actual = select(QueryConstants.STAR)
                .from(USER_TABLE)
                .where(Fields.SURNAME)
                .equalTo("bates")
                .orderBy(Fields.AGE)
                .desc()
                .build();

        assertEquals(expected, actual);
    }

    @Test
    public void testOrderByDesc(){
        String expected = "SELECT * FROM user ORDER BY age DESC";
        String actual = select(QueryConstants.STAR)
                .from(USER_TABLE)
                .orderBy(Fields.AGE)
                .desc()
                .build();

        assertEquals(expected, actual);
    }

    @Test
    public void testOrderByMultipleFields(){
        String expected = "SELECT * FROM user ORDER BY age, surname ASC";
        String actual = select(QueryConstants.STAR)
                .from(USER_TABLE)
                .orderBy(Fields.AGE, Fields.SURNAME)
                .build();

        assertEquals(expected, actual);
    }
}
