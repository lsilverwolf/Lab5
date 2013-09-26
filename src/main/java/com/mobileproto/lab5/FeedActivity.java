package com.mobileproto.lab5;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class FeedActivity extends Activity implements ActionBar.TabListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        /*
         * Creating some sample test data to see what the layout looks like.
         * You should eventually delete this.
         */
        FeedItem item1 = new FeedItem("@TimRyan", "Dear reader, you are reading.");
        FeedItem item2 = new FeedItem("@EvanSimpson", "Hey @TimRyan");
        FeedItem item3 = new FeedItem("@JulianaNazare", "Everything happens so much.");
        FeedItem item4 = new FeedItem("@reyner", "dGhlIGNvb2wgbmV3IHRoaW5nIHRvIGRvIGlzIGJhc2U2NCBlY29kZSB5b3VyIHR3ZWV0cw==");
        List<FeedItem> sampleData = new ArrayList<FeedItem>();
        sampleData.add(item1);
        sampleData.add(item2);
        sampleData.add(item3);
        sampleData.add(item4);

        // Set up the ArrayAdapter for the feedList
        FeedListAdapter feedListAdapter = new FeedListAdapter(this, sampleData);
        ListView feedList = (ListView) findViewById(R.id.feedList);
        feedList.setAdapter(feedListAdapter);



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
