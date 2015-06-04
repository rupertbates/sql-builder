package com.theguardian.sql;

import org.junit.Test;

import static com.theguardian.sql.SqlBuilder.deleteFrom;
import static com.theguardian.sql.Tables.USER_TABLE;
import static org.junit.Assert.assertEquals;

public class DeleteTests {
    @Test
    public void testUpdate() {
        String expected = "DELETE FROM user WHERE surname = 'bates' AND age > 18";
        String actual = deleteFrom(USER_TABLE)
                .where(Tables.Fields.SURNAME)
                .equalTo("bates")
                .and(Tables.Fields.AGE)
                .greaterThan(18)
                .build();

        assertEquals(expected, actual);
    }
}
