package com.mobileproto.lab5;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FeedActivity extends Activity {

    TweetDbAdapter dbAdapter;
    TweetsListCursorAdapter cursorAdapter;



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                /*PostData p = new PostData();
                p.execute(null, null, null);*/
                GetData g = new GetData();
                g.execute(null,null,null);
                //Cursor cursor = dbAdapter.getAllTweets();
                //cursor.moveToFirst();
                //Log.d("wolfpack", TweetDbAdapter.tweetFromCursor(cursor).toString());
                return true;
            case R.id.action_tweet:
                MakeTweetDialog m = new MakeTweetDialog();
                m.show(this.getFragmentManager(), null);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbAdapter = new TweetDbAdapter(this);
        dbAdapter.open();



        // Define view fragments
        FeedFragment feedFragment = new FeedFragment();
        ConnectionFragment connectionFragment = new ConnectionFragment();
        SearchFragment searchFragment = new SearchFragment();

        /*
         *  The following code is used to set up the tabs used for navigation.
         *  You shouldn't need to touch the following code.
         */
        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);


        ActionBar.Tab feedTab = actionBar.newTab().setText(R.string.tab1);
        feedTab.setTabListener(new NavTabListener(feedFragment));

        ActionBar.Tab connectionTab = actionBar.newTab().setText(R.string.tab2);
        connectionTab.setTabListener(new NavTabListener(connectionFragment));

        ActionBar.Tab searchTab = actionBar.newTab().setText(R.string.tab3);
        searchTab.setTabListener(new NavTabListener(searchFragment));

        actionBar.addTab(feedTab);
        actionBar.addTab(connectionTab);
        actionBar.addTab(searchTab);

        actionBar.setStackedBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.android_dark_blue)));

    }



    private class PostData extends AsyncTask<String, String, String>{
        @Override
        protected String doInBackground(String... uri){
            String responseString ="";
            Log.d("wolfpack", "I'm posting data!");
            try{
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost("http://twitterproto.herokuapp.com/wolf/tweets");
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
                nameValuePairs.add(new BasicNameValuePair("tweet", "spirit hoods are still cool!"));
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                HttpResponse response = httpClient.execute(httppost);
                Log.d("wolfpack", "I posted the tweet!");

            } catch (Exception e){
                e.printStackTrace();
            }
            return responseString;
        }
    }

    private class GetData extends AsyncTask<String, String, String>{
        @Override
        protected String doInBackground(String... uri){
            String responseString ="";
            Log.d("wolfpack", "I'm getting data!");
            try{
                HttpClient httpClient = new DefaultHttpClient();
                HttpGet httpget = new HttpGet("http://twitterproto.herokuapp.com/tweets");

                HttpResponse response = httpClient.execute(httpget);
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                response.getEntity().writeTo(out);
                out.close();
                responseString = out.toString();
                List<List<String>> parsedResponse = JSONParse(responseString);
                //Log.d("wolfpack", responseString);
                dbAdapter.clearData();
                for (List<String> l: parsedResponse){
                    Log.d("wolfpack", l.get(0));
                    dbAdapter.createTweet(l.get(1), l.get(0), Long.valueOf(l.get(2)));
                }
                Log.d("wolfpack", "soclose");
                cursorAdapter.changeCursor(dbAdapter.getAllTweets());
                cursorAdapter.notifyDataSetChanged();
                Log.d("wolfpack", "done");


            } catch (Exception e){
                e.printStackTrace();
            }
            return responseString;
        }

    }



    public List<List<String>> JSONParse(String responseString) throws JSONException {
        JSONObject obj = new JSONObject(responseString);
        List<List<String>> res = new ArrayList<List<String>>();
        JSONArray array;
        array = obj.getJSONArray("tweets");

        for (int j = 0; j<array.length(); j++){
            List<String> inner = new ArrayList<String>();
            inner.add(array.getJSONObject(j).getString("tweet"));
            inner.add(array.getJSONObject(j).getString("username"));
            inner.add(array.getJSONObject(j).getString("date"));
            inner.add(array.getJSONObject(j).getString("_id"));
            res.add(inner);
        }
        return res;

    }

}

