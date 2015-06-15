package com.theguardian.sql;

import org.junit.Test;

import static com.theguardian.sql.SqlBuilder.select;
import static com.theguardian.sql.SqlBuilder.update;
import static org.junit.Assert.assertEquals;

public class SubQueryTests {
    @Test
    public void testSubQueryInUpdate() {
        String expected = "UPDATE user SET login_count = (SELECT login_count + 1 FROM user WHERE id = 1234) WHERE id = 1234";

        StatementBuilder subquery = select(Tables.Fields.LOGIN_COUNT + " + 1")
                .from(Tables.USER_TABLE)
                .where(Tables.Fields.ID)
                .equalTo(1234);

        String actual = update(Tables.USER_TABLE)
                .set(Tables.Fields.LOGIN_COUNT, subquery)
                .where(Tables.Fields.ID)
                .equalTo(1234)
                .build();

        assertEquals(expected, actual);
    }

    @Test
    public void testSelectSubQuery() {
        String expected = "SELECT firstname, surname FROM user WHERE login_count > (SELECT AVG(login_count) FROM user)";

        StatementBuilder subquery = select()
                .avg(Tables.Fields.LOGIN_COUNT)
                .from(Tables.USER_TABLE);

        String actual = select(Tables.Fields.FIRSTNAME, Tables.Fields.SURNAME)
                .from(Tables.USER_TABLE)
                .where(Tables.Fields.LOGIN_COUNT)
                .greaterThan(subquery)
                .build();

        assertEquals(expected, actual);
    }
}
