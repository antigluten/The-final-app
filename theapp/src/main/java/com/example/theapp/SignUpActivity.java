package com.example.theapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        EditText login  = findViewById(R.id.profileName);
        EditText password = findViewById(R.id.profilePassword);
        EditText email = findViewById(R.id.profileEmail);
        Button submit = findViewById(R.id.profileSubmit);


        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = login.getText().toString().trim();
                String pass = password.getText().toString().trim();
                String mail = email.getText().toString().trim();

                if (mail.isEmpty()) {
                    email.setError("Please fill it");
                    email.requestFocus();
                    return;
                }


                User currentUser = new User(
                        name,
                        pass,
                        mail
                );
                Log.d("ANTIGLUTEN", currentUser.toString());
                ref.push().setValue(currentUser);
            }
        });


    }
}
