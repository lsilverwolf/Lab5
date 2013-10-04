package com.mobileproto.lab5;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

/**
 * Created by wolflyra on 10/2/13.
 */
public class TweetListAdapter extends ArrayAdapter {
    private List<String> data;
    private Activity activity;

    public TweetListAdapter(Activity a, int viewResourceId, List<String> data){
        super(a, viewResourceId, data);
        this.data = data;
        this.activity = a;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        View v = convertView;
        if (v==null){
            LayoutInflater vi = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.feed_item, null);
        }

        final TextView name = (TextView) v.findViewById(R.id.feedItemUser);
        name.setText(data.get(position));



        return v;
    }
}
