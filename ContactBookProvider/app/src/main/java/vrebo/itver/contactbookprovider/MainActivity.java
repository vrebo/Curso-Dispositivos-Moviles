package vrebo.itver.contactbookprovider;

import android.app.Activity;
import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by CERO on 21/04/2016.
 */
public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ContentResolver cr = getContentResolver();

        ContentValues cv = new ContentValues();
        cv.put(Contact.COL_NAME, "Pancha");
        cv.put(Contact.COL_LASTNAME, "Something");
        cv.put(Contact.COL_EMAIL, "Something@asda.com");
        cv.put(Contact.COL_TELEPHONE, "2222222");
        cv.put(Contact.COL_ADDRESS, "Andador 9 #35");


        Uri uri = cr.insert(ContactBookProvider.URI_AUTHORITY, cv);
        consulta(R.id.texto);

        long id = Long.parseLong(uri.getLastPathSegment());


        cv.put(Contact.COL_NAME, "Francisca");
        cr.update(ContactBookProvider.URI_AUTHORITY, cv, "_id = ?", new String[]{ Long.toString(id)});
        consulta(R.id.textoUpdate);


        cr.delete(ContactBookProvider.URI_AUTHORITY, "_id = ?", new String[]{ Long.toString(id)});
        consulta(R.id.textoDelete);
    }

    private void consulta(int idTextView) {
        ContentResolver cr = getContentResolver();
        Cursor cursor = null;
        String string = "";
        cursor = cr.query(ContactBookProvider.URI_ALL_CONTACTS, null, null, null, null);

        cursor.moveToFirst();
        do {
            int index = cursor.getColumnIndex(Contact.COL_NAME);
            string += cursor.getString(index) + "\n";
        } while (cursor.moveToNext());

        ((TextView) findViewById(idTextView)).setText(string);
    }
}
