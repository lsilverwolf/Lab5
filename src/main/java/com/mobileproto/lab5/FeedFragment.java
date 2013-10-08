package com.mobileproto.lab5;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by evan on 9/25/13.
 */
public class FeedFragment extends Fragment {

    FeedActivity activity;


    public void onCreate(Bundle savedInstanceState) {
        activity = (FeedActivity) getActivity();
        super.onCreate(savedInstanceState);


    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        ListView tweets = (ListView) getActivity().findViewById(R.id.feedList);
        activity.cursorAdapter = new TweetsListCursorAdapter(activity,
                activity.dbAdapter.getAllTweets(), activity.dbAdapter);

        tweets.setAdapter(activity.cursorAdapter);
        tweets.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                FeedItem tweet = ((TweetsListCursorAdapter.ViewHolder) view.getTag()).tweet;
                Intent in = new Intent(activity.getApplicationContext(), TweetDetailAdapter.class);
                in.putExtra("username", tweet.getName());
                in.putExtra("tweet", tweet.getContents());
                startActivity(in);
            }
        });
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.feed_fragment, null);
        final View vi = inflater.inflate(R.layout.feed_item, null);



        return v;

    }



}
