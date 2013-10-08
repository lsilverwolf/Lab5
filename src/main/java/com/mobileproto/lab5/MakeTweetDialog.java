package com.mobileproto.lab5;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wolflyra on 10/8/13.
 */
public class MakeTweetDialog extends DialogFragment {

    String tweet;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();


        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        final View view = inflater.inflate(R.layout.new_tweet_dialog, null);
        builder.setView(view)
                // Add action buttons

                .setPositiveButton(R.string.MakeTweetDialog, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        final EditText tweetText = (EditText) view.findViewById(R.id.newTweetEditText);
                        Log.d("wolfpack", tweetText.toString());
                        Log.d("wolfpack", tweetText.getText().toString());
                        tweet = tweetText.getText().toString();
                        //tweetText.setText("");
                        PostData p = new PostData();
                        p.execute(null, null, null);

                    }
                })
                .setNegativeButton(R.string.MakeTweetCancelDialog, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        return builder.create();
    }

    private class PostData extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... tweetMessage){
            String responseString ="";
            Log.d("wolfpack", "I'm posting data!");
            try{
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost("http://twitterproto.herokuapp.com/wolf/tweets");
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
                nameValuePairs.add(new BasicNameValuePair("tweet", tweet));
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                HttpResponse response = httpClient.execute(httppost);
                Log.d("wolfpack", "I posted the tweet!");

            } catch (Exception e){
                e.printStackTrace();
            }
            return responseString;
        }
    }
}