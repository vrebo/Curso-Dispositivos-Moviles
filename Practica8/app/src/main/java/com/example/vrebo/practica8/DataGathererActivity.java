package com.example.vrebo.practica8;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by VREBO on 20/05/2016.
 */
public class DataGathererActivity extends AppCompatActivity {


    public static final String EXTRA_HINT = "HINT";
    public static final String EXTRA_DATA = "DATA";

    private Button.OnClickListener onClickListener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            
            switch (v.getId()) {
                case R.id.cancelButton:
                    setResult(RESULT_CANCELED);
                    finish();
                    break;
                default:
                    String dataString = dataInput.getText().toString();
                    if (dataString.isEmpty()) {
                        Toast.makeText(DataGathererActivity.this, "Data field is empty", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent data = new Intent();
                        data.putExtra(EXTRA_DATA, dataString);
                        setResult(RESULT_OK, data);
                        finish();
                    }

            }
        }
    };

    private EditText dataInput;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_gather_activity);

        dataInput = (EditText) findViewById(R.id.dataInput);

        int hint = getIntent().getIntExtra(EXTRA_HINT, -1);
        if (hint != -1) {
            dataInput.setHint(hint);
        }

        ((Button)findViewById(R.id.saveButton)).setOnClickListener(onClickListener);
        ((Button)findViewById(R.id.cancelButton)).setOnClickListener(onClickListener);

    }
}
