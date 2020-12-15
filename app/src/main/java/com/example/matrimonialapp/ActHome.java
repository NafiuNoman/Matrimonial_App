package com.example.matrimonialapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class ActHome extends AppCompatActivity {


    RecyclerView recyclerView;
    ClsCandidateRecylcerAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_home);

        recyclerView = findViewById(R.id.IdRecycler);


        FirebaseRecyclerOptions<ClsUserDetails> options =
                new FirebaseRecyclerOptions.Builder<ClsUserDetails>()
                        .setQuery(FirebaseDatabase.getInstance().getReference("student"), ClsUserDetails.class)
                        .build();



            myAdapter = new ClsCandidateRecylcerAdapter(options);
            recyclerView.setAdapter(myAdapter);

        Log.d( "Adapter","inside on create of firebaseOption");





    }

    @Override
    protected void onStart() {
        super.onStart();
        myAdapter.startListening();

        Log.d( "Adapter","inside onStart");

    }

    @Override
    protected void onStop() {
        super.onStop();
        myAdapter.stopListening();

        Log.d( "Adapter","inside onStop");

    }
}