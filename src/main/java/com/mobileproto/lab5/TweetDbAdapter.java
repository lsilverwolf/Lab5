package com.mobileproto.lab5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by rachel on 9/18/13.
 */
public class TweetDbAdapter {

    private static final String DATABASE_NAME = "tweets.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "tweets";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_USERFROM = "userfrom";
    private static final String COLUMN_TWEET = "tweet";
    private static final String COLUMN_USERTO = "userto";
    private static final String COLUMN_TYPE = "type";

    private final Context context;
    private TweetsDbHelper dbHelper;
    private SQLiteDatabase db;

    public TweetDbAdapter(Context context) {
        this.context = context;
    }

    public TweetDbAdapter open(){
        dbHelper = new TweetsDbHelper(context);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }


    public FeedItem createTweet(String userName, String text) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_USERFROM, userName);
        values.put(COLUMN_TWEET, text);
        long id = db.insert(TABLE_NAME, null, values);

        return new FeedItem(id, userName, text);
    }


    public boolean deleteTweet(FeedItem tweet) {
        return db.delete(TABLE_NAME, COLUMN_ID + "=" + tweet.getId(), null) > 0;
    }

    public FeedItem getTweet(long id){
        Cursor cursor = db.query(true, TABLE_NAME, new String[] {COLUMN_ID, COLUMN_USERFROM, COLUMN_TWEET}, COLUMN_ID + "=" + id, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return tweetFromCursor(cursor);

    }

    public Cursor getAllTweets(){
        return db.query(true, TABLE_NAME, new String[] {COLUMN_ID, COLUMN_USERFROM, COLUMN_TWEET}, null, null, null, null, null, null);
    }

    public static FeedItem tweetFromCursor(Cursor cursor){
        return new FeedItem(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(COLUMN_USERFROM)),
                cursor.getString(cursor.getColumnIndex(COLUMN_TWEET)));
    }


    private class TweetsDbHelper extends SQLiteOpenHelper {

        private static final String CREATE_DATABASE = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_USERFROM + " TEXT, " + COLUMN_TWEET + " TEXT)";

        public TweetsDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_DATABASE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }
}
