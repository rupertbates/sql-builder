package com.theguardian.sql;

import org.junit.Test;

import static com.theguardian.sql.SqlBuilder.select;
import static com.theguardian.sql.SqlBuilder.update;
import static org.junit.Assert.assertEquals;

public class SubQueryTests {
    @Test
    public void testSubQueryInUpdate() {
        String expected = "UPDATE user SET login_count = (SELECT login_count + 1 FROM user WHERE id = 1234) WHERE id = 1234";

        Builder subquery = select(TestData.Fields.LOGIN_COUNT + " + 1")
                .from(TestData.USER_TABLE)
                .where(TestData.Fields.ID)
                .equalTo(1234);

        String actual = update(TestData.USER_TABLE)
                .set(TestData.Fields.LOGIN_COUNT, subquery)
                .where(TestData.Fields.ID)
                .equalTo(1234)
                .build();

        assertEquals(expected, actual);
    }

    @Test
    public void testSelectSubQuery() {
        String expected = "SELECT firstname, surname FROM user WHERE login_count > (SELECT AVG(login_count) FROM user)";

        Builder subquery = select()
                .avg(TestData.Fields.LOGIN_COUNT)
                .from(TestData.USER_TABLE);

        String actual = select(TestData.Fields.FIRSTNAME, TestData.Fields.SURNAME)
                .from(TestData.USER_TABLE)
                .where(TestData.Fields.LOGIN_COUNT)
                .greaterThan(subquery)
                .build();

        assertEquals(expected, actual);
    }
}
