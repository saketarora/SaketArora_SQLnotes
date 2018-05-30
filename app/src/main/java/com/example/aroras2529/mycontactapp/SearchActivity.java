package com.example.aroras2529.mycontactapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        //Get the intent that initialized this activity
        android.content.Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.Extra_Message);

        //Capture the layout textView and set the string as the text
        android.widget.TextView textview = findViewById(R.id.textView2);
        textview.setText(message);
    }
}
