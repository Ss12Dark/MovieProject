package com.example.ss12dark.almostmovieproject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.net.URL;
import java.util.List;

public class EditActivity extends AppCompatActivity {

    EditText title;
    EditText description;
    EditText url1;
    LinearLayout l;
    ImageView imageView;
    int ID;
    int check;
    MyDBHandler db;
    int No=0;//default api ID(NumberOrder-No) so if i send here an already movie with api and he changed i wont give hime the chance to get movie page...example:
    //if i got the movie Spider-Man and he got movie page cuz' he was impoted from the net, and now someone edit his name to "Bob the Builder", in code
    //he will still have the Spider-Man movie page cuz'of the api id he got . so i reset it after an edit(also for th non impoted movies)


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        //get all the views and database
        imageView = (ImageView) findViewById(R.id.imageView);
        title = (EditText) findViewById(R.id.name);
        description = (EditText) findViewById(R.id.description);
        url1 = (EditText) findViewById(R.id.url);
        l = (LinearLayout) findViewById(R.id.outside);
        db = new MyDBHandler(this);
        Intent A = getIntent();
        Bundle b = A.getExtras();
        if(b!=null)//here i check if there any data to collect
        {

            String NAME =(String) b.get("name");
            String d =(String) b.get("des");
            String u =(String) b.get("url");
            int id = (int) b.get("id");//i get the "id" that i made to know wich movie to delete after the movie has been edited
            ID=id;

            if(null!=b.get("No")){//here i check if i edit/add movie from the internet(if hes from the internet he got api ID)
                No = (int) b.get("No");
            }

            if(NAME==null){//this is a check if i got name from the mainActivity or from addFromInternet activity
                check=0;
                NAME=(String) b.get("title");
            }else{
                check=-1;
            }

//place the data in the editText boxes
            title.setText(NAME);
            description.setText(d);
            url1.setText(u);

        }
    }

    public void ok(View v){
        MyDBHandler db = new MyDBHandler(this);
        if(check==-1){//i use the id to delete the previous movie that has been edited
            db.deleteMovie(ID);
        }
        Intent returnIntent = new Intent();
        String name = title.getText().toString();
        List<Movie> m =db.getAllMovieList();
        boolean nameExist = true;
        for(int i=0;i<m.size();i++){
            if(name.equals(m.get(i).getSubject())) {
                nameExist = false;
            }
        }
        if(nameExist) {
            String des = description.getText().toString();
            String url = url1.getText().toString();
            if (name.equals("")) {
                Toast.makeText(this, R.string.movietitleedit, Toast.LENGTH_SHORT).show();
            } else {
                returnIntent.putExtra("No", No);
                returnIntent.putExtra("name", name);
                returnIntent.putExtra("des", des);
                returnIntent.putExtra("url", url);
                setResult(Activity.RESULT_OK, returnIntent);
                No=0;
                finish();
            }
        }else{
            Toast.makeText(this, R.string.movieexsited,Toast.LENGTH_SHORT).show();
        }


    }
    public void cancel(View v){//in case of a cancel
        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_CANCELED, returnIntent);
        finish();
    }
    public void show(View v){//image download class in show button

        String url = url1.getText().toString();
        new DownloadImageTask(this,l,this, imageView, url).execute();
    }
}
