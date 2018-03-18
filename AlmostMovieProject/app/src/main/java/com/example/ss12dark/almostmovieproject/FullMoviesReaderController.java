package com.example.ss12dark.almostmovieproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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


    public FullMoviesReaderController(Activity activity) {
        super(activity);
        context =activity;

    }


    public void getFullMovie(int No) {
        HttpRequest httpRequest = new HttpRequest(this);
        httpRequest.execute("https://api.themoviedb.org/3/movie/"+No+"?api_key=0dfa979f5f5b49d638840ce5b53339c1");
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
                String image = baseImageUrl+poster_path;
                int budget = jsonObject.getInt("budget");
                int runtime = jsonObject.getInt("runtime");
                String release_date = jsonObject.getString("release_date");
                String va = jsonObject.getString("vote_average");
                float vote_average = Float.parseFloat(va);


                FullMovieInfo movie = new FullMovieInfo(uselessInt,name,desc,image,No,vote_average,release_date,budget,runtime);

                MoviePage.setAllDetails(movie);


        }
        catch (JSONException ex) {
            Toast.makeText(activity, "Error: " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }


        progressDialog.dismiss();


    }
}