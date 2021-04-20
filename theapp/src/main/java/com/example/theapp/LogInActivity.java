package com.example.theapp;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class LogInActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        EditText login = findViewById(R.id.profileLogin);
        EditText password = findViewById(R.id.profileLoginPassword);
        TextView register = findViewById(R.id.profileRegister);
        Button logIn = findViewById(R.id.profileRegisterButton);
        ProgressBar progressBar = findViewById(R.id.profileProgress);

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = login.getText().toString().trim();
                String pass = password.getText().toString().trim();

                if (mail.isEmpty()) {
                    login.setError("Please fill it");
                    login.requestFocus();
                    return;
                }

                if (pass.isEmpty()) {
                    password.setError("Invalid password");
                    password.requestFocus();
                    return;
                }

                if (pass.length() < 8) {
                    password.setError("Invalid length of password, min is 8");
                    password.requestFocus();

                }

                progressBar.setVisibility(View.VISIBLE);

                mAuth.signInWithEmailAndPassword(mail, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressBar.setVisibility(View.INVISIBLE);
                            finish();
                        }
                        else {
                            progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(getBaseContext(), "Fail to sign in", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), SignUpActivity.class));
                finish();
            }
        });
    }
}
