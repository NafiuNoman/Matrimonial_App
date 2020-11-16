package com.example.matrimonialapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private TextView register,forgetPassword;

    private EditText email,password;

    private Button btnLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        register = findViewById(R.id.IdTxtRegistration);
        forgetPassword = findViewById(R.id.IdTxtForgetPassword);
        email = findViewById(R.id.IdEditTxtEmail);
        password = findViewById(R.id.IdEditTxtPassword);

        btnLogIn = findViewById(R.id.IdBtnLogIn);


        mAuth = FirebaseAuth.getInstance();
    }

    public void getTheClick(View view) {

        switch (view.getId())

        {
            case R.id.IdBtnLogIn:
                Intent intent = new Intent(MainActivity.this,ActHome.class);
                MainActivity.this.startActivity(intent);

                break;

            case R.id.IdTxtRegistration:
                Intent intent1 = new Intent(MainActivity.this,ActRegistration.class);
                MainActivity.this.startActivity(intent1);

        }

    }
}