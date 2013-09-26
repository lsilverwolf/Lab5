package com.mobileproto.lab5;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by evan on 9/25/13.
 */
public class ConnectionActivity extends Activity implements ActionBar.TabListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connections);

        /*
         *  The following code is used to set up the tabs used for navigation.
         *  You shouldn't need to touch the following code.
         */
        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.addTab(actionBar.newTab().setText(R.string.tab1)
                .setTabListener(this));
        actionBar.addTab(actionBar.newTab().setText(R.string.tab2)
                .setTabListener(this));
        actionBar.addTab(actionBar.newTab().setText(R.string.tab3)
                .setTabListener(this));
        actionBar.setStackedBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.android_dark_blue)));





    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft){

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft){

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft){

    }
}
