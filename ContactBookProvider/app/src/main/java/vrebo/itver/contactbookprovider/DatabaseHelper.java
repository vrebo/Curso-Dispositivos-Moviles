package vrebo.itver.contactbookprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by CERO on 21/04/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DROP_TABLE_STATEMENT;
    private static final String CREATE_TABLE_STATEMENT;

    static {
        StringBuilder sb = new StringBuilder("DROP TABLE IF EXISTS ")
                .append(Contact.TABLE_NAME).append(';');
        DROP_TABLE_STATEMENT = sb.toString();
        sb = new StringBuilder("CREATE TABLE IF NOT EXISTS ")
                .append(Contact.TABLE_NAME).append(" (")
                .append(Contact._ID).append(" INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append(Contact.COL_NAME).append(" TEXT,")
                .append(Contact.COL_LASTNAME).append(" TEXT,")
                .append(Contact.COL_ADDRESS).append(" TEXT,")
                .append(Contact.COL_TELEPHONE).append(" TEXT,")
                .append(Contact.COL_EMAIL).append(" TEXT);");
        CREATE_TABLE_STATEMENT = sb.toString();
    }

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        Log.d("DatabaseHelper", "DatabaseHelper creado");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("DatabaseHelper", "Base de datos creada");
        db.execSQL(CREATE_TABLE_STATEMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("DatabaseHelper", "Base de datos actualizada");
        db.execSQL(DROP_TABLE_STATEMENT);
        db.execSQL(CREATE_TABLE_STATEMENT);

    }
}
