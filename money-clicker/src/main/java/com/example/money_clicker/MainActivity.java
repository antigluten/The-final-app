package com.example.money_clicker;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView money;
    private int dollars;
    private TextView initialCost;

    private int lvl = 0;
    private int increase = 0;
    private int adderValue = 1;
    private int costOfAdder = 5;

    private int clicks = 0;
    TextView clicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // how much money we have to add
        clicked = findViewById(R.id.clicked);

        money = findViewById(R.id.money);
        ImageView cookie = findViewById(R.id.cookie);
        cookie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickStats();
                dollars += adderValue;
                money.setText(String.valueOf(dollars));
            }
        });
        Button adder = findViewById(R.id.adder);

        initialCost = findViewById(R.id.costOfAdder);

        adder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dollars >= costOfAdder) {
                    increaseClick();
                }
            }
        });
    }
    @SuppressLint("SetTextI18n")
    public void clickStats() {
        clicks++;
        clicked.setText("Clicked: " + clicks);
    }
    public void increaseClick() {
        dollars -= costOfAdder;
        lvl++;
        costOfAdder += costOfAdder + 0.1;
        increase++;
        adderValue += increase;
        initialCost.setText(String.valueOf("Cost: " + costOfAdder + " - Adder value: "  + String.valueOf(adderValue)));
        money.setText(String.valueOf(dollars));
    }
}