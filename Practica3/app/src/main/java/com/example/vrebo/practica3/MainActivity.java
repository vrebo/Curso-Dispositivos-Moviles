package com.example.vrebo.practica3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView contactData;
    private EditText nameInput;
    private EditText addressInput;
    private EditText phoneInput;
    private EditText emailInput;

    private Button.OnClickListener onClickListener = new Button.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.addButton:
                    String name, address, phone, email;
                    name = nameInput.getText().toString();
                    address = addressInput.getText().toString();
                    phone = phoneInput.getText().toString();
                    email = emailInput.getText().toString();
                    contactData.setText(
                            "New contact added\n\n" +
                                    "\n\tName: " + name +
                                    "\n\tAddress: " + address +
                                    "\n\tPhone: " + phone +
                                    "\n\tEmail: " + email
                    );
                    nameInput.setText("");
                    addressInput.setText("");
                    phoneInput.setText("");
                    emailInput.setText("");
                    break;
                default:
                    contactData.setText(R.string.no_contact);
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((ImageButton) findViewById(R.id.addButton)).setOnClickListener(onClickListener);
        ((ImageButton) findViewById(R.id.deleteButton)).setOnClickListener(onClickListener);
        contactData = (TextView)findViewById(R.id.contactData);
        nameInput = (EditText) findViewById(R.id.nameInput);
        addressInput = (EditText) findViewById(R.id.addressInput);
        phoneInput = (EditText) findViewById(R.id.phoneInput);
        emailInput = (EditText) findViewById(R.id.emailInput);
    }
}
