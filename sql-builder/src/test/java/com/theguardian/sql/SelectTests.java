package com.theguardian.sql;

import com.theguardian.sql.Tables.Fields;

import org.junit.Test;

import static com.theguardian.sql.SqlBuilder.select;
import static org.junit.Assert.assertEquals;

public class SelectTests {

    @Test
    public void testSimpleSelect() {
        String expected = "SELECT * FROM user";
        String actual = select()
                .from(Tables.USER_TABLE)
                .build();

        assertEquals(expected, actual);
    }

    @Test
    public void testSelectWithFields() {
        String expected = "SELECT firstname, surname, phoneNumber FROM user";
        String actual = select(Fields.FIRSTNAME, Fields.SURNAME, Fields.PHONE_NUMBER)
                .from(Tables.USER_TABLE)
                .build();

        assertEquals(expected, actual);
    }

    @Test
    public void testSelectWithSimpleWhereClause() {
        String expected = "SELECT firstname, surname, phoneNumber FROM user WHERE surname = 'bates'";
        String actual = select(Fields.FIRSTNAME, Fields.SURNAME, Fields.PHONE_NUMBER)
                .from(Tables.USER_TABLE)
                .where(Fields.SURNAME)
                .equalTo("bates")
                .build();

        assertEquals(expected, actual);
    }

    @Test
    public void testSelectWithGreaterThan() {
        String expected = "SELECT * FROM user WHERE age > 18";
        String actual = select()
                .from(Tables.USER_TABLE)
                .where(Fields.AGE)
                .greaterThan(18)
                .build();

        assertEquals(expected, actual);
    }

    @Test
    public void testSelectWithGreaterThanOrEqualTo() {
        String expected = "SELECT * FROM user WHERE age >= 18";
        String actual = select()
                .from(Tables.USER_TABLE)
                .where(Fields.AGE)
                .greaterThanOrEqualTo(18)
                .build();

        assertEquals(expected, actual);
    }

    @Test
    public void testSelectWithLessThanWhereClause() {
        String expected = "SELECT * FROM user WHERE age < 18";
        String actual = select()
                .from(Tables.USER_TABLE)
                .where(Fields.AGE)
                .lessThan(18)
                .build();

        assertEquals(expected, actual);
    }

    @Test
    public void testSelectWithLessThanOrEqualTo() {
        String expected = "SELECT * FROM user WHERE age <= 18";
        String actual = select()
                .from(Tables.USER_TABLE)
                .where(Fields.AGE)
                .lessThanOrEqualTo(18)
                .build();

        assertEquals(expected, actual);
    }

    @Test
    public void testSelectWithStringIn() {
        String expected = "SELECT * FROM user WHERE surname IN ('bates', 'shields')";
        String actual = select()
                .from(Tables.USER_TABLE)
                .where(Fields.SURNAME)
                .in("bates", "shields")
                .build();

        assertEquals(expected, actual);
    }

    @Test
    public void testSelectWithNumericIn() {
        String expected = "SELECT * FROM user WHERE age IN (18, 23, 45)";
        String actual = select()
                .from(Tables.USER_TABLE)
                .where(Fields.AGE)
                .in(18, 23, 45)
                .build();

        assertEquals(expected, actual);
    }

    @Test
    public void testSelectWithNumericNotIn() {
        String expected = "SELECT * FROM user WHERE age NOT IN (18, 23, 45)";
        String actual = select()
                .from(Tables.USER_TABLE)
                .where(Fields.AGE)
                .notIn(18, 23, 45)
                .build();

        assertEquals(expected, actual);
    }

    @Test
    public void testSelectByFirstNameAndSurname() {
        String expected = "SELECT * FROM user WHERE firstname = 'rupert' AND surname = 'bates'";
        String actual = select()
                .from(Tables.USER_TABLE)
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
        String actual = select()
                .from(Tables.USER_TABLE)
                .where(Fields.FIRSTNAME)
                .equalTo("rupert")
                .and(Fields.AGE)
                .greaterThanOrEqualTo(18)
                .build();

        assertEquals(expected, actual);
    }

    @Test
    public void testSelectByFirstNameInListAndAge() {
        String expected = "SELECT * FROM user WHERE firstname IN ('rupert', 'Emily') AND age >= 18";
        String actual = select()
                .from(Tables.USER_TABLE)
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
        String actual = select()
                .from(Tables.USER_TABLE)
                .where(Fields.FIRSTNAME)
                .notEqualTo("rupert")
                .build();

        assertEquals(expected, actual);
    }

    @Test
    public void testLike() {
        String expected = "SELECT * FROM user WHERE firstname LIKE 'rup%'";
        String actual = select()
                .from(Tables.USER_TABLE)
                .where(Fields.FIRSTNAME)
                .like("rup%")
                .build();

        assertEquals(expected, actual);
    }

    @Test
    public void testSelectBySurnameOrAge() {
        String expected = "SELECT * FROM user WHERE surname = 'bates' OR age >= 18";
        String actual = select()
                .from(Tables.USER_TABLE)
                .where(Fields.SURNAME)
                .equalTo("bates")
                .or(Fields.AGE)
                .greaterThanOrEqualTo(18)
                .build();

        assertEquals(expected, actual);
    }

    @Test
    public void testCount() {
        String expected = "SELECT COUNT(*) FROM user WHERE surname = 'bates' OR age >= 18";
        String actual = select()
                .count()
                .from(Tables.USER_TABLE)
                .where(Fields.SURNAME)
                .equalTo("bates")
                .or(Fields.AGE)
                .greaterThanOrEqualTo(18)
                .build();

        assertEquals(expected, actual);
    }

    @Test
    public void testCountColumn() {
        String expected = "SELECT COUNT(id) FROM user WHERE surname = 'bates' OR age >= 18";
        String actual = select()
                .count(Fields.ID)
                .from(Tables.USER_TABLE)
                .where(Fields.SURNAME)
                .equalTo("bates")
                .or(Fields.AGE)
                .greaterThanOrEqualTo(18)
                .build();

        assertEquals(expected, actual);
    }

    @Test
    public void testMax() {
        String expected = "SELECT MAX(age) FROM user";
        String actual = select()
                .max(Fields.AGE)
                .from(Tables.USER_TABLE)
                .build();

        assertEquals(expected, actual);
    }

    @Test
    public void testMin() {
        String expected = "SELECT MIN(age) FROM user";
        String actual = select()
                .min(Fields.AGE)
                .from(Tables.USER_TABLE)
                .build();

        assertEquals(expected, actual);
    }

    @Test
    public void testAvg() {
        String expected = "SELECT AVG(age) FROM user";
        String actual = select()
                .avg(Fields.AGE)
                .from(Tables.USER_TABLE)
                .build();

        assertEquals(expected, actual);
    }


}
