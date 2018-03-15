package com.example.ss12dark.almostmovieproject;

import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;


public class MoviesReaderController extends MovieController {


    public MoviesReaderController(Activity activity) {
        super(activity);
    }


    public void readAllMovies() {
        HttpRequest httpRequest = new HttpRequest(this);
        httpRequest.execute("https://restcountries.eu/rest/v2/all?fields=name");
    }


    public void onSuccess(String downloadedText) {

        try {


            JSONArray jsonArray = new JSONArray(downloadedText);


            Movies = new ArrayList<>();


            for (int i = 0; i < jsonArray.length(); i++) {


                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String name = jsonObject.getString("name");
                String movie =name;


                Movies.add(movie);
            }


            ArrayAdapter<String> adapter = new ArrayAdapter<>(activity, android.R.layout.simple_list_item_1, Movies);


            listViewMovies.setAdapter(adapter);
        }
        catch (JSONException ex) {
            Toast.makeText(activity, "Error: " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }


        progressDialog.dismiss();
    }
}