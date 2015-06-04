package com.theguardian.sql;

import org.junit.Test;

import static com.theguardian.sql.SqlBuilder.update;
import static com.theguardian.sql.Tables.USER_TABLE;

import static org.junit.Assert.assertEquals;

public class UpdateTests {
    @Test
    public void testUpdate(){
        String expected = "UPDATE user SET firstname = 'Ian', surname = 'Curtis' WHERE band = 'Joy Division'";
        String actual = update(USER_TABLE)
                .set(Tables.Fields.FIRSTNAME, "Ian")
                .set(Tables.Fields.SURNAME, "Curtis")
                .where(Tables.Fields.BAND)
                .equalTo("Joy Division")
                .build();

        assertEquals(expected, actual);
    }
}
