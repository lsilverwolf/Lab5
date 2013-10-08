package com.mobileproto.lab5;

/**
 * Created by evan on 9/25/13.
 */
public class FeedItem {

    public long id;
    public String text;
    public String userName;
    public long time;

    public FeedItem(long id, String userName, String text, long time){
        this.id = id;
        this.userName = userName;
        this.text = text;
        this.time = time;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return userName;
    }

    public String getText() {
        return text;
    }

    public long getTime() {
        return time;
    }

    public void setName(String name) {
        this.userName = name;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getContents() {
        return text;
    }

    @Override
    public String toString() {
        return "FeedItem{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", userName='" + userName + '\'' +
                ", time=" + time +
                '}';
    }
}
