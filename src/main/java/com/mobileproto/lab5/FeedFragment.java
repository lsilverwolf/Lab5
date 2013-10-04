package com.mobileproto.lab5;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by evan on 9/25/13.
 */
public class FeedFragment extends Fragment {


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.feed_fragment, null);
        final View vi = inflater.inflate(R.layout.feed_item, null);

         /*
         * Creating some sample test data to see what the layout looks like.
         * You should eventually delete this.
         */

        class URLConnectionReader {
            public void main(String[] args) throws Exception {
                URL yahoo = new URL("http://www.yahoo.com/");
                URLConnection yc = yahoo.openConnection();
                Log.d("Lyra", "I opened a connection to the site!");
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(
                                yc.getInputStream()));
                String inputLine;

                while ((inputLine = in.readLine()) != null)
                    System.out.println(inputLine);
                in.close();
            }
        }

        FeedItem item1 = new FeedItem(0,"@TimRyan", "Dear reader, you are reading.");
        FeedItem item2 = new FeedItem(1,"@EvanSimpson", "Hey @TimRyan");
        FeedItem item3 = new FeedItem(2,"@JulianaNazare", "Everything happens so much.");
        FeedItem item4 = new FeedItem(3,"@reyner", "dGhlIGNvb2wgbmV3IHRoaW5nIHRvIGRvIGlzIGJhc2U2NCBlY29kZSB5b3VyIHR3ZWV0cw==");
        List<FeedItem> sampleData = new ArrayList<FeedItem>();
        sampleData.add(item1);
        sampleData.add(item2);
        sampleData.add(item3);
        sampleData.add(item4);

        // Set up the ArrayAdapter for the feedList
        FeedListAdapter feedListAdapter = new FeedListAdapter(this.getActivity(), sampleData);
        ListView feedList = (ListView) v.findViewById(R.id.feedList);
        feedList.setAdapter(feedListAdapter);


        return v;

    }


}
