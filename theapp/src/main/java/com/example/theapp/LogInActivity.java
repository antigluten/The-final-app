package com.example.theapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogInActivity extends Activity {

    private FirebaseUser user;

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

        user = mAuth.getCurrentUser();

        ConstraintLayout constraintLayout = findViewById(R.id.container2);
        logIn.setOnClickListener(v -> {
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

            constraintLayout.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);

            mAuth.signInWithEmailAndPassword(mail, pass).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    progressBar.setVisibility(View.INVISIBLE);
                    finish();
                } else {
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(getBaseContext(), "Fail to sign in", Toast.LENGTH_LONG).show();
                }
            });
        });

        register.setOnClickListener(v -> {
            startActivity(new Intent(getBaseContext(), SignUpActivity.class));
            finish();
        });
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//
//        if (user == null) {
//            startActivity(new Intent(getBaseContext(), LogInActivity.class));
//        }
//    }
}
