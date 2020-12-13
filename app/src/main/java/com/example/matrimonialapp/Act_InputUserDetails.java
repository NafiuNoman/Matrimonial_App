package com.example.matrimonialapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Act_InputUserDetails extends AppCompatActivity {


    TextInputLayout name,creator,dob,age;

    TextInputEditText dobInput;


    AutoCompleteTextView
            autoReligion,
            autoCreator,
            autoMaritalStatus,
            autolivingStatus,
            autoWorkingStatus,
            autoPhysicalStatus;

    RadioGroup genderRadio;


    Button btn;

    String sName,sAge,sGender,sReligion;
    String[] religionNames;
    String[] creatorsName;
    String[] maritalStatus;
    String[] physicalStatus;
    String[] livingStatus;
    String[] workingStatus;


    Calendar calendar;
    DatePickerDialog datePickerDialog;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act__input_user_details);

        name = findViewById(R.id.IdInputName);
        creator = findViewById(R.id.IdInputCreator);
        dob = findViewById(R.id.IdInputDOB);
        dobInput = findViewById(R.id.IdInputDobInput);

        autoReligion = findViewById(R.id.IdInputReligion);
        autoCreator = findViewById(R.id.IdInputAutoCreator);
        autoMaritalStatus = findViewById(R.id.IdInputAutoMaritalStatus);
        autoPhysicalStatus = findViewById(R.id.IdInputPhysicalStatus);
        autolivingStatus = findViewById(R.id.IdInputLiving);
        autoWorkingStatus=findViewById(R.id.IdInputWorking);


        age = findViewById(R.id.IdInputAge);
        genderRadio = findViewById(R.id.IdInputRadioGroupGender);

        btn  = findViewById(R.id.IdInputBtn);


        religionNames = getResources().getStringArray(R.array.Religions);
        creatorsName = getResources().getStringArray(R.array.Creator);
        maritalStatus = getResources().getStringArray(R.array.maritalStatus);
        livingStatus = getResources().getStringArray(R.array.livingStatus);
        workingStatus = getResources().getStringArray(R.array.workStatus);
        physicalStatus = getResources().getStringArray(R.array.physicalCondition);

        ArrayAdapter<String> religionAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,religionNames);
        ArrayAdapter<String> creatorAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,creatorsName);
        ArrayAdapter<String> maritalAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,maritalStatus);
        ArrayAdapter<String> workingAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,workingStatus);
        ArrayAdapter<String> livingAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,livingStatus);
        ArrayAdapter<String> physicalAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,physicalStatus);

        autoReligion.setAdapter(religionAdapter);
        autoCreator.setAdapter(creatorAdapter);
        autoMaritalStatus.setAdapter(maritalAdapter);
        autolivingStatus.setAdapter(livingAdapter);
        autoWorkingStatus.setAdapter(workingAdapter);
        autoPhysicalStatus.setAdapter(physicalAdapter);


    }

    public void sendToRealDatabase(View view) {
        
        
        

        sName = name.getEditText().getText().toString().trim();

        sAge = age.getEditText().getText().toString().trim();
        sReligion = autoReligion.getText().toString().trim();

        
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

    public void ClickedDateOfBirth(View view) {

        calendar = Calendar.getInstance();

        int day =calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        datePickerDialog =    new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int iYear, int iMonth, int iDay) {

               dobInput.setText(""+iDay+"/"+(iMonth+1)+"/"+iYear);
            }
        },day,month,year);

        datePickerDialog.show();


    }
}