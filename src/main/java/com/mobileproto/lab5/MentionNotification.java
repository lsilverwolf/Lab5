package com.mobileproto.lab5;

/**
 * Created by evan on 9/25/13.
 */
public class MentionNotification extends FeedNotification {

    String userFrom;
    String userTo;
    String text;

    public MentionNotification(String userFrom, String userTo, String text){
        super(userFrom, userTo, text, "mention");
        this.userFrom = userFrom;
        this.userTo = userTo;
        this.text = text;
    }
}
