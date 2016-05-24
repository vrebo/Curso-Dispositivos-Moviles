package com.example.vrebo.practica5;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Fragment tabFragment;
    private Bundle args;

    public MainActivity() {
        args = new Bundle(1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        args.putInt(TabFragment.EXTRA_TAB, R.layout.rgb_tab_fragment);
        tabFragment = new TabFragment();
        tabFragment.setArguments(args);
        replaceTabFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        tabFragment.onStop();
        if (item.getItemId() == R.id.rgb_menu_item) {
            args.putInt(TabFragment.EXTRA_TAB, R.layout.rgb_tab_fragment);
        } else {
            args.putInt(TabFragment.EXTRA_TAB, R.layout.cmyk_tab_fragment);
        }
        tabFragment = new TabFragment();
        tabFragment.setArguments(args);
        replaceTabFragment();
        return true;
    }

    private void replaceTabFragment() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.tab_fragment, tabFragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }
}
