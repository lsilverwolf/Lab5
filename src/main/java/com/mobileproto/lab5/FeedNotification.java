package com.mobileproto.lab5;

/**
 * Created by evan on 9/25/13.
 */
public class FeedNotification {

    String userFrom;
    String userTo;
    String text;
    String type;

    public FeedNotification(String userFrom, String userTo, String text, String type){
        this.userFrom = userFrom;
        this.userTo = userTo;
        this.text = text;
        this.type = type;
    }
}
