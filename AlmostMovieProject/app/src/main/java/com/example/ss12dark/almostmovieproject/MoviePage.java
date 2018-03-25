package com.example.ss12dark.almostmovieproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.ShareActionProvider;
import android.widget.TextView;

import java.io.Serializable;

public class MoviePage extends AppCompatActivity{
// i use so many statics so i could use it on a static method and declare it on another class while this class is an activity
    public FullMoviesReaderController fullMoviesReaderController;
    static LinearLayout l;
    static LinearLayout movieLinear;
    static TextView head;
    static TextView description;
    static ImageView image;
    static TextView vote;
    static RatingBar rate;
    static TextView date;
    static TextView budget;
    static TextView runtime;
    static Activity activity;
    static Context context;

    static String movieName;
    static String movieScore;
    int aSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_page);
        movieLinear=(LinearLayout) findViewById(R.id.fullMovie);
        fullMoviesReaderController = new FullMoviesReaderController(this);
        Intent i = getIntent();
        int No =i.getIntExtra("No",0);//gets the NumberOrder of the movie so i can send it in the api
        aSwitch =i.getIntExtra("switch",0);//this variable is to check if its random movie or existed movie and change the code.
        fullMoviesReaderController.getFullMovie(No,aSwitch);
        l = (LinearLayout) findViewById(R.id.l);
        head = (TextView) findViewById(R.id.title);
        description = (TextView) findViewById(R.id.description);
        image = (ImageView) findViewById(R.id.imageView);
        vote = (TextView) findViewById(R.id.vote_average);
        rate = (RatingBar) findViewById(R.id.MyRating);
        date = (TextView) findViewById(R.id.release_date);
        budget = (TextView) findViewById(R.id.budget);
        runtime = (TextView) findViewById(R.id.runtime);
        activity = this;//so i will be able to get the resources and strings
        context = this;


    }

    @Override
    protected void onPause() {
        super.onPause();
        if(aSwitch==1) {
            finish();
        }
    }
//---------this method are used in another class and checks if there is empty places in the details
    public static void setAllDetails(FullMovieInfo movie){


        head.setText(movie.getSubject());
        description.setText(movie.getBody());

        if (movie.getUrl().equals("")) {
            image.setBackgroundResource(R.drawable.nopic);
            image.getBackground().setAlpha(150);
        } else {
            new DownloadImageTask(activity,l,context, image, movie.getUrl()).execute();
        }

        String voteText;
        if(0==movie.getVote_average()){//check if there are an available score
            rate.setVisibility(View.GONE);
            vote.setVisibility(View.VISIBLE);
            voteText = activity.getString(R.string.noinfomration2);
        }else {
            voteText = movie.getVote_average()+"";
            if((float)6.5<=movie.getVote_average()){//check if the score is high or low and paint the color red\green in result
                movieLinear.setBackgroundResource(R.drawable.layoutstylegreen);
            }else{
                movieLinear.setBackgroundResource(R.drawable.layoutstylered);
            }
        }
//-----------------all string is from the strings values so i could change language
        vote.setText(activity.getString(R.string.score)+" "+voteText);
        rate.setNumStars(5);
        rate.setMax(5);
        float rating = (float) 0.5*movie.getVote_average();
        rate.setStepSize((float)0.05);
        rate.setRating(rating);
        date.setText(activity.getString(R.string.releasedate)+" "+movie.getRelease_date());
        String money;
        if(0==movie.getBudget()){
            money = activity.getString(R.string.noinformation);
        }else {
            money = movie.getBudget() + "";
        }
        budget.setText(activity.getString(R.string.budget)+" "+money);
        if(0!=movie.getRuntime()) {
            int hours = movie.getRuntime() / 60;
            int minutes = movie.getRuntime() % 60;
            runtime.setText(activity.getString(R.string.movielenght)+" " + hours+" " + activity.getString(R.string.movielength2)+" " + minutes+" " + activity.getString(R.string.movielength3)+" ");
        }else{
            runtime.setText(R.string.noinformationexisted);
        }

        movieName = movie.getSubject().toString();
        movieScore = movie.getVote_average()+"";
    }

    public void share(View v){
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,
                getString(R.string.share1)+" "+movieName+" "+getString(R.string.share2)+" "+movieScore+"!!!");//default messege in the share option
        sendIntent.setType("text/plain");
        startActivity(sendIntent.createChooser(sendIntent, getResources().getText(R.string.app_name)));
    }
}
