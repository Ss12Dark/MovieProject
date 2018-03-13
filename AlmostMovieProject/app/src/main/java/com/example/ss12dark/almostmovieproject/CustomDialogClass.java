package com.example.ss12dark.almostmovieproject;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

/**
 * Created by Android on 13/03/2018.
 */
public class CustomDialogClass extends Dialog implements
        android.view.View.OnClickListener {

    public Activity c;
    public Dialog d;
    public Button delete, edit;

    public CustomDialogClass(Activity a) {
        super(a);

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

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_delete:
                c.finish();
                break;
            case R.id.btn_edit:
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }
}