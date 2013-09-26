package com.mobileproto.lab5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by evan on 9/26/13.
 */
public class ConnectionListAdapter extends ArrayAdapter<FeedNotification> {

    private final Context context;
    private final List<FeedNotification> data;

    public ConnectionListAdapter(Context context, List<FeedNotification> data){
        super(context, R.layout.connection_item, data);
        this.context = context;
        this.data = data;
    }

    private class ConnectionItemHolder{
        TextView userName;
        TextView text;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ConnectionItemHolder holder;
        View connectionRow = convertView;

        if(connectionRow == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            connectionRow = inflater.inflate(R.layout.connection_item, parent, false);
            holder = new ConnectionItemHolder();
            holder.userName = (TextView) connectionRow.findViewById(R.id.connectionItemUser);
            holder.text = (TextView) connectionRow.findViewById(R.id.connectionText);

            connectionRow.setTag(holder);
        } else {
            holder = (ConnectionItemHolder) connectionRow.getTag();
        }

        FeedNotification item = data.get(position);

        if(item.type.equals("follow")){
            holder.userName.setText(item.text);
            holder.text.setText("");
        }else{
            holder.userName.setText(item.userFrom);
            holder.text.setText(item.text);
        }

        return connectionRow;
    }
}
