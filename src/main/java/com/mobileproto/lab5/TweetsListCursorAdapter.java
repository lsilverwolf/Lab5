/*
package com.mobileproto.lab5;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Currency;

*/
/**
 * Created by wolflyra on 10/2/13.
 *//*

public class TweetsListCursorAdapter extends CursorAdapter {
    private final LayoutInflater inflater;
    private final TweetDbAdapter dbAdapter;

    public TweetsListCursorAdapter(Context context, Cursor c, TweetDbAdapter dbAdapter) {
        super(context, c, true);
        inflater= LayoutInflater.from(context);
        this.dbAdapter = dbAdapter;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return inflater.inflate(R.layout.feed_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        final ViewHolder holder;
        holder = new ViewHolder(TweetDbAdapter.tweetFromCursor(cursor), (TextView) view.findViewById(R.id.feedItemUser));
        view.setTag(holder);

        holder.titleTextView.setText(holder.FeedItem.getName());

    }

    public class ViewHolder{
        Tweet tweet;
        TextView titleTextView;
        ImageButton deleteButton;

        private ViewHolder(Tweet tweet, TextView titleTextView) {
            this.tweet = tweet;
            this.titleTextView = titleTextView;
        }
    }
}
*/
