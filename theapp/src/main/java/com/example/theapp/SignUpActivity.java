package com.example.theapp;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;


public class SignUpActivity extends Activity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        ConstraintLayout constraintLayout = findViewById(R.id.container);

        mAuth = FirebaseAuth.getInstance();

        EditText login  = findViewById(R.id.profileName);
        EditText password = findViewById(R.id.profilePassword);
        EditText email = findViewById(R.id.profileEmail);
        Button submit = findViewById(R.id.profileSubmit);
        ProgressBar progressBar = findViewById(R.id.progress_circular);

        submit.setOnClickListener(v -> {

            String name = login.getText().toString().trim();
            String pass = password.getText().toString().trim();
            String mail = email.getText().toString().trim();

            if (name.isEmpty()) {
                login.setError("Invalid login");
                login.requestFocus();
                return;
            }

            if (pass.isEmpty()) {
                password.setError("Invalid password");
                password.requestFocus();
                return;
            }

            if (mail.isEmpty()) {
                email.setError("Please fill it");
                email.requestFocus();
                return;
            }

            if (pass.length() < 8) {
                password.setError("Invalid length of password, min is 8");
                password.requestFocus();

            }

            constraintLayout.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
            mAuth.createUserWithEmailAndPassword(mail, pass)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            User user = new User(name, pass, mail);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user)
                                    .addOnCompleteListener(task1 -> {
                                        if (task1.isSuccessful()) {
                                            Toast.makeText(getBaseContext(),"User was registered", Toast.LENGTH_SHORT).show();
                                            progressBar.setVisibility(View.INVISIBLE);
                                            finish();
                                        }
                                        else {
                                            Toast.makeText(getBaseContext(),"Fail to register, try again!!!", Toast.LENGTH_SHORT).show();
                                            progressBar.setVisibility(View.INVISIBLE);
                                        }
                                    });

                        } else {
                            Toast.makeText(getBaseContext(),"Fail to register!!!", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.INVISIBLE);
                        }

                    });
        });

    }

}
