package vrebo.itver.contactbook;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ContactAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        setTitle("LISTA DE CONTACTOS");

        ContentResolver cr = getContentResolver();
        Cursor contacts =
                cr.query(Uri.parse("content://mx.edu.itver.contact-book.provider/contact"), null, null, null, null);

        adapter = new ContactAdapter(this, contacts, 0);
        ((ListView) findViewById(R.id.listView)).setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_contact_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(this, NewContactActivity.class);
        startActivityForResult(intent, 0);
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Contact contact = (Contact) data.getSerializableExtra("contact");
            ContentResolver cr = getContentResolver();
            ContentValues values = new ContentValues();
            values.put("name", contact.getName());
            values.put("lastName", contact.getLastName());
            values.put("email", contact.getEmail());
            values.put("telephone", contact.getTelephone());
            values.put("address", contact.getAddress());
            cr.insert(Uri.parse("content://mx.edu.itver.contact-book.provider"), values);
            adapter.changeCursor(cr.query(Uri.parse("content://mx.edu.itver.contact-book.provider/contact"), null, null, null, null));
        }
    }
}
