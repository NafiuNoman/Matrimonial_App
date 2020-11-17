package com.example.matrimonialapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaCodec;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

public class ActRegistration extends AppCompatActivity {

    private EditText editTxtFullName, editTxtEmail, editTextAge, editTxtPassWord;
    private Button btnRegisterUser;

    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_registration);

        editTxtFullName = findViewById(R.id.IdEditTxtFullName);
        editTxtEmail = findViewById(R.id.IdEditTxtEmail);
        editTextAge = findViewById(R.id.IdEditTxtAge);
        editTxtPassWord = findViewById(R.id.IdEditTextPassword);

        btnRegisterUser = findViewById(R.id.IdBtnRegisterUser);


        mAuth = FirebaseAuth.getInstance();


    }

    public void DoRegisterUser(View view) {

        RegisterUser();


    }

    private void RegisterUser()

    {
        final String name,password,email,age;

        Integer ageNumber;

        name = editTxtFullName.getText().toString().trim();
        password = editTxtPassWord.getText().toString().trim();
        email = editTxtEmail.getText().toString().trim();
        age = editTextAge.getText().toString().trim();

        ageNumber = Integer.valueOf(editTextAge.getText().toString().trim());


        if(name.isEmpty())
        {
            editTxtFullName.setError("Full name is required");
            editTxtFullName.requestFocus();

            return;
        }

        if(password.isEmpty())
        {
            editTxtPassWord.setError("Password is Required");
            editTxtPassWord.requestFocus();

            return;
        }

        if(password.length() < 6)

        {
            editTxtPassWord.setError("Min length should be 6 characters");
            editTxtPassWord.requestFocus();

            return;
       }

        if(email.isEmpty())
        {
            editTxtEmail.setError("Email is required");
            editTxtEmail.requestFocus();
            return;

        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())

        {
            editTxtEmail.setError("Invalid Email Id");
            editTxtEmail.requestFocus();
            return;

        }

        if(age.isEmpty())
        {
            editTextAge.setError("Age is Required");
            editTextAge.requestFocus();
            return;
        }
        if(ageNumber < 20)

        {
            editTextAge.setError("Min Age is 20");
            editTextAge.requestFocus();
            return;
        }



        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            FirebaseUser user = mAuth.getCurrentUser();

                        } else {

                            Toast.makeText(ActRegistration.this, "ok failed", Toast.LENGTH_SHORT).show();


                        }

                        // ...
                    }
                });



    }

}

