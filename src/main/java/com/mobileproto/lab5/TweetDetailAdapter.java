package com.mobileproto.lab5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by wolflyra on 10/2/13.
 */
public class TweetDetailAdapter extends Activity{

    @Override
    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_detail);
        Intent intent = getIntent();
        String userName = intent.getStringExtra("username");
        String text = intent.getStringExtra("tweet");



        TextView username = (TextView) findViewById(R.id.textViewUsername);
        TextView tweetContent = (TextView) findViewById(R.id.textViewTweet);

        username.setText(userName);
        tweetContent.setText(text);



    }
}
