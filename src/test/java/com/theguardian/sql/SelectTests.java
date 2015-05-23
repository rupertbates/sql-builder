package com.theguardian.sql;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SelectTests {

    @Test
    public void testSimpleSelect() {
        String expected = "SELECT * FROM user";
        String actual = new SqlBuilder()
                .select()
                .from(TestData.USER_TABLE)
                .build();

        assertEquals(expected, actual);
    }

    @Test
    public void testSelectWithFields() {
        String expected = "SELECT firstname, surname, phoneNumber FROM user";
        String actual = new SqlBuilder()
                .select(TestData.Fields.FIRSTNAME, TestData.Fields.SURNAME, TestData.Fields.PHONE_NUMBER)
                .from(TestData.USER_TABLE)
                .build();

        assertEquals(expected, actual);
    }

    @Test
    public void testSelectWithSimpleWhereClause() {
        String expected = "SELECT firstname, surname, phoneNumber FROM user WHERE surname = 'bates'";
        String actual = new SqlBuilder()
                .select(TestData.Fields.FIRSTNAME, TestData.Fields.SURNAME, TestData.Fields.PHONE_NUMBER)
                .from(TestData.USER_TABLE)
                .where(TestData.Fields.SURNAME)
                .equalTo("bates")
                .build();

        assertEquals(expected, actual);
    }

    @Test
    public void testSelectWithGreaterThan() {
        String expected = "SELECT * FROM user WHERE age > 18";
        String actual = new SqlBuilder()
                .select()
                .from(TestData.USER_TABLE)
                .where(TestData.Fields.AGE)
                .greaterThan(18)
                .build();

        assertEquals(expected, actual);
    }

    @Test
    public void testSelectWithGreaterThanOrEqualTo() {
        String expected = "SELECT * FROM user WHERE age >= 18";
        String actual = new SqlBuilder()
                .select()
                .from(TestData.USER_TABLE)
                .where(TestData.Fields.AGE)
                .greaterThanOrEqualTo(18)
                .build();

        assertEquals(expected, actual);
    }

    @Test
    public void testSelectWithLessThanWhereClause() {
        String expected = "SELECT * FROM user WHERE age < 18";
        String actual = new SqlBuilder()
                .select()
                .from(TestData.USER_TABLE)
                .where(TestData.Fields.AGE)
                .lessThan(18)
                .build();

        assertEquals(expected, actual);
    }

    @Test
    public void testSelectWithLessThanOrEqualTo() {
        String expected = "SELECT * FROM user WHERE age <= 18";
        String actual = new SqlBuilder()
                .select()
                .from(TestData.USER_TABLE)
                .where(TestData.Fields.AGE)
                .lessThanOrEqualTo(18)
                .build();

        assertEquals(expected, actual);
    }

    @Test
    public void testSelectWithStringIn() {
        String expected = "SELECT * FROM user WHERE surname IN ('bates','shields')";
        String actual = new SqlBuilder()
                .select()
                .from(TestData.USER_TABLE)
                .where(TestData.Fields.SURNAME)
                .in("bates", "shields")
                .build();

        assertEquals(expected, actual);
    }

    @Test
    public void testSelectWithNumericIn() {
        String expected = "SELECT * FROM user WHERE age IN (18,23,45)";
        String actual = new SqlBuilder()
                .select()
                .from(TestData.USER_TABLE)
                .where(TestData.Fields.AGE)
                .in(18, 23, 45)
                .build();

        assertEquals(expected, actual);
    }

    @Test
    public void testSelectWithNumericNotIn() {
        String expected = "SELECT * FROM user WHERE age NOT IN (18,23,45)";
        String actual = new SqlBuilder()
                .select()
                .from(TestData.USER_TABLE)
                .where(TestData.Fields.AGE)
                .notIn(18, 23, 45)
                .build();

        assertEquals(expected, actual);
    }

    @Test
    public void testSelectByFirstNameAndSurname() {
        String expected = "SELECT * FROM user WHERE firstname = 'rupert' AND surname = 'bates'";
        String actual = new SqlBuilder()
                .select()
                .from(TestData.USER_TABLE)
                .where(TestData.Fields.FIRSTNAME)
                .equalTo("rupert")
                .and(TestData.Fields.SURNAME)
                .equalTo("bates")
                .build();

        assertEquals(expected, actual);
    }

    @Test
    public void testSelectByFirstNameAndAge() {
        String expected = "SELECT * FROM user WHERE firstname = 'rupert' AND age >= 18";
        String actual = new SqlBuilder()
                .select()
                .from(TestData.USER_TABLE)
                .where(TestData.Fields.FIRSTNAME)
                .equalTo("rupert")
                .and(TestData.Fields.AGE)
                .greaterThanOrEqualTo(18)
                .build();

        assertEquals(expected, actual);
    }

    @Test
    public void testSelectByFirstNameInListAndAge() {
        String expected = "SELECT * FROM user WHERE firstname IN ('rupert','Emily') AND age >= 18";
        String actual = new SqlBuilder()
                .select()
                .from(TestData.USER_TABLE)
                .where(TestData.Fields.FIRSTNAME)
                .in("rupert", "Emily")
                .and(TestData.Fields.AGE)
                .greaterThanOrEqualTo(18)
                .build();

        assertEquals(expected, actual);
    }

    @Test
    public void testNotEqualTo() {
        String expected = "SELECT * FROM user WHERE firstname != 'rupert'";
        String actual = new SqlBuilder()
                .select()
                .from(TestData.USER_TABLE)
                .where(TestData.Fields.FIRSTNAME)
                .notEqualTo("rupert")
                .build();

        assertEquals(expected, actual);
    }

    @Test
    public void testLike() {
        String expected = "SELECT * FROM user WHERE firstname LIKE 'rup%'";
        String actual = new SqlBuilder()
                .select()
                .from(TestData.USER_TABLE)
                .where(TestData.Fields.FIRSTNAME)
                .like("rup%")
                .build();

        assertEquals(expected, actual);
    }

}
