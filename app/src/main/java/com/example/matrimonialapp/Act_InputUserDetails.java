package com.example.matrimonialapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class Act_InputUserDetails extends AppCompatActivity {


    Uri filePath;
    Bitmap bitmap;
    String key,picUrl;

    TextInputLayout name, creator, dob, age;

    ImageView profilePic;

    TextInputEditText dobInput, height, income, cAddress, pAddress;


    AutoCompleteTextView
            autoReligion,
            autoCreator,
            autoMaritalStatus,
            autoLivingStatus,
            autoWorkingStatus,
            autoPhysicalStatus;

    RadioGroup genderRadio;


    Button btn,browsBtn;

    String sCreator, sName, sAge, sGender, sDob, sMaritalStatus, sReligion, sHeight,
            sPhysicalStatus, sLivingStatus, sIncome, sPaddress, sCaddress, sWorkingStatus;
    String[] religionNames;
    String[] creatorsName;
    String[] maritalStatus;
    String[] physicalStatus;
    String[] livingStatus;
    String[] workingStatus;


    Calendar calendar;
    DatePickerDialog datePickerDialog;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act__input_user_details);

        profilePic = findViewById(R.id.IdInputImage);
        browsBtn = findViewById(R.id.IdInputBrowsePic);
        name = findViewById(R.id.IdInputName);
        creator = findViewById(R.id.IdInputCreator);
        dob = findViewById(R.id.IdInputDOB);
        height = findViewById(R.id.IdInputHeight);
        dobInput = findViewById(R.id.IdInputDobInput);
        income = findViewById(R.id.IdInputIncome);
        cAddress = findViewById(R.id.IdInputCurrentAddress);
        pAddress = findViewById(R.id.IdInputPermanentAddress);

        autoReligion = findViewById(R.id.IdInputReligion);
        autoCreator = findViewById(R.id.IdInputAutoCreator);
        autoMaritalStatus = findViewById(R.id.IdInputAutoMaritalStatus);
        autoPhysicalStatus = findViewById(R.id.IdInputPhysicalStatus);
        autoLivingStatus = findViewById(R.id.IdInputLiving);
        autoWorkingStatus = findViewById(R.id.IdInputWorking);


        age = findViewById(R.id.IdInputAge);
        genderRadio = findViewById(R.id.IdInputRadioGroupGender);

        btn = findViewById(R.id.IdInputBtn);


        religionNames = getResources().getStringArray(R.array.Religions);
        creatorsName = getResources().getStringArray(R.array.Creator);
        maritalStatus = getResources().getStringArray(R.array.maritalStatus);
        livingStatus = getResources().getStringArray(R.array.livingStatus);
        workingStatus = getResources().getStringArray(R.array.workStatus);
        physicalStatus = getResources().getStringArray(R.array.physicalCondition);

        ArrayAdapter<String> religionAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, religionNames);
        ArrayAdapter<String> creatorAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, creatorsName);
        ArrayAdapter<String> maritalAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, maritalStatus);
        ArrayAdapter<String> workingAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, workingStatus);
        ArrayAdapter<String> livingAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, livingStatus);
        ArrayAdapter<String> physicalAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, physicalStatus);

        autoReligion.setAdapter(religionAdapter);
        autoCreator.setAdapter(creatorAdapter);
        autoMaritalStatus.setAdapter(maritalAdapter);
        autoLivingStatus.setAdapter(livingAdapter);
        autoWorkingStatus.setAdapter(workingAdapter);
        autoPhysicalStatus.setAdapter(physicalAdapter);


         reference = FirebaseDatabase.getInstance().getReference("student");
         key = reference.push().getKey();


    }

    public void sendToRealDatabase(View view) {


        sCreator = creator.getEditText().getText().toString().trim();
        sName = name.getEditText().getText().toString().trim();
        sAge = age.getEditText().getText().toString().trim();
        sReligion = autoReligion.getText().toString().trim();
        sDob = dobInput.getText().toString().trim();
        sMaritalStatus = autoMaritalStatus.getText().toString().trim();
        sReligion = autoReligion.getText().toString().trim();
        sHeight = height.getText().toString().trim();
        sPhysicalStatus = autoPhysicalStatus.getText().toString().trim();
        sLivingStatus = autoLivingStatus.getText().toString().trim();
        sIncome = income.getText().toString().trim();
        sCaddress = cAddress.getText().toString().trim();
        sPaddress = pAddress.getText().toString().trim();
        sWorkingStatus = autoWorkingStatus.getText().toString().trim();


        ClsUserDetails clsUserDetails = new ClsUserDetails(sCreator, sName, sGender, sAge, sDob, sMaritalStatus, sReligion,
                sHeight, sPhysicalStatus, sLivingStatus, sIncome, sPaddress, sCaddress, sWorkingStatus,picUrl);

//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference reference = database.getReference("student");
//         key = reference.push().getKey();
        reference.child(key).setValue(clsUserDetails);

        Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(Act_InputUserDetails.this, ActHome.class);

        startActivity(intent);

        Toast.makeText(this, "now it will show recyclerview", Toast.LENGTH_SHORT).show();

    }

    public void GenderClicked(View view) {


        switch (view.getId()) {
            case R.id.IdRadioButtonMale:
                sGender = "Male";

            case R.id.IdRadioButtonFemale:
                sGender = "Female";
            default:
                Toast.makeText(this, "Male", Toast.LENGTH_SHORT).show();

        }


    }

    public void ClickedDateOfBirth(View view) {

        calendar = Calendar.getInstance();

        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int iYear, int iMonth, int iDay) {

                dobInput.setText("" + iDay + "/" + (iMonth + 1) + "/" + iYear);
            }
        }, day, month, year);

        datePickerDialog.show();


    }

    public void ImageViewClicked(View view) {

        Dexter.withContext(Act_InputUserDetails.this)
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {

                        Intent intent = new Intent(Intent.ACTION_PICK);
                        intent.setType("image/*");

                        startActivityForResult(Intent.createChooser(intent, "please select the app"), 1);

                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {

                    }
                })
                .check();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            filePath = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(filePath);
                bitmap = BitmapFactory.decodeStream(inputStream);

                profilePic.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

    public void SendToStorage(View view) {

        FirebaseStorage storage = FirebaseStorage.getInstance();
         final StorageReference uploder =  storage.getReference().child("user"+key);


        uploder.putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


              uploder.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                  @Override
                  public void onSuccess(Uri uri) {
                      picUrl = uri.toString();
                  }
              });

                ////////////

//         picUrl  = String.valueOf(uploder.getDownloadUrl());
//
//          Log.d("url",""+picUrl);

          //////
//                HashMap<String,String> hashMap = new HashMap<>();
//                hashMap.put(picUrl, String.valueOf(uploder.getDownloadUrl()));
//
//                Log.d("link...","!!!"+picUrl);



                Toast.makeText(Act_InputUserDetails.this, "Purl Send", Toast.LENGTH_SHORT).show();

            }
        });



    }
}