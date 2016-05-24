package com.example.vrebo.practica5;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by VREBO on 19/05/2016.
 */
public class TabFragment extends Fragment {

    public static final java.lang.String EXTRA_TAB = "TAB_ID";

    private Fragment contentFragment;
    private Bundle argsFragment;

    public TabFragment() {
        argsFragment = new Bundle(1);
    }

    private final Button.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int color;
            switch (v.getId()) {
                case R.id.red_button:
                    color = Color.RED;
                    break;
                case R.id.green_button:
                    color = Color.GREEN;
                    break;
                case R.id.blue_button:
                    color = Color.BLUE;
                    break;
                case R.id.cyan_button:
                    color = Color.CYAN;
                    break;
                case R.id.magenta_button:
                    color = Color.MAGENTA;
                    break;
                default:
                    color = Color.YELLOW;
                    break;
            }
            argsFragment.putInt(ColorFragment.EXTRA_COLOR, color);
            contentFragment.onStop();
            contentFragment = new ColorFragment();
            contentFragment.setArguments(argsFragment);
            replaceContentFragment();
        }
    };

    private void replaceContentFragment() {
        FragmentTransaction fmt = getFragmentManager().beginTransaction();
        fmt.replace(R.id.container_fragment, contentFragment);
        fmt.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fmt.commit();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle args = getArguments();
        if (args != null) {
            int tabId = args.getInt(EXTRA_TAB);
            View view = inflater.inflate(tabId, null);

            if (tabId == R.layout.rgb_tab_fragment) {
                ((Button) view.findViewById(R.id.red_button)).setOnClickListener(onClickListener);
                ((Button) view.findViewById(R.id.green_button)).setOnClickListener(onClickListener);
                ((Button) view.findViewById(R.id.blue_button)).setOnClickListener(onClickListener);
                argsFragment.putInt(ColorFragment.EXTRA_COLOR, Color.RED);
            } else {

                ((Button) view.findViewById(R.id.cyan_button)).setOnClickListener(onClickListener);
                ((Button) view.findViewById(R.id.magenta_button)).setOnClickListener(onClickListener);
                ((Button) view.findViewById(R.id.yellow_button)).setOnClickListener(onClickListener);
                argsFragment.putInt(ColorFragment.EXTRA_COLOR, Color.CYAN);
            }
            contentFragment = new ColorFragment();
            contentFragment.setArguments(argsFragment);
            replaceContentFragment();
            return view;
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
