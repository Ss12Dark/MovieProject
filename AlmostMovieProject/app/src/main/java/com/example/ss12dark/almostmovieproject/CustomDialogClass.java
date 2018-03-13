package com.example.ss12dark.almostmovieproject;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import java.util.List;

/**
 * Created by Android on 13/03/2018.
 */
public class CustomDialogClass extends Dialog implements
        android.view.View.OnClickListener {

    public Activity c;
    public Dialog d;
    public Button delete, edit;
    public int id;
    MyDBHandler db;
    List<Movie> names;

    public CustomDialogClass(Activity a,int ID) {
        super(a);
        id=ID;
        this.c = a;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.edit_dialog);
        delete = (Button) findViewById(R.id.btn_delete);
        edit = (Button) findViewById(R.id.btn_edit);
        delete.setOnClickListener(this);
        edit.setOnClickListener(this);
        db= new MyDBHandler(c);
        names=db.getAllMovieList();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_delete:
                db.deleteMovie(id);
                c.recreate();
                break;
            case R.id.btn_edit:
                String title =names.get(id-1).getSubject();
                String des =names.get(id-1).getBody();
                String url =names.get(id-1).getUrl();

                Intent editActivity = new Intent(c,EditActivity.class);
                editActivity.putExtra("name",title);
                editActivity.putExtra("des",des);
                editActivity.putExtra("url",url);
                editActivity.putExtra("id",id);
                c.startActivityForResult(editActivity,1);
                break;
            default:
                break;
        }
        dismiss();
    }
}