package com.example.matrimonialapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Act_InputUserDetails extends AppCompatActivity {


    EditText name,age,gender;
    String sName,sAge,sGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act__input_user_details);

        name = findViewById(R.id.IdInputName);
        age = findViewById(R.id.IdInputAge);
        gender = findViewById(R.id.IdInputGender);
    }

    public void sendToRealDatabase(View view) {
        
        
        
        sName = name.getText().toString().trim();
        sGender = gender.getText().toString().trim();
        sAge = age.getText().toString().trim();
        
        ClsUserDetails clsUserDetails = new ClsUserDetails(sName,sAge,sGender);
        
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("student");

        reference.child(sAge).setValue(clsUserDetails);

        Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
        





    }
}