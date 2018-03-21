package com.example.ss12dark.almostmovieproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;


public class MoviesReaderController extends MovieController {
public Activity contex;

    public List<Movie> giveMovies(){
        return allMoviesData;
    }

    public MoviesReaderController(Activity activity) {
        super(activity);
        contex =activity;
    }


    public void readAllMovies(String name) {
        HttpRequest httpRequest = new HttpRequest(this);
        httpRequest.execute("https://api.themoviedb.org/3/search/movie?api_key=0dfa979f5f5b49d638840ce5b53339c1&query="+name+"&page=1");
    }


    public void onSuccess(String downloadedText) {

        try {
            List<Movie> tempList = new ArrayList<>();
//i tear apart the json to pieaces and get the string i in need for
            JSONObject jsonArray = new JSONObject(downloadedText);

            JSONArray resultArray = jsonArray.getJSONArray("results");



            Movies = new ArrayList<>();


            for (int i = 0; i < resultArray.length(); i++) {


                JSONObject jsonObject = resultArray.getJSONObject(i);
                int No = jsonObject.getInt("id");
                String name = jsonObject.getString("title");
                String desc = jsonObject.getString("overview");
                String image = jsonObject.getString("poster_path");

                Movie movie = new Movie(name,desc,image,No);
                tempList.add(i,movie);

                Movies.add(name);
            }

            allMoviesData = tempList;
            ArrayAdapter<String> adapter = new ArrayAdapter<>(activity, android.R.layout.simple_list_item_1, Movies);


            listViewMovies.setAdapter(adapter);

        }
        catch (JSONException ex) {
//            Toast.makeText(activity, "Error: " + ex.getMessage(), Toast.LENGTH_LONG).show();
            //need for my own use while using json
        }


        progressDialog.dismiss();


    }
}