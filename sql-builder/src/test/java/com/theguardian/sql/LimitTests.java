package com.theguardian.sql;

import org.junit.Test;

import static com.theguardian.sql.SqlBuilder.select;
import static org.junit.Assert.assertEquals;

public class LimitTests {
    @Test
    public void testSimpleLimit() {
        String expected = "SELECT * FROM user LIMIT 10";
        String actual = select()
                .from(Tables.USER_TABLE)
                .limit(10)
                .build();

        assertEquals(expected, actual);
    }

    @Test
    public void testLimitWithOrderBy() {
        String expected = "SELECT * FROM user ORDER BY surname ASC LIMIT 10";
        String actual = select()
                .from(Tables.USER_TABLE)
                .orderBy(Tables.Fields.SURNAME)
                .limit(10)
                .build();

        assertEquals(expected, actual);
    }
}
