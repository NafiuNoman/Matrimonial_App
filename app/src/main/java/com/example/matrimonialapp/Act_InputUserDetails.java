package com.example.matrimonialapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Act_InputUserDetails extends AppCompatActivity {


    TextInputLayout name,creator;

    AutoCompleteTextView religion,autoCreator,autoMaritalStatus;

    RadioGroup genderRadio;

    EditText age;

    Button btn;

    String sName,sAge,sGender,sReligion;
    String[] religionNames;
    String[] creatorsName;
    String[] maritalStatus;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act__input_user_details);

        name = findViewById(R.id.IdInputName);
        creator = findViewById(R.id.IdInputCreator);

        autoCreator = findViewById(R.id.IdInputAutoCreator);
        autoMaritalStatus = findViewById(R.id.IdInputAutoMaritalStatus);
        age = findViewById(R.id.IdInputAge);
        religion = findViewById(R.id.IdInputReligion);
        genderRadio = findViewById(R.id.IdInputRadioGroupGender);

        btn  = findViewById(R.id.IdInputbtn);


        religionNames = getResources().getStringArray(R.array.Religions);
        creatorsName = getResources().getStringArray(R.array.Creator);
        maritalStatus = getResources().getStringArray(R.array.maritalStatus);

        ArrayAdapter<String> religionAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,religionNames);
        ArrayAdapter<String> creatorAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,creatorsName);
        ArrayAdapter<String> maritalAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,maritalStatus);

        religion.setAdapter(religionAdapter);
        autoCreator.setAdapter(creatorAdapter);
        autoMaritalStatus.setAdapter(maritalAdapter);


    }

    public void sendToRealDatabase(View view) {
        
        
        

        sName = name.getEditText().getText().toString().trim();

        sAge = age.getText().toString().trim();
        sReligion =religion.getText().toString().trim();

        
        ClsUserDetails clsUserDetails = new ClsUserDetails(sName,sAge,sGender,sReligion);
        
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("student");
        reference.child(sAge).setValue(clsUserDetails);

        Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
        





    }

    public void GenderClicked(View view) {


        switch (view.getId())
        {
            case R.id.IdRadioButtonMale:
                sGender = "Male";

            case R.id.IdRadioButtonFemale:
                sGender="Female";
            default:
                Toast.makeText(this, "Male", Toast.LENGTH_SHORT).show();

        }


    }
}