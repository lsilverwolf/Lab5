package com.mobileproto.lab5;

/**
 * Created by evan on 9/25/13.
 */
public class FeedItem {

    public long id;
    public String text;
    public String userName;

    public FeedItem(long id, String userName, String text){
        this.id = id;
        this.userName = userName;
        this.text = text;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return userName;
    }

    public void setName(String name) {
        this.userName = name;
    }

    public String getContents() {
        return text;
    }

}
