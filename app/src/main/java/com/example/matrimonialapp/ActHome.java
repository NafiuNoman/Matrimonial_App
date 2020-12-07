package com.example.matrimonialapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class ActHome extends AppCompatActivity {

    TextView textView;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_home);

        textView = findViewById(R.id.textView);

        mAuth = FirebaseAuth.getInstance();


        String i= getIntent().getStringExtra("email");
        String y= getIntent().getStringExtra("name");



        textView.setText(i+""+y);

    }

    public void clickedLogOut(View view) {


        mAuth.signOut();
        Intent intent = new Intent(ActHome.this,MainActivity.class);
        startActivity(intent);



    }
}