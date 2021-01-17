package com.example.matrimonialapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;

public class Act_InfoDisplay extends AppCompatActivity {

    TextView name,age,religion,living;
    CircleImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfo_display);

        name = findViewById(R.id.IdShowName);
        age = findViewById(R.id.IdShowAge);
        religion = findViewById(R.id.IdShowReligion);
        living = findViewById(R.id.IdShowLiving);
        imageView =findViewById(R.id.IdShowImage);


        ClsUserDetails obj = (ClsUserDetails) getIntent().getSerializableExtra("ObjInfo");

        name.setText(obj.getName());
        age.setText(obj.getAge());
        religion.setText(obj.getReligion());
        living.setText(obj.getLivingStatus());

        Glide.with(name.getContext()).load(obj.getPictureUrl()).into(imageView);

    }

//    public void taptap(View view) {
//
//        if(progressBar.isShown())
//        {
//            progressBar.setVisibility(View.INVISIBLE);
//        }
//        else
//        {
//            progressBar.setVisibility(View.VISIBLE);
//        }
//
//    }
}