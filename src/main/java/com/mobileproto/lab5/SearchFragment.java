package com.mobileproto.lab5;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by evan on 9/26/13.
 */
public class SearchFragment extends Fragment {
    String search;
    TweetDbAdapter dbAdapter;
    TweetsListCursorAdapter cursorAdapter;
    TweetListAdapter listAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.search_fragment, null);
        final EditText searchText = (EditText) v.findViewById(R.id.searchField);

        Button searchButton = (Button)v.findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                search = searchText.getText().toString();
                searchText.setText("");
                Log.d("wolfpack", search);

        final AsyncTask<String,String,String> d = new AsyncTask<String, String, String> (){
            protected String doInBackground(String... uri){
                String responseString ="";
                Log.d("wolfpack", "I'm getting data!");
                try{
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpGet httpget = new HttpGet("http://twitterproto.herokuapp.com/tweets?q="+search);
                    Log.d("wolfpack", "http://twitterproto.herokuapp.com/tweets?q="+search);


                    HttpResponse response = httpClient.execute(httpget);
                    Log.d("wolfpack", "I made response");
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    response.getEntity().writeTo(out);
                    out.close();
                    Log.d("wolfpack", "I closed out");
                    responseString = out.toString();
                    List<List<String>> parsedResponse = JSONParse(responseString);
                    Log.d("wolfpack", responseString);
//                dbAdapter.clearData();

                    ListView feedList = (ListView) v.findViewById(R.id.searchResults);
                    Log.d("wolfpack", "I made feedList");
                    listAdapter = new TweetListAdapter(getActivity(), parsedResponse);
                    feedList.setAdapter(listAdapter);
                    Log.d("wolfpack", "soclose");

                    listAdapter.notifyDataSetChanged();
                    Log.d("wolfpack", "done");


                } catch (Exception e){
                    e.printStackTrace();
                }
                return responseString;
            }

//GetData();
        };
                d.execute();
            }
        });

        super.onActivityCreated(savedInstanceState);
        return v;
    }



    public List<List<String>> JSONParse(String responseString) throws JSONException {
        JSONObject obj = new JSONObject(responseString);
        List<List<String>> res = new ArrayList<List<String>>();
        JSONArray array;
        array = obj.getJSONArray("tweets");

        for (int j = 0; j<array.length(); j++){
            List<String> inner = new ArrayList<String>();
            inner.add(array.getJSONObject(j).getString("tweet"));
            inner.add(array.getJSONObject(j).getString("username"));
            inner.add(array.getJSONObject(j).getString("date"));
            inner.add(array.getJSONObject(j).getString("_id"));
            res.add(inner);
        }
        return res;

    }
}
