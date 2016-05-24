package vrebo.itver.contactbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

/**
 * Created by CERO on 25/04/2016.
 */
public class NewContactActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);
        setTitle("CONTACTO NUEVO");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_new_contact, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent data = getIntent();
        switch (item.getItemId()){
            case R.id.backMI:
                setResult(RESULT_CANCELED, data);
                break;
            case R.id.saveMI:
                Contact contact = new Contact();
                contact.setName(getTextEditText(R.id.nameET));
                contact.setLastName(getTextEditText(R.id.lastNameET));
                contact.setEmail(getTextEditText(R.id.emailET));
                contact.setAddress(getTextEditText(R.id.addressET));
                contact.setTelephone(getTextEditText(R.id.phoneET));
                data.putExtra("contact",contact);
                setResult(RESULT_OK, data);
        }
        finish();
        return true;
    }

    private String getTextEditText(int idEditText){
        return ((EditText)findViewById(idEditText)).getText().toString();
    }
}
