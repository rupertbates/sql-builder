package com.theguardian.androidsqlitebuilder;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.theguardian.sql.SqlBuilder.createTable;

public class UserDBHelper extends SQLiteOpenHelper {

    public static final String USER_DB = "UserDb";
    public static final int VERSION = 1;
    public static final String USER_TABLE = "user";

    static class Fields {

        public static final String ID = "id";
        public static final String FIRSTNAME = "firstname";
        public static final String SURNAME = "surname";
        public static final String AGE = "age";
    }

    public UserDBHelper(Context context) {
        super(context, USER_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTable(USER_TABLE)
                .field(Fields.ID).integer().primaryKey()
                .field(Fields.FIRSTNAME).text().notNull()
                .field(Fields.SURNAME).text().notNull()
                .field(Fields.AGE).real()
                .build()
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
