package com.example.ss12dark.almostmovieproject;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {

    EditText title;
    EditText description;
    EditText URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        title = (EditText) findViewById(R.id.name);
        description = (EditText) findViewById(R.id.description);
        URL = (EditText) findViewById(R.id.url);
    }

    public void ok(View v){
        Intent returnIntent = new Intent();
        String name = title.getText().toString();
        String des = description.getText().toString();
        String url = URL.getText().toString();
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

    }
}
