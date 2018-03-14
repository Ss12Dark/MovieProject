package com.example.ss12dark.almostmovieproject;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class AddFromInternet extends AppCompatActivity {

    public ListView listViewCandies;
    private Dialog insertOrUpdateDialog;
    private AlertDialog deleteDialog;
    public MoviesReaderController candiesReaderController;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Take UI components:
        listViewCandies = (ListView) findViewById(R.id.listView);

        // Create controllers:
        candiesReaderController = new MoviesReaderController(this);

        // Show all countries from server:
        candiesReaderController.readAllCountries();
    }


}