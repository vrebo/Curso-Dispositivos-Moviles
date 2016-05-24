package com.example.vrebo.practica4;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getFragmentManager();
        FragmentTransaction fmt = fm.beginTransaction();
        fmt.add(R.id.left_container, new RedFragment());
        fmt.add(R.id.center_container, new GreenFragment());
        fmt.add(R.id.right_container, new BlueFragment());
        fmt.commit();
    }
}
