package com.example.money_clicker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView money;
    private int dollars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        money = findViewById(R.id.money);
        ImageView cookie = findViewById(R.id.cookie);
        cookie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dollars++;
                money.setText(String.valueOf(dollars));
            }
        });
    }
}