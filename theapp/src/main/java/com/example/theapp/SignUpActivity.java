package com.example.theapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.NotNull;

import java.util.concurrent.Executor;

public class SignUpActivity extends Activity {
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();


        EditText login  = findViewById(R.id.profileName);
        EditText password = findViewById(R.id.profilePassword);
        EditText email = findViewById(R.id.profileEmail);
        Button submit = findViewById(R.id.profileSubmit);
        ProgressBar progressBar = findViewById(R.id.progress_circular);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

                progressBar.setVisibility(View.VISIBLE);
                mAuth.createUserWithEmailAndPassword(mail, pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    User user = new User(name, pass, mail);
                                    FirebaseDatabase.getInstance().getReference("Users")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(user)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        Toast.makeText(getBaseContext(),"User was registered", Toast.LENGTH_SHORT).show();
                                                        progressBar.setVisibility(View.INVISIBLE);
                                                    }
                                                    else {
                                                        Toast.makeText(getBaseContext(),"Fail to register, try again!!!", Toast.LENGTH_SHORT).show();
                                                        progressBar.setVisibility(View.INVISIBLE);
                                                    }
                                                }
                                            });

                                } else {
                                    Toast.makeText(getBaseContext(),"Fail to register!!!", Toast.LENGTH_SHORT).show();
                                    progressBar.setVisibility(View.INVISIBLE);
                                }

                            }
                        });
            }
        });


    }
}
