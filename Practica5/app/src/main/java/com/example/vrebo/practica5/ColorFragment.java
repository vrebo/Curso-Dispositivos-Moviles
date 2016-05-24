package com.example.vrebo.practica5;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by VREBO on 19/05/2016.
 */
public class ColorFragment extends Fragment {

    public static final String EXTRA_COLOR = "COLOR";
    private static final String TAG = "ColorFragment";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.colorless_fragment, null);
        Bundle args = getArguments();
        if (args != null) {
            int color = args.getInt("COLOR");
            view.findViewById(R.id.linearLayout).setBackgroundColor(color);

            Log.d(TAG, "Creating a fragment with color " + color);
        }
        return view;
    }
}
