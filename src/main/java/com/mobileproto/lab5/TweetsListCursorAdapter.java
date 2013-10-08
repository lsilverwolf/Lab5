
package com.mobileproto.lab5;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by wolflyra on 10/2/13.
 */

public class TweetsListCursorAdapter extends CursorAdapter {
    private final LayoutInflater inflater;
    private final TweetDbAdapter dbAdapter;

    public TweetsListCursorAdapter(Context context, Cursor c, TweetDbAdapter dbAdapter) {
        super(context, c, true);
        inflater= LayoutInflater.from(context);
        this.dbAdapter = dbAdapter;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return inflater.inflate(R.layout.feed_item, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        final ViewHolder holder;
        holder = new ViewHolder(TweetDbAdapter.tweetFromCursor(cursor), (TextView) view.findViewById(R.id.feedItemUser),
                (TextView) view.findViewById(R.id.feedText));
        holder.titleTextView.setText(holder.tweet.getName());
        holder.tweetTextView.setText(holder.tweet.getText());
        view.setTag(holder);

    }


    public class ViewHolder{
        FeedItem tweet;
        TextView titleTextView;
        TextView tweetTextView;

        private ViewHolder(FeedItem tweet, TextView titleTextView, TextView tweetTextView) {
            this.tweet = tweet;
            this.titleTextView = titleTextView;
            this.tweetTextView = tweetTextView;
        }
    }
}

