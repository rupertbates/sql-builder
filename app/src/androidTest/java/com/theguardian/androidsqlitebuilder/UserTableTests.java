package com.theguardian.androidsqlitebuilder;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import static com.theguardian.androidsqlitebuilder.UserDBHelper.*;
import static com.theguardian.androidsqlitebuilder.UserDBHelper.USER_TABLE;
import static com.theguardian.sql.SqlBuilder.insert;
import static com.theguardian.sql.SqlBuilder.select;

public class UserTableTests extends AndroidTestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        getContext().deleteDatabase(USER_DB);
    }

    public void testUserTableCreation() {
        UserDBHelper helper = new UserDBHelper(getContext());
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '" + USER_TABLE + "'", null);
        assertNotNull(cursor);
        assertEquals(1, cursor.getCount());
        cursor.close();
    }

    public void testInsert() {
        UserDBHelper helper = new UserDBHelper(getContext());
        SQLiteDatabase db = helper.getWritableDatabase();
        String insertQuery = insert()
                .into(USER_TABLE)
                .values(TestData.ID, TestData.RUPERT, TestData.BATES, TestData.AGE)
                .build();

        db.execSQL(insertQuery);

        String selectQuery = select(Fields.ID, Fields.FIRSTNAME, Fields.SURNAME, Fields.AGE)
                .from(USER_TABLE)
                .build();

        Cursor cursor = helper.getReadableDatabase().rawQuery(selectQuery, null);

        assertEquals(1, cursor.getCount());
        assertTrue(cursor.moveToFirst());
        assertEquals(TestData.ID, cursor.getInt(cursor.getColumnIndex(Fields.ID)));
        assertEquals(TestData.RUPERT, cursor.getString(cursor.getColumnIndex(Fields.FIRSTNAME)));
        assertEquals(TestData.BATES, cursor.getString(cursor.getColumnIndex(Fields.SURNAME)));
        assertEquals(TestData.AGE, cursor.getFloat(cursor.getColumnIndex(Fields.AGE)));
        cursor.close();

    }
}
