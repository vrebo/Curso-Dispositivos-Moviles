package vrebo.itver.contactbookprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.webkit.MimeTypeMap;

/**
 * Created by CERO on 21/04/2016.
 */
public class ContactBookProvider extends ContentProvider {

    private static final String URI_AUTHORITY_STRING = "content://mx.edu.itver.contact-book.provider";
    private static final String URI_ALL_CONTACTS_STRING = "content://mx.edu.itver.contact-book.provider/contact";
    private static final String URI_CONTACT_BY_ID_STRING = "content://mx.edu.itver.contact-book.provider/contact/#";
    private static final int ALL_CONTACTS_CODE = 1;
    private static final int CONTACT_BY_ID_CODE = 2;

    public static final Uri URI_AUTHORITY;
    public static final Uri URI_ALL_CONTACTS;
    public static final Uri URI_CONTACT_BY_ID;
    public static final UriMatcher URI_MATCHER;

    private static final String DB_NAME = "CONTACT-BOOK";
    private static final int DB_VERSION = 2;

    static {
        URI_AUTHORITY = Uri.parse(URI_AUTHORITY_STRING);
        URI_ALL_CONTACTS = Uri.parse(URI_ALL_CONTACTS_STRING);
        URI_CONTACT_BY_ID = Uri.parse(URI_CONTACT_BY_ID_STRING);

        URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
        URI_MATCHER.addURI("content://mx.edu.itver.contact-book.provider", "contact", ALL_CONTACTS_CODE);
        URI_MATCHER.addURI("content://mx.edu.itver.contact-book.provider", "contact/#", CONTACT_BY_ID_CODE);
    }

    private DatabaseHelper dbHelper;


    @Override
    public boolean onCreate() {
        dbHelper = new DatabaseHelper(getContext(), DB_NAME, null, DB_VERSION);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase database = null;

        if (URI_MATCHER.match(uri) == CONTACT_BY_ID_CODE) {
            selection = "_id = " + uri.getLastPathSegment();
        }

        database = dbHelper.getReadableDatabase();
        return database.query(Contact.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        switch (URI_MATCHER.match(uri)) {
            case ALL_CONTACTS_CODE:
                return "vnd.android.cursor.dir/mx.edu.itver.contact-book.provider.contact";
            case CONTACT_BY_ID_CODE:
                return "vnd.android.cursor.item/mx.edu.itver.contact-book.provider.contact";
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        return ContentUris
                .withAppendedId(URI_CONTACT_BY_ID,
                        database.insert(Contact.TABLE_NAME, null, values));
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        database.delete(Contact.TABLE_NAME, selection, selectionArgs);
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        return database.update(Contact.TABLE_NAME,values,selection,selectionArgs);
    }
}
