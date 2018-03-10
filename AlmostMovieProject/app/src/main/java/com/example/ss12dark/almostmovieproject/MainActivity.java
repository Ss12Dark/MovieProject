package com.example.ss12dark.almostmovieproject;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    MyDBHandler dbHandler;
    int i;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHandler = new MyDBHandler(this, null, null, 1);
        boolean next = true;

        while(next){

            next =loadMovie();

        }
    }

    public boolean loadMovie(){
        LinearLayout l = (LinearLayout) findViewById(R.id.linearLayoutMain);
        Button b = new Button(this);

        List names =dbHandler.databaseToList();
        i =0;

        if(names.size()==i){
            TextView empty = (TextView) findViewById(R.id.showAll);
            empty.setVisibility(View.VISIBLE);
            return false;
        }else{

            String s =names.get(i).toString();
            LinearLayout.LayoutParams positionRules = new LinearLayout.LayoutParams( LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            b.setLayoutParams(positionRules);
            b.setText(s);
            i++;
            l.addView(b);
            return true;
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);

        // Need to find menu items only here because they belong to the menu and not to the main layout.
        // Also, they are not created yet on the onCreate event:

        return true;
    }

    // Return true to state that the menu event has been handled.
    // Return false to state that the menu event has not been handled.
    public boolean onOptionsItemSelected(MenuItem item) {

        int itemId = item.getItemId();

        switch (itemId) {

            case R.id.menuItemSettings:

                return true;
            case R.id.addInternet:

                return true;
            case R.id.addManuall:
                Intent add = new Intent(this,EditActivity.class);
                startActivityForResult(add,1);
                return true;
            case R.id.exit:
                finish();
                return true;
            case R.id.deleteAll:

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
                        Movie movie = new Movie(name);
                        dbHandler.addMovie(movie);
                    } else {
                        Movie movie = new Movie(name, null, url1);
                        dbHandler.addMovie(movie);
                    }
                }else if(url1.equals("")){
                        Movie movie = new Movie(name,des,null);
                        dbHandler.addMovie(movie);
                }else{
                    Movie movie = new Movie(name,des,url1);
                    dbHandler.addMovie(movie);
                }

                this.recreate();



            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }
}