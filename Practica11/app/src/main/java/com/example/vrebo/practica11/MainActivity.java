package com.example.vrebo.practica11;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SQLiteHelper helper = new SQLiteHelper(this);
        helper.insertData();

        Cursor cursor = helper.consultData();

        String dataString = "";
        cursor.moveToFirst();
        do {
            dataString += cursor.getString(cursor.getColumnIndex(DatabaseContract.ContactContract.NAME_COL)) + " - ";
            dataString += cursor.getString(cursor.getColumnIndex(DatabaseContract.ContactContract.ADDRESS_COL)) + " - ";
            dataString += cursor.getString(cursor.getColumnIndex(DatabaseContract.ContactContract.PHONE_COL)) + " - ";
            dataString += cursor.getString(cursor.getColumnIndex(DatabaseContract.ContactContract.EMAIL_COL)) + "\n";
        } while (cursor.moveToNext());

        ((TextView) findViewById(R.id.dataTextView)).setText(dataString);
    }

    private static class SQLiteHelper extends SQLiteOpenHelper{

        public SQLiteHelper(Context context) {
            super(context, DatabaseContract.DATABASE_NAME, null, DatabaseContract.DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String createTableStmnt = "CREATE TABLE IF NOT EXISTS CONTACT (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, phone TEXT, address TEXT, email TEXT);";
            db.execSQL(createTableStmnt);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            String dropStmt = "DROP TABLE " + DatabaseContract.ContactContract.TABLE_NAME;
            db.execSQL(dropStmt);
            onCreate(db);
        }

        public Cursor consultData() {
            SQLiteDatabase database = getReadableDatabase();
            return database.query(DatabaseContract.ContactContract.TABLE_NAME, null, null, null, null, null, null);
        }

        public void insertData() {
            SQLiteDatabase database = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(DatabaseContract.ContactContract.NAME_COL, "Pancha");
            values.put(DatabaseContract.ContactContract.ADDRESS_COL, "Algún lugar");
            values.put(DatabaseContract.ContactContract.PHONE_COL, "555555");
            values.put(DatabaseContract.ContactContract.EMAIL_COL, "a@a.com");
            database.insert(DatabaseContract.ContactContract.TABLE_NAME, null, values);
            values.put(DatabaseContract.ContactContract.NAME_COL, "Alan");
            values.put(DatabaseContract.ContactContract.ADDRESS_COL, "Algún otro lugar");
            values.put(DatabaseContract.ContactContract.PHONE_COL, "44444");
            values.put(DatabaseContract.ContactContract.EMAIL_COL, "b@b.com");
            database.insert(DatabaseContract.ContactContract.TABLE_NAME, null, values);
            values.put(DatabaseContract.ContactContract.NAME_COL, "Alguien");
            values.put(DatabaseContract.ContactContract.ADDRESS_COL, "Oblivion");
            values.put(DatabaseContract.ContactContract.PHONE_COL, "11111");
            values.put(DatabaseContract.ContactContract.EMAIL_COL, "o@o.com");
            database.insert(DatabaseContract.ContactContract.TABLE_NAME, null, values);
        }
    }



    private static final class DatabaseContract {
        public static final String DATABASE_NAME = "CONTACT-BOOK.db";
        public static final int DATABASE_VERSION = 1;

        public static final class ContactContract implements BaseColumns {
            public static final String TABLE_NAME = "CONTACT";
            public static final String NAME_COL = "name";
            public static final String PHONE_COL = "phone";
            public static final String ADDRESS_COL = "address";
            public static final String EMAIL_COL = "email";
        }
    }
}
