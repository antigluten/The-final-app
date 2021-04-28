package com.example.theapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.theapp.data.Card;

public class CardTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_test);


        Intent intent = getIntent();
        intent.getSerializableExtra("Foreign");
        Card card = new Card(
                intent.getSerializableExtra("Foreign").toString(),
                intent.getSerializableExtra("Translation").toString(),
                intent.getSerializableExtra("Sentence").toString()
        );

        TextView textView = findViewById(R.id.textView2);
        Toast.makeText(getBaseContext(), card.toString(), Toast.LENGTH_LONG).show();
        String text = card.toString();
        textView.setText(text);
    }
}