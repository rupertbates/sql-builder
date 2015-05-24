package com.theguardian.sql;

import org.junit.Test;

import static com.theguardian.sql.TestData.USER_TABLE;
import static org.junit.Assert.assertEquals;

public class DeleteTests {
    @Test
    public void testUpdate(){
        String expected = "DELETE FROM user WHERE surname = 'bates' AND age > 18";
        String actual = new SqlBuilder()
                .deleteFrom(USER_TABLE)
                .where("surname")
                .equalTo("bates")
                .and("age")
                .greaterThan(18)
                .build();

        assertEquals(expected, actual);
    }
}
