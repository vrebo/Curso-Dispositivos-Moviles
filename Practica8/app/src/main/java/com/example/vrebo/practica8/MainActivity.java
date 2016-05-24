package com.example.vrebo.practica8;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int NAME_REQUEST = 1;
    private static final int EMAIL_REQUEST = 2;

    private Button.OnClickListener onClickListener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, DataGathererActivity.class);
            int hintId;
            int request;
            switch (v.getId()){
                case R.id.nameButton:
                    hintId = R.string.name_hint;
                    request = NAME_REQUEST;
                    break;
                default:
                    hintId = R.string.email_hint;
                    request = EMAIL_REQUEST;
            }
            intent.putExtra(DataGathererActivity.EXTRA_HINT, hintId);
            startActivityForResult(intent, request);
        }
    };

    private TextView nameTextView;
    private TextView emailTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameTextView = (TextView) findViewById(R.id.nameTextView);
        emailTextView = (TextView) findViewById(R.id.emailTextView);

        ((Button)findViewById(R.id.nameButton)).setOnClickListener(onClickListener);
        ((Button)findViewById(R.id.emailButton)).setOnClickListener(onClickListener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode != RESULT_CANCELED){
            String dataString= data.getStringExtra(DataGathererActivity.EXTRA_DATA);
            if(requestCode == NAME_REQUEST){
                nameTextView.setText(dataString);
            }else{
                emailTextView.setText(dataString);
            }
        }
    }
}
