package com.example.vrebo.practica10;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dropTable();
        createDatabase();
        insertData();
        Cursor cursor = retrieveData();

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

    private Cursor retrieveData() {
        SQLiteDatabase database = openOrCreateDatabase(DatabaseContract.DATABASE_NAME, MODE_PRIVATE, null);
        return database.query(DatabaseContract.ContactContract.TABLE_NAME, null, null, null, null, null, null);
    }

    private void insertData() {
        SQLiteDatabase database = openOrCreateDatabase(DatabaseContract.DATABASE_NAME, MODE_PRIVATE, null);
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

    private void dropTable() {
        SQLiteDatabase database = openOrCreateDatabase(DatabaseContract.DATABASE_NAME, MODE_PRIVATE, null);
        String dropStmt = "DROP TABLE " + DatabaseContract.ContactContract.TABLE_NAME;
        database.execSQL(dropStmt);
    }


    private void createDatabase() {
        SQLiteDatabase database = openOrCreateDatabase(DatabaseContract.DATABASE_NAME, MODE_PRIVATE, null);
        String createTableStmnt = "CREATE TABLE IF NOT EXISTS CONTACT (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, phone TEXT, address TEXT, email TEXT);";
        database.execSQL(createTableStmnt);
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
