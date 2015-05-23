package com.theguardian.sql;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SqlBuilderTests {

    public static final String USER_TABLE = "user";

    @Test
    public void testSimpleSelect() {
        String expected = "SELECT * FROM user";
        String actual = new SqlBuilder()
                .select()
                .from(USER_TABLE)
                .build();

        assertEquals(expected, actual);
    }

    @Test
    public void testSelectWithFields() {
        String expected = "SELECT firstname,surname,phoneNumber FROM user";
        String actual = new SqlBuilder()
                .select(Fields.FIRSTNAME, Fields.SURNAME, Fields.PHONE_NUMBER)
                .from(USER_TABLE)
                .build();

        assertEquals(expected, actual);
    }

    @Test
    public void testSelectWithSimpleWhereClause() {
        String expected = "SELECT firstname,surname,phoneNumber FROM user WHERE surname = 'bates'";
        String actual = new SqlBuilder()
                .select(Fields.FIRSTNAME, Fields.SURNAME, Fields.PHONE_NUMBER)
                .from(USER_TABLE)
                .where(Fields.SURNAME)
                .equalTo("bates")
                .build();

        assertEquals(expected, actual);
    }

    @Test
    public void testSelectWithGreaterThanWhereClause() {
        String expected = "SELECT * FROM user WHERE age > 18";
        String actual = new SqlBuilder()
                .select()
                .from(USER_TABLE)
                .where(Fields.AGE)
                .greaterThan(18)
                .build();

        assertEquals(expected, actual);
    }

    @Test
    public void testSelectWithGreaterThanOrEqualToWhereClause() {
        String expected = "SELECT * FROM user WHERE age >= 18";
        String actual = new SqlBuilder()
                .select()
                .from(USER_TABLE)
                .where(Fields.AGE)
                .greaterThanOrEqualTo(18)
                .build();

        assertEquals(expected, actual);
    }

    @Test
    public void testSelectWithLessThanWhereClause() {
        String expected = "SELECT * FROM user WHERE age < 18";
        String actual = new SqlBuilder()
                .select()
                .from(USER_TABLE)
                .where(Fields.AGE)
                .lessThan(18)
                .build();

        assertEquals(expected, actual);
    }

    @Test
    public void testSelectWithLessThanOrEqualToWhereClause() {
        String expected = "SELECT * FROM user WHERE age <= 18";
        String actual = new SqlBuilder()
                .select()
                .from(USER_TABLE)
                .where(Fields.AGE)
                .lessThanOrEqualTo(18)
                .build();

        assertEquals(expected, actual);
    }

    @Test
    public void testSelectWithStringIn() {
        String expected = "SELECT * FROM user WHERE surname IN ('bates','shields')";
        String actual = new SqlBuilder()
                .select()
                .from(USER_TABLE)
                .where(Fields.SURNAME)
                .in("bates", "shields")
                .build();

        assertEquals(expected, actual);
    }

    @Test
    public void testSelectWithNumericIn() {
        String expected = "SELECT * FROM user WHERE age IN (18,23,45)";
        String actual = new SqlBuilder()
                .select()
                .from(USER_TABLE)
                .where(Fields.AGE)
                .in(18, 23, 45)
                .build();

        assertEquals(expected, actual);
    }

    @Test
    public void testSelectWithNumericNotIn() {
        String expected = "SELECT * FROM user WHERE age NOT IN (18,23,45)";
        String actual = new SqlBuilder()
                .select()
                .from(USER_TABLE)
                .where(Fields.AGE)
                .notIn(18, 23, 45)
                .build();

        assertEquals(expected, actual);
    }

    @Test
    public void testSelectByFirstNameAndSurname() {
        String expected = "SELECT * FROM user WHERE firstname = 'rupert' AND surname = 'bates'";
        String actual = new SqlBuilder()
                .select()
                .from(USER_TABLE)
                .where(Fields.FIRSTNAME)
                .equalTo("rupert")
                .and(Fields.SURNAME)
                .equalTo("bates")
                .build();

        assertEquals(expected, actual);
    }

    @Test
    public void testSelectByFirstNameAndAge() {
        String expected = "SELECT * FROM user WHERE firstname = 'rupert' AND age >= 18";
        String actual = new SqlBuilder()
                .select()
                .from(USER_TABLE)
                .where(Fields.FIRSTNAME)
                .equalTo("rupert")
                .and(Fields.AGE)
                .greaterThanOrEqualTo(18)
                .build();

        assertEquals(expected, actual);
    }

    @Test
    public void testSelectByFirstNameInListAndAge() {
        String expected = "SELECT * FROM user WHERE firstname IN ('rupert','Emily') AND age >= 18";
        String actual = new SqlBuilder()
                .select()
                .from(USER_TABLE)
                .where(Fields.FIRSTNAME)
                .in("rupert", "Emily")
                .and(Fields.AGE)
                .greaterThanOrEqualTo(18)
                .build();

        assertEquals(expected, actual);
    }

    @Test
    public void testNotEqualTo() {
        String expected = "SELECT * FROM user WHERE firstname != 'rupert'";
        String actual = new SqlBuilder()
                .select()
                .from(USER_TABLE)
                .where(Fields.FIRSTNAME)
                .notEqualTo("rupert")
                .build();

        assertEquals(expected, actual);
    }

    static class Fields {
        public static final String SURNAME = "surname";
        public static final String AGE = "age";
        public static final String FIRSTNAME = "firstname";
        public static final String PHONE_NUMBER = "phoneNumber";
    }
}
