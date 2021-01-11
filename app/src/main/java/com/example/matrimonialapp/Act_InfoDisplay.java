package com.example.matrimonialapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Act_InfoDisplay extends AppCompatActivity {

    TextView addressDisplay;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfo_display);

        addressDisplay = findViewById(R.id.IdDisplayTextViewPer);
        progressBar = findViewById(R.id.progressBar);


        ClsUserDetails obj = (ClsUserDetails) getIntent().getSerializableExtra("ObjInfo");

//        obj.getpAddress()

        addressDisplay.setText(obj.getpAddress());

    }

    public void taptap(View view) {

        if(progressBar.isShown())
        {
            progressBar.setVisibility(View.INVISIBLE);
        }
        else
        {
            progressBar.setVisibility(View.VISIBLE);
        }

    }
}