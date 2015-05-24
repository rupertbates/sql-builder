package com.theguardian.sql;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SubQueryTests {
    @Test
    public void testSubQuery(){
        String expected = "UPDATE user SET login_count = (SELECT login_count + 1 FROM user WHERE id = 1234) WHERE id = 1234";

        Builder subquery = new SqlBuilder()
                .select("login_count + 1")
                .from(TestData.USER_TABLE)
                .where("id")
                .equalTo(1234);

        String actual = new SqlBuilder()
                .update(TestData.USER_TABLE)
                .set("login_count", subquery)
                .where("id")
                .equalTo(1234)
                .build();

        assertEquals(expected, actual);
    }

    @Test
    public void testSelectSubQuery(){
        String expected = "SELECT firstname, surname FROM user WHERE login_count > (SELECT AVG(login_count) FROM user)";

        Builder subquery = new SqlBuilder()
                .select().avg("login_count")
                .from(TestData.USER_TABLE);

        String actual = new SqlBuilder()
                .select("firstname", "surname")
                .from(TestData.USER_TABLE)
                .where("login_count")
                .greaterThan(subquery)
                .build();

        assertEquals(expected, actual);
    }
}
