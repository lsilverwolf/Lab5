package com.mobileproto.lab5;

/**
 * Created by evan on 9/25/13.
 */
public class FollowNotification extends FeedNotification {

    String userFrom;
    String userTo;

    public FollowNotification(String userFrom, String userTo){
        super(userFrom, userTo, userFrom + " is now following you!", "follow");
        this.userFrom = userFrom + " is now following you!";
        this.userTo = userTo;
    }

}
