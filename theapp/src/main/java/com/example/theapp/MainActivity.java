package com.example.theapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.theapp.R;
import com.example.theapp.fragments.CounterFragment;
import com.example.theapp.fragments.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    Fragment homeFragment = new HomeFragment();
    Fragment counterFragment = new CounterFragment();

    Fragment current = homeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frame_content, homeFragment, "home")
                .add(R.id.frame_content, counterFragment, "counter")
                .hide(counterFragment)
                .commit();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        changeFragment(homeFragment);
                        return true;
                    case R.id.navigation_counter:
                        changeFragment(counterFragment);
                        return true;
                }
                return false;
            }
        });
    }

    private void changeFragment(Fragment newFragment) {
//      FragmentTransaction transaction = manager.beginTransaction();
//      transaction.hide(current);
//      transaction.show(homeFragment);
//      transaction.commit();
        getSupportFragmentManager()
                .beginTransaction()
                .hide(current)
                .show(newFragment)
                .commit();
        current = newFragment;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Toast.makeText(
                this,
                "requestCode:"+requestCode+"|resultCode:"+resultCode,
                Toast.LENGTH_LONG
        ).show();

        super.onActivityResult(requestCode, resultCode, data);
    }
}