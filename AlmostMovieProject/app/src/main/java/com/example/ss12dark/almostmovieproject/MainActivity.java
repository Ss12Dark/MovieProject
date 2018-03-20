package com.example.ss12dark.almostmovieproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.graphics.Color;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebHistoryItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    MyDBHandler db;
    int i;
    LinearLayout l;
    boolean next;
    List<Movie> names;
    String tempTitle;

    int watchNum;
    TextView watchView;
    Button watchButton;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        App.setContext(this);
        db = new MyDBHandler(this);
        next = true;
        l = (LinearLayout) findViewById(R.id.linearLayoutMain);
            loadMovie();
    }

    public void deleteall(){
        db.clear();
        restart();
    }

    public void restart(){
        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
        this.finishAffinity();
    }

    public void loadMovie(){
        names =db.getAllMovieList();
        i =0;
            if (names.size() == i) {
                TextView empty = (TextView) findViewById(R.id.showAll);
                empty.setVisibility(View.VISIBLE);
            } else {
                while(i<names.size()) {
                String s = names.get(i).getSubject();
                tempTitle =s;
                String u = names.get(i).getUrl();
                String d = names.get(i).getBody();
                int id =names.get(i).get_id();
                int No = names.get(i).getNo();
                int watched = names.get(i).getWatched();
                makeMovie(s,d,u,id,No,watched);

                    i++;
            }
        }
    }

    public void makeMovie(String name,String description , String url,int id,int No,int watched) {

        ImageView image = new ImageView(this);
        TextView title = new TextView(this);
        TextView des = new TextView(this);
        TextView watchNumberTextView = new TextView(this);
        LinearLayout ll = new LinearLayout(this);
        LinearLayout llinside = new LinearLayout(this);
        LinearLayout llimageInside = new LinearLayout(this);
        final Button goPageButton = new Button(this);
        Button watchButton = new Button(this);

        resizeLinearLayoutimageInside(llimageInside);
        resizeButton(goPageButton);
        resizeLinearLayoutinside(llinside);
        resizeTextDes(des);
        resizeLinearLayout(ll);
        resizeImageView(image);
        resizeTextView(title);

        watchButton.setTag(id);
        setAddWatch(watchButton,watched,watchNumberTextView);
        addPicture(image, url);

        image.setTag(name);
        goPageButton.setTag(No);

        goPageButton.setText(R.string.moviepage);
        goPageButton.setTextSize(13);
        title.setText(name);
        des.setText(description);

        final CustomDialogClass cdd = new CustomDialogClass(MainActivity.this, name, id);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToEdit(view);
            }
        });
        image.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                cdd.show();
                return false;
            }
        });

        goPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int No = (int)goPageButton.getTag();
                goToMoviePage(No);
            }
        });

        llinside.addView(title);
        llinside.addView(des);
        if(No!=0) {
            llinside.addView(goPageButton);
        }
        llimageInside.addView(image);
        llimageInside.addView(watchNumberTextView);
        llimageInside.addView(watchButton);

        ll.addView(llimageInside);
        ll.addView(llinside);

        l.addView(ll);
    }

    public void resizeButton(Button sv){
        LinearLayout.LayoutParams positionRules = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        sv.setLayoutParams(positionRules);
        positionRules.setMargins(10, 10, 10, 10);
    }

    public void resizeLinearLayoutimageInside(LinearLayout llimageInside){
        LinearLayout.LayoutParams positionRules = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        llimageInside.setLayoutParams(positionRules);
        llimageInside.setGravity(Gravity.CENTER);
        llimageInside.setOrientation(LinearLayout.VERTICAL);
    }

    public void resizeLinearLayoutinside(LinearLayout llinside){
        LinearLayout.LayoutParams positionRules = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        llinside.setLayoutParams(positionRules);
        llinside.getLayoutParams().height = 950;
        positionRules.setMargins(25,0, 0, 5);
        llinside.setOrientation(LinearLayout.VERTICAL);
    }

    public void resizeTextDes(TextView des){
        LinearLayout.LayoutParams positionRules = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        des.setLayoutParams(positionRules);
        des.setTextColor(Color.WHITE);
        des.setTextSize(13);
        positionRules.setMargins(5,5, 5, 0);
        des.getLayoutParams().height = 425;
    }

    public void resizeLinearLayout(LinearLayout ll){
        LinearLayout.LayoutParams positionRules = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        ll.setLayoutParams(positionRules);
        positionRules.setMargins(25, 25, 25, 25);
        ll.setLayoutParams(positionRules);
        ll.getLayoutParams().height = 900;
        ll.setBackgroundResource(R.drawable.layoutstyle);
    }

    public void resizeTextView(TextView b){
        LinearLayout.LayoutParams positionRules = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        b.setLayoutParams(positionRules);
        b.setTextColor(Color.WHITE);
        b.setTextSize(25);
        positionRules.setMargins(15,0, 15, 15);
    }

    public void resizeTextViewWatchNumber(TextView b){
        LinearLayout.LayoutParams positionRules = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        b.setLayoutParams(positionRules);
        b.setTextSize(25);
        b.setTextColor(Color.WHITE);

    }

    public void resizeWatchButton(Button sv){
        LinearLayout.LayoutParams positionRules = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        sv.setLayoutParams(positionRules);
        sv.getLayoutParams().height = 80;
        sv.getLayoutParams().width = 80;
        sv.setBackground(getDrawable(R.drawable.eye));
    }

    public void resizeImageView(ImageView b){
        LinearLayout.LayoutParams positionRules = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        b.setLayoutParams(positionRules);
        positionRules.setMargins(15, 15, 25, 0);
        b.getLayoutParams().height = 710;
        b.getLayoutParams().width = 400;
    }

    public void setAddWatch(final Button b ,int watched,final TextView watchNumberTextView){

        resizeTextViewWatchNumber(watchNumberTextView);
        resizeWatchButton(b);
        if(watched!=0){
            b.setBackground(getDrawable(R.drawable.eyeopen));
        }
        String watchString = watched+"";
        watchNumberTextView.setText(watchString);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = Integer.parseInt(view.getTag().toString());
                addWatch(b,watchNumberTextView,id);

            }
        });

        b.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                int id = Integer.parseInt(view.getTag().toString());
                resetWatch(b,watchNumberTextView,id);
                return false;
            }
        });

    }

    public void addWatch(Button b,TextView watchNumberTextView,int id){
        int watched=0;
        for(int p=0;p<names.size();p++){
            if(id==names.get(p).get_id()){
                names.get(p).setWatched(names.get(p).getWatched()+1);
                watched = names.get(p).getWatched();
                db.updateWatch(id);
            }
        }
        if(watched>0){
            b.setBackground(getDrawable(R.drawable.eyeopen));
        }
        String watch = watched+"";
        watchNumberTextView.setText(watch);
    }

    public void resetWatch(Button b,TextView watchNumberTextView,int id){
        int watched=0;
        for(int p=0;p<names.size();p++){
            if(id==names.get(p).get_id()){
                names.get(p).setWatched(0);
                watched = names.get(p).getWatched();
                db.updateWatch(id);
            }
        }
        b.setBackground(getDrawable(R.drawable.eye));
        String watch = watched+"";
        watchNumberTextView.setText(watch);
    }

    public void addPicture(ImageView b,String u) {
        if (u.equals("")) {
            b.setBackgroundResource(R.drawable.nopic);
            b.getBackground().setAlpha(150);
        } else {
                                 new DownloadImageTask(this,l,this, b, u).execute();
        }
    }

    public void goToMoviePage(int No){
        Intent moviePage = new Intent(this,MoviePage.class);
        moviePage.putExtra("No",No);
        moviePage.putExtra("switch",0);
        startActivity(moviePage);
    }

    public void goToEdit(View v){
        String movieTitle = v.getTag().toString();

        for(i=0;i<names.size();i++){
            if(movieTitle.equals(names.get(i).getSubject())){
                String title =names.get(i).getSubject();
                String des =names.get(i).getBody();
                String url =names.get(i).getUrl();
                int id = names.get(i).get_id();

                Intent editActivity = new Intent(this,EditActivity.class);
                editActivity.putExtra("name",title);
                editActivity.putExtra("des",des);
                editActivity.putExtra("url",url);
                editActivity.putExtra("id",id);
                this.startActivityForResult(editActivity,1);

            }
        }
    }

    public void random(View v){
        Intent moviePage = new Intent(this,MoviePage.class);
        Random rand = new Random();
        App.setmView(v);
        int  n = rand.nextInt(68718) + 50;
        moviePage.putExtra("No",n);
        moviePage.putExtra("switch",1);
        startActivity(moviePage);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);


        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        int itemId = item.getItemId();

        switch (itemId) {

            case R.id.menuItemSettings:

                return true;
            case R.id.addInternet:
                Intent net = new Intent(this,AddFromInternet.class);
                startActivity(net);
                return true;
            case R.id.addManuall:
                Intent add = new Intent(this,EditActivity.class);
                add.putExtra("id",-1);
                startActivityForResult(add,1);
                return true;
            case R.id.exit:
                finish();
                return true;
            case R.id.deleteAll:
                deleteall();
                return true;

        }

        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                int No=data.getIntExtra("No",0);
                String name=data.getStringExtra("name");
                String des=data.getStringExtra("des");
                String url1=data.getStringExtra("url");

                if(des.equals("")) {
                    if ( url1.equals("")) {
                        Movie movie = new Movie(name,"","",No);
                        movie.setWatched(0);
                        db.addMovie(movie);
                    } else {
                        Movie movie = new Movie(name, "", url1,No);
                        movie.setWatched(0);
                        db.addMovie(movie);
                    }
                }else if(url1.equals("")){
                        Movie movie = new Movie(name,des,"",No);
                    movie.setWatched(0);
                    db.addMovie(movie);
                }else{
                    Movie movie = new Movie(name,des,url1,No);
                    movie.setWatched(0);
                    db.addMovie(movie);
                }


                this.recreate();



            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }
}