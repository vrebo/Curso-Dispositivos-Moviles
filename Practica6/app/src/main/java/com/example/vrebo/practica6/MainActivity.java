package com.example.vrebo.practica6;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText nameInput;
    private EditText addressInput;
    private EditText phoneInput;
    private EditText emailInput;
    private ArrayAdapter<String> adapter;
    private List<String> dataList;

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
                    dataList.add(
                            "\tName: " + name +
                                    "\n\tAddress: " + address +
                                    "\n\tPhone: " + phone +
                                    "\n\tEmail: " + email
                    );
                    nameInput.setText("");
                    addressInput.setText("");
                    phoneInput.setText("");
                    emailInput.setText("");
                    adapter.notifyDataSetChanged();
                    break;
                default:
                    adapter.clear();
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((ImageButton) findViewById(R.id.addButton)).setOnClickListener(onClickListener);
        ((ImageButton) findViewById(R.id.deleteButton)).setOnClickListener(onClickListener);
        ListView listView = (ListView) findViewById(R.id.listView);

        dataList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(adapter);
        nameInput = (EditText) findViewById(R.id.nameInput);
        addressInput = (EditText) findViewById(R.id.addressInput);
        phoneInput = (EditText) findViewById(R.id.phoneInput);
        emailInput = (EditText) findViewById(R.id.emailInput);
    }
}
