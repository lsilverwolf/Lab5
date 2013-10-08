package com.mobileproto.lab5;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by wolflyra on 10/2/13.
 */
public class TweetListAdapter extends ArrayAdapter<List<String>> {
    private List<List<String>> data;

    private Activity activity;

    public TweetListAdapter(Activity a, List<List<String>> data){

        super(a, R.layout.feed_item, data);
        Log.d("wolfpack", data.toString());
//        super(a, data);
        this.data = data;
        this.activity = a;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        View v = convertView;
        if (v==null){
            LayoutInflater vi = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.feed_item, parent, false);
        }

        final TextView name = (TextView) v.findViewById(R.id.feedItemUser);
        name.setText(data.get(position).get(1));
        Log.d("wolfpack", data.get(position).get(1));
        final TextView tweet = (TextView) v.findViewById(R.id.feedText);
        Log.d("wolfpack", data.get(position).get(0));
        tweet.setText(data.get(position).get(0));



        return v;
    }
}
