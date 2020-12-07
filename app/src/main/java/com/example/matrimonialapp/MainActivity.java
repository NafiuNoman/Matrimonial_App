package com.example.matrimonialapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
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
        email = findViewById(R.id.IdEditTxtEmailLogIn);
        password = findViewById(R.id.IdEditTxtPasswordLogIn);

        btnLogIn = findViewById(R.id.IdBtnLogIn);



     mAuth = FirebaseAuth.getInstance();
    }

    public void getTheClick(View view) {

        switch (view.getId())

        {
            case R.id.IdBtnLogIn:

                manageLogIn();

                break;

            case R.id.IdTxtRegistration:
                Intent intent1 = new Intent(MainActivity.this,ActRegistration.class);
                MainActivity.this.startActivity(intent1);

        }

    }

    private void manageLogIn() {

       String logInEmail = email.getText().toString().trim();
       String logInPassword = password.getText().toString().trim();





        mAuth.signInWithEmailAndPassword(logInEmail, logInPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {


                            Intent intent = new Intent(MainActivity.this,ActHome.class);

                            intent.putExtra("email",mAuth.getCurrentUser().getEmail());
                            intent.putExtra("name",mAuth.getCurrentUser().getDisplayName());


                            startActivity(intent);



                        } else

                            {
                                email.setError("");

                                password.setError("");

                                Toast.makeText(MainActivity.this, "Invalid password/email", Toast.LENGTH_SHORT).show();

                            }

                    }
                });


 }
}