package com.theguardian.sql;

import org.junit.Test;

import static com.theguardian.sql.SqlBuilder.update;
import static com.theguardian.sql.TestData.USER_TABLE;

import static org.junit.Assert.assertEquals;

public class UpdateTests {
    @Test
    public void testUpdate(){
        String expected = "UPDATE user SET firstname = 'Ian', surname = 'Curtis' WHERE band = 'Joy Division'";
        String actual = update(USER_TABLE)
                .set(TestData.Fields.FIRSTNAME, "Ian")
                .set(TestData.Fields.SURNAME, "Curtis")
                .where(TestData.Fields.BAND)
                .equalTo("Joy Division")
                .build();

        assertEquals(expected, actual);
    }
}
