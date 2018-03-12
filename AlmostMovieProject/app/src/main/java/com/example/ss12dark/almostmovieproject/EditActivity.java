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

import java.net.URL;

public class EditActivity extends AppCompatActivity {

    EditText title;
    EditText description;
    EditText url1;

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        imageView = (ImageView) findViewById(R.id.imageView);
        title = (EditText) findViewById(R.id.name);
        description = (EditText) findViewById(R.id.description);
        url1 = (EditText) findViewById(R.id.url);
    }

    public void ok(View v){
        Intent returnIntent = new Intent();
        String name = title.getText().toString();
        String des = description.getText().toString();
        String url = url1.getText().toString();
        returnIntent.putExtra("name",name);
        returnIntent.putExtra("des",des);
        returnIntent.putExtra("url",url);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }
    public void cancel(View v){
        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_CANCELED, returnIntent);
        finish();
    }
    public void show(View v){
        String url = url1.getText().toString();
        new DownloadImageTask(this, imageView, url).execute();
    }
}
