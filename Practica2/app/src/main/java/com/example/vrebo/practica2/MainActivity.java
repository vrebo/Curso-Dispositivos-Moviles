package com.example.vrebo.practica2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView.OnEditorActionListener onEditorActionListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                String name = nameInput.getText().toString();
                if (name.isEmpty()) {
                   name = "Nameless";
                }else{
                    nameInput.setText("");
                }
                sayHelloTo(name);
            }
            return true;
        }
    };

    private EditText nameInput;
    private TextView greetingMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameInput = (EditText) findViewById(R.id.nameInput);
        greetingMessage = (TextView) findViewById(R.id.greetingMessage);

        nameInput.setOnEditorActionListener(onEditorActionListener);
        String message = String.format(getResources().getString(R.string.greeting), "World");
        greetingMessage.setText(message);
        sayHelloTo("Wold");
    }
    
    private void sayHelloTo(String name){
        String message = String.format(getResources().getString(R.string.greeting), name);
        greetingMessage.setText(message);
    }
}
