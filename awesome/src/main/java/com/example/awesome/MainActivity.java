package com.example.awesome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.example.awesome.User;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    final String TAG = "FAILURE";

    private static final String keyUser = "User";
    private static final String keyName = "Name";
    private static final String keyLast = "Last";
    private static final String keyAge = "Age";

    EditText name;
    EditText last;
    EditText age;
    Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        last = findViewById(R.id.lastName);
        age = findViewById(R.id.age);
        submit = findViewById(R.id.button);

        FirebaseFirestore base = FirebaseFirestore.getInstance();

        submit.setOnClickListener(new View.OnClickListener() {
            int id = 0;
            @Override
            public void onClick(View v) {
                String nameA = name.getText().toString().trim();
                String lastA = last.getText().toString().trim();
                int ageA = Integer.parseInt(age.getText().toString().trim());
                User user = new User(nameA, lastA, ageA);

                Map<String, Object> data = new HashMap<>();
                data.put(keyName, nameA);
                data.put(keyLast, lastA);
                data.put(keyAge, ageA);

                base.collection("learning")
                        .document(String.valueOf(id))
                        .set(data)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_LONG).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d(TAG, "onFailure" + e.toString());
                            }
                        });
                id++;
            }
        });
    }
}