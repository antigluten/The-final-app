package com.example.theapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.theapp.database.AppDatabase;
import com.example.theapp.database.User;
import com.example.theapp.database.UserDao;
import com.example.theapp.fragments.ProfileFragment;
import com.example.theapp.fragments.StatisticsFragment;
import com.example.theapp.fragments.DeckFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Fragment deckFragment = new DeckFragment();
    Fragment statisticsFragment = new StatisticsFragment();
    Fragment profileFragment = new ProfileFragment();

    Fragment currentFragment = deckFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frame_content, deckFragment, "deck")
                .add(R.id.frame_content, statisticsFragment, "stats")
                .add(R.id.frame_content, profileFragment, "profile")
                .hide(statisticsFragment)
                .hide(profileFragment)
                .commit();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_deck:
                        changeFragment(deckFragment);
                        return true;
                    case R.id.navigation_stats:
                        changeFragment(statisticsFragment);
                        return true;
                    case R.id.navigation_profile:
                        changeFragment(profileFragment);
                }
                return false;
            }
        });

        //TODO make a database
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").build();

        UserDao userDao = db.userDao();
        List<User> users = userDao.getAll();
    }

    private void changeFragment(Fragment newFragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .hide(currentFragment)
                .show(newFragment)
                .commit();
        currentFragment = newFragment;
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