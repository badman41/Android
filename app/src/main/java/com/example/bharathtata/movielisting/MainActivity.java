package com.example.bharathtata.movielisting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private Adapter mAdapter;
    private RequestQueue mRequestQueue;
    private ArrayList<movie_Items> mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
            return;
        }
        if (AppStatus.getInstance(getApplicationContext()).isOnline()) {

            mRecyclerView = findViewById(R.id.recycle_view);
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            mList = new ArrayList<>();
            mRequestQueue = Volley.newRequestQueue(this);
            parseJSON();
        } else {
            startActivity(new Intent(getApplicationContext(), Main2Activity.class));
        }
    }
    private void parseJSON() {
        String url ="use the api from themoviedb(tmdb) to fetch json data from it";
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray=response.getJSONArray("results");
                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject jo = jsonArray.getJSONObject(i);
                                String imageUrl= "https://image.tmdb.org/t/p/w500" + jo.getString("poster_path");
                                String title= jo.getString("title");
                                String releaseDate = jo.getString("release_date");
                                double rating = jo.getDouble("vote_average");
                                mList.add(new movie_Items(imageUrl,title,releaseDate,rating));
                            }
                            mAdapter=new Adapter(MainActivity.this,mList);
                            mRecyclerView.setAdapter(mAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
          error.printStackTrace();
            }
        });
        mRequestQueue.add(request);
    }
    public void onBackPressed(){
        finish();
    }
}
