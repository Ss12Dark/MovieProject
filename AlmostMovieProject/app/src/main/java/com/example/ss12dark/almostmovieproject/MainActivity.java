package com.example.ss12dark.almostmovieproject;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.graphics.Color;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    MyDBHandler db;
    int i;
    LinearLayout l;
    boolean next;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        List<Movie> names =db.getAllMovieList();
        i =0;
            if (names.size() == i) {
                TextView empty = (TextView) findViewById(R.id.showAll);
                empty.setVisibility(View.VISIBLE);
            } else {
                while(i<names.size()) {
                String s = names.get(i).getSubject();
                String u = names.get(i).getUrl();
                String d = names.get(i).getBody();
                int id =names.get(i).get_id();
                makeMovie(s,d,u,id);

                    i++;
            }
        }
    }

    public void makeMovie(String name,String description , String url,int id){
        ImageView image = new ImageView(this);
        TextView title = new TextView(this);
        TextView des = new TextView(this);
        LinearLayout ll = new LinearLayout(this);
        LinearLayout llinside = new LinearLayout(this);
//        ScrollView scroll = new ScrollView(this);
//        resizeScroll(scroll);
        resizeLinearLayoutinside(llinside);
        resizeTextDes(des);
        resizeLinearLayout(ll);
        resizeImageView(image);
        resizeTextView(title);
        addPicture(image,url);
        title.setText(name);
        des.setText(description);
        final CustomDialogClass cdd=new CustomDialogClass(MainActivity.this,name,id);
        image.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                cdd.show();
                return false;
            }
        });
        llinside.addView(title);
        llinside.addView(des);
//        scroll.addView(llinside);
        ll.addView(image);
        ll.addView(llinside);
        l.addView(ll);
    }
//
//    public void resizeScroll(ScrollView sv){
//        LinearLayout.LayoutParams positionRules = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        sv.setLayoutParams(positionRules);
//        float w =translateDP(190);
//        float h =translateDP(285);
//        float m =translateDP(10);
//        int width=(int)w;
//        int hight =(int)h;
//        int mergin = (int)m;
//        positionRules.setMargins(mergin, mergin, mergin, mergin);
//        sv.setLayoutParams(positionRules);
//        sv.getLayoutParams().height = hight;
//        sv.getLayoutParams().width = width;
//
//    }

    public void resizeLinearLayoutinside(LinearLayout ll){
        LinearLayout.LayoutParams positionRules = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        ll.setLayoutParams(positionRules);
        float w =translateDP(190);
        float h =translateDP(285);
        int width=(int)w;
        int hight =(int)h;
        ll.setLayoutParams(positionRules);
        ll.getLayoutParams().height = hight;
        ll.getLayoutParams().width = width;
        ll.setOrientation(LinearLayout.VERTICAL);


    }

    public void resizeTextDes(TextView des){
        LinearLayout.LayoutParams positionRules = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        des.setLayoutParams(positionRules);
        float m =translateDP(5);
        des.setTextColor(Color.BLACK);
        int mergin = (int)m;
        float TextSize =translateDP(5);
        des.setTextSize(TextSize);
        positionRules.setMargins(mergin,mergin, mergin, mergin);
        des.setLayoutParams(positionRules);
    }

    public  void resizeLinearLayout(LinearLayout ll){
        LinearLayout.LayoutParams positionRules = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        ll.setLayoutParams(positionRules);
        float h =translateDP(320);
        float m =translateDP(15);
        int hight =(int)h;
        int mergin = (int)m;
        positionRules.setMargins(mergin, mergin, mergin, mergin);
        ll.setLayoutParams(positionRules);
        ll.getLayoutParams().height = hight;
        ll.setBackgroundResource(R.drawable.layoutstyle);
    }

    public void resizeTextView(TextView b){
        LinearLayout.LayoutParams positionRules = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        b.setLayoutParams(positionRules);
        float m =translateDP(15);
        b.setTextColor(Color.BLACK);
        int mergin = (int)m;
        float TextSize =translateDP(10);
        b.setTextSize(TextSize);
        positionRules.setMargins(mergin,0, mergin, mergin);
        b.setLayoutParams(positionRules);


    }

    public void resizeImageView(ImageView b){
        LinearLayout.LayoutParams positionRules = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        b.setLayoutParams(positionRules);
        float w =translateDP(160);
        float h =translateDP(285);
        float m =translateDP(15);
        int width=(int)w;
        int hight =(int)h;
        int mergin = (int)m;
        positionRules.setMargins(mergin, mergin, mergin, 0);
        b.setLayoutParams(positionRules);
        b.getLayoutParams().height = hight;
        b.getLayoutParams().width = width;
    }

    public void addPicture(ImageView b,String u) {
        if (u.equals("")) {
            b.setBackgroundResource(R.drawable.nopic);
            b.getBackground().setAlpha(150);
        } else {


//            try {
//                try {
//                    try {
//                        try {
                                 new DownloadImageTask(this,l,this, b, u).execute();

//                        } catch (URISyntaxException use) {
//                            Toast.makeText(this, "FATAL error with Syntax", Toast.LENGTH_SHORT).show();
//                            b.setBackgroundResource(R.drawable.nopic);
//                            b.getBackground().setAlpha(150);
//                        }
//                    } catch (MalformedURLException mue) {
//                        Toast.makeText(this, "FATAL error with image URL", Toast.LENGTH_SHORT).show();
//                        b.setBackgroundResource(R.drawable.nopic);
//                        b.getBackground().setAlpha(150);
//                    }
//                } catch (IOException ioe) {
//                    Toast.makeText(this, "FATAL error with image IO", Toast.LENGTH_SHORT).show();
//                    b.setBackgroundResource(R.drawable.nopic);
//                    b.getBackground().setAlpha(150);
//                }
//            }catch(Exception e){
//                Toast.makeText(this, "FATAL error with image loading", Toast.LENGTH_SHORT).show();
//                b.setBackgroundResource(R.drawable.nopic);
//                b.getBackground().setAlpha(150);
//            }
        }
    }

    public float translateDP(float num){
        // Get device's display metrics:
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();

        // Convert 10dp to pixels:
        float pixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, num, displayMetrics);
        return pixels;
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

                String name=data.getStringExtra("name");
                String des=data.getStringExtra("des");
                String url1=data.getStringExtra("url");

                if(des.equals("")) {
                    if ( url1.equals("")) {
                        Movie movie = new Movie(name,"","");
                        db.addMovie(movie);
                    } else {
                        Movie movie = new Movie(name, "", url1);
                        db.addMovie(movie);
                    }
                }else if(url1.equals("")){
                        Movie movie = new Movie(name,des,"");
                    db.addMovie(movie);
                }else{
                    Movie movie = new Movie(name,des,url1);
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