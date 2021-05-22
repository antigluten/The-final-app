package com.example.theapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.theapp.fragments.DeckFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "ANTIGLUTEN";

    Fragment deckFragment = new DeckFragment();
//    Fragment statisticsFragment = new StatisticsFragment();
//    Fragment profileFragment = new ProfileFragment();

    Fragment currentFragment = deckFragment;

    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frame_content, deckFragment, "deck")
//                .add(R.id.frame_content, statisticsFragment, "stats")
//                .add(R.id.frame_content, profileFragment, "profile")
//                .hide(statisticsFragment)
//                .hide(profileFragment)
                .commit();

//        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
//        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
//            switch (item.getItemId()) {
//                case R.id.navigation_deck:
//                    changeFragment(deckFragment);
//                    return true;
////                    case R.id.navigation_stats:
////                        changeFragment(statisticsFragment);
////                        return true;
////                case R.id.navigation_profile:
////                    changeFragment(profileFragment);
////                    return true;
//            }
//            return false;
//        });


    }

//    private void changeFragment(Fragment newFragment) {
//        getSupportFragmentManager()
//                .beginTransaction()
//                .hide(currentFragment)
//                .show(newFragment)
//                .commit();
//        currentFragment = newFragment;
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Toast.makeText(
                this,
                "requestCode:" + requestCode + "|resultCode:" + resultCode,
                Toast.LENGTH_LONG
        ).show();
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onResume() {
        super.onResume();

        user = FirebaseAuth.getInstance().getCurrentUser();
        ImageButton button = findViewById(R.id.settingsButton);

        LoginDialogFragment dialogFragment = new LoginDialogFragment();
        dialogFragment.setCancelable(false);


        if (user != null) {
            button.setOnClickListener(v -> {
                FirebaseAuth.getInstance().signOut();
                dialogFragment.show(getSupportFragmentManager(), "Dialog");

            });
        } else {
            dialogFragment.setOnDismissListener(v -> {
                Log.d(TAG, "onResume: logged in");
//                SignDialogFragment signDialogFragment = new SignDialogFragment();
//                signDialogFragment.setCancelable(false);
//                signDialogFragment.show(getSupportFragmentManager(), "Dialog");
            });
            dialogFragment.show(getSupportFragmentManager(), "Dialog");
        }
    }
}