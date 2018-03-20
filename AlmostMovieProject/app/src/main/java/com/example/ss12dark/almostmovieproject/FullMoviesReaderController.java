package com.example.ss12dark.almostmovieproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class FullMoviesReaderController extends FullMovieController implements Serializable {
    public Activity context;
    public int aSwitch;

    public FullMoviesReaderController(Activity activity) {
        super(activity);
        context = activity;

    }


    public void getFullMovie(int No, int num) {
        aSwitch = num;
        HttpRequest httpRequest = new HttpRequest(this);
        httpRequest.execute("https://api.themoviedb.org/3/movie/" + No + "?api_key=0dfa979f5f5b49d638840ce5b53339c1");
    }


    public void onSuccess(String downloadedText) {

        try {


            JSONObject jsonObject = new JSONObject(downloadedText);


            int uselessInt = 0;
            int No = jsonObject.getInt("id");
            String name = jsonObject.getString("title");
            String desc = jsonObject.getString("overview");
            String poster_path = jsonObject.getString("poster_path");
            String baseImageUrl = "http://image.tmdb.org/t/p/w185";
            String image = baseImageUrl + poster_path;
            int budget = jsonObject.getInt("budget");
            int runtime;
            try {
                runtime = jsonObject.getInt("runtime");
            }catch (Exception eee) {
                runtime = 0;
            }
            String release_date = jsonObject.getString("release_date");
            String va = jsonObject.getString("vote_average");
            float vote_average = Float.parseFloat(va);


            FullMovieInfo movie = new FullMovieInfo(uselessInt, name, desc, image, No, vote_average, release_date, budget, runtime);

            MoviePage.setAllDetails(movie);


        } catch (JSONException ex) {
            if (aSwitch == 1) {
                MainActivity mContext = (MainActivity) App.getContext();
                View v = App.getmView();
                mContext.random(v);
            }
            Toast.makeText(activity, "Give me a second", Toast.LENGTH_LONG).show();
        }


        progressDialog.dismiss();


    }
}