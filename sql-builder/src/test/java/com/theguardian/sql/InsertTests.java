package com.theguardian.sql;

import org.junit.Test;

import static com.theguardian.sql.SqlBuilder.insert;
import static com.theguardian.sql.Tables.USER_TABLE;
import static org.junit.Assert.assertEquals;

public class InsertTests {
    @Test
    public void testInsertWithoutFields() {
        String expected = "INSERT INTO user VALUES ('Ian', 'Curtis', 'Joy Division')";
        String actual = insert()
                .into(USER_TABLE)
                .values("Ian", "Curtis", "Joy Division")
                .build();

        assertEquals(expected, actual);
    }

    @Test
    public void testInsertWithFields() {
        String expected = "INSERT INTO user (firstname, surname, band) VALUES ('Ian', 'Curtis', 'Joy Division')";
        String actual = insert()
                .into(USER_TABLE, "firstname", "surname", "band")
                .values("Ian", "Curtis", "Joy Division")
                .build();

        assertEquals(expected, actual);
    }
}
