package com.example.ss12dark.almostmovieproject;

import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

// Class for reading server data:
public class MoviesReaderController extends MovieController {

    // ctor:
    public MoviesReaderController(Activity activity) {
        super(activity);
    }

    // Read all countries from the server:
    public void readAllCountries() {
        HttpRequest httpRequest = new HttpRequest(this);
        httpRequest.execute("https://restcountries.eu/rest/v2/all?fields=name");
    }

    // Got all candies from the server - update all in the ListView:
    public void onSuccess(String downloadedText) {

        try {

            // Translate all to a JSON array:
            JSONArray jsonArray = new JSONArray(downloadedText);

            // Create a new array list to hold all candies:
            countries = new ArrayList<>();

            // Run on all JSON objects:
            for (int i = 0; i < jsonArray.length(); i++) {

                // Convert each candy from a JSON object into a Candy object:
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String name = jsonObject.getString("name");
                String country =name;

                // Add the candy object into the candies array:
                countries.add(country);
            }

            // Set adapter for the ListView:
            ArrayAdapter<String> adapter = new ArrayAdapter<>(activity, android.R.layout.simple_list_item_1, countries);

            // Display all:
            listViewCountries.setAdapter(adapter);
        }
        catch (JSONException ex) {
            Toast.makeText(activity, "Error: " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }

        // Dismiss dialog:
        progressDialog.dismiss();
    }
}