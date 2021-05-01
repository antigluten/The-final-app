package com.example.theapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.RemoteController;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.theapp.data.Card;
import com.example.theapp.data.DatabaseHelper;

public class CardTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_test);

//        DatabaseHelper databaseHelper = new DatabaseHelper(getBaseContext());
//
//        Intent intent = getIntent();
//        intent.getSerializableExtra("Foreign");
//        Card card = new Card(
//                intent.getSerializableExtra("Foreign").toString(),
//                intent.getSerializableExtra("Translation").toString(),
//                intent.getSerializableExtra("Sentence").toString()
//        );
//
//        Button button = findViewById(R.id.deleteCard);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                databaseHelper.deleteOne(card);
//                finish();
//            }
//        });
//
//        TextView textView = findViewById(R.id.textView2);
//        Toast.makeText(getBaseContext(), card.toString(), Toast.LENGTH_LONG).show();
//        String text = card.toString();
//        textView.setText(text);
    }
}