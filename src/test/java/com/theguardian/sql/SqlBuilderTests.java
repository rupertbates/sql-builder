package com.theguardian.sql;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SqlBuilderTests {
    @Test
    public void testSimpleSelect(){
        String expected = "SELECT * FROM user";
        String actual = new SqlBuilder()
                .select()
                .from("user")
                .build();

        assertEquals(expected, actual);
    }

    @Test
    public void testSelectWithFields(){
        String expected = "SELECT firstname,surname,phoneNumber FROM user";
        String actual = new SqlBuilder()
                .select("firstname", "surname", "phoneNumber")
                .from("user")
                .build();

        assertEquals(expected, actual);
    }

    @Test
    public void testSelectWithSimpleWhereClause(){
        String expected = "SELECT firstname,surname,phoneNumber FROM user WHERE surname = 'bates'";
        String actual = new SqlBuilder()
                .select("firstname", "surname", "phoneNumber")
                .from("user")
                .where("surname")
                .equalTo("bates")
                .build();

        assertEquals(expected, actual);
    }

    @Test
    public void testSelectWithGreaterThanWhereClause(){
        String expected = "SELECT * FROM user WHERE age > 18";
        String actual = new SqlBuilder()
                .select()
                .from("user")
                .where("age")
                .greaterThan(18)
                .build();

        assertEquals(expected, actual);
    }

    @Test
    public void testSelectWithGreaterThanOrEqualToWhereClause(){
        String expected = "SELECT * FROM user WHERE age >= 18";
        String actual = new SqlBuilder()
                .select()
                .from("user")
                .where("age")
                .greaterThanOrEqualTo(18)
                .build();

        assertEquals(expected, actual);
    }

    @Test
    public void testSelectWithLessThanWhereClause(){
        String expected = "SELECT * FROM user WHERE age < 18";
        String actual = new SqlBuilder()
                .select()
                .from("user")
                .where("age")
                .lessThan(18)
                .build();

        assertEquals(expected, actual);
    }

    @Test
    public void testSelectWithLessThanOrEqualToWhereClause(){
        String expected = "SELECT * FROM user WHERE age <= 18";
        String actual = new SqlBuilder()
                .select()
                .from("user")
                .where("age")
                .lessThanOrEqualTo(18)
                .build();

        assertEquals(expected, actual);
    }
}
