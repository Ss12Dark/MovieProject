package com.example.ss12dark.almostmovieproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.Serializable;

public class MoviePage extends AppCompatActivity implements Serializable {
    public FullMoviesReaderController fullMoviesReaderController;

    static LinearLayout l;
    static TextView head;
    static TextView description;
    static ImageView image;
    static TextView vote;
    static TextView date;
    static TextView budget;
    static TextView runtime;
    static Activity activity;
    static Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_page);
        fullMoviesReaderController = new FullMoviesReaderController(this);
        Intent i = getIntent();
        int No =i.getIntExtra("No",0);
        fullMoviesReaderController.getFullMovie(No);
        l = (LinearLayout) findViewById(R.id.l);
        head = (TextView) findViewById(R.id.title);
        description = (TextView) findViewById(R.id.description);
        image = (ImageView) findViewById(R.id.imageView);
        vote = (TextView) findViewById(R.id.vote_average);
        date = (TextView) findViewById(R.id.release_date);
        budget = (TextView) findViewById(R.id.budget);
        runtime = (TextView) findViewById(R.id.runtime);
        activity = this;
        context = this;
    }

    public static void setAllDetails(FullMovieInfo movie){


        head.setText(movie.getSubject());
        description.setText(movie.getBody());
        if (movie.getUrl().equals("")) {
            image.setBackgroundResource(R.drawable.nopic);
            image.getBackground().setAlpha(150);
        } else {
            new DownloadImageTask(activity,l,context, image, movie.getUrl()).execute();
        }
        String voteText = movie.getVote_average()+"";
        vote.setText("Score: "+voteText);
        date.setText("Release Date: "+movie.getRelease_date());
        String money = movie.getBudget()+"";
        budget.setText("Budget: "+money);
        int hours = movie.getRuntime()/60;
        int minutes = movie.getRuntime()%60;
        runtime.setText("Movie length: "+hours+" hours and "+minutes+" minutes");

    }
}
