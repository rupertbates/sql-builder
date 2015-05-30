package com.theguardian.androidsqlitebuilder;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

public class UserTableTests extends AndroidTestCase {
    public void testUserTableCreation() {
        UserDBHelper helper = new UserDBHelper(getContext());
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '" + UserDBHelper.USER_TABLE + "'", null);
        assertNotNull(cursor);
        assertEquals(1, cursor.getCount());
        cursor.close();
    }

    public void testInsert(){
        UserDBHelper helper = new UserDBHelper(getContext());
        SQLiteDatabase db = helper.getWritableDatabase();
        //db.execSQL(insert());
    }
}
