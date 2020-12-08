package com.example.matrimonialapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Act_InputUserDetails extends AppCompatActivity {


    EditText name,age,gender;
    String sName,sAge,sGender,sReligion,sGenderNew;
    String[] religionNames;
    AutoCompleteTextView religion;
    TextInputLayout genderNew;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act__input_user_details);

        name = findViewById(R.id.IdInputName);
        age = findViewById(R.id.IdInputAge);
        gender = findViewById(R.id.IdInputGender);
        religion = findViewById(R.id.IdInputReligion);

        genderNew = findViewById(R.id.IdInputGenderNew);


        religionNames = getResources().getStringArray(R.array.Religions);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,religionNames);

        religion.setAdapter(adapter);


    }

    public void sendToRealDatabase(View view) {
        
        
        
        sName = name.getText().toString().trim();
        sGender = gender.getText().toString().trim();
        sGenderNew = genderNew.getEditText().getText().toString().trim();
        sAge = age.getText().toString().trim();
        sReligion =religion.getText().toString().trim();

        
        ClsUserDetails clsUserDetails = new ClsUserDetails(sName,sAge,sGender,sReligion,sGenderNew);
        
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("student");

        reference.child(sAge).setValue(clsUserDetails);

        Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
        





    }
}