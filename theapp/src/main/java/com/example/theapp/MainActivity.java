package com.example.theapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.theapp.database.UserModel;
import com.example.theapp.fragments.ProfileFragment;
import com.example.theapp.fragments.StatisticsFragment;
import com.example.theapp.fragments.DeckFragment;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
//        setContentView(R.layout.fragment_profile);







//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users");
//        User user = new User("Vladimir", "Louis");
//        ref.child("antigluten").setValue(user);






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
                        return true;
                }
                return false;
            }
        });



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