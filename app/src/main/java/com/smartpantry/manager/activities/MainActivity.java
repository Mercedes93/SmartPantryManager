package com.smartpantry.manager.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.smartpantry.manager.R;
import com.smartpantry.manager.fragments.PantryListFragment;
import com.smartpantry.manager.fragments.SettingsFragment;
import com.smartpantry.manager.fragments.SuggestedRecipesFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);

        // Load pantry list as the default screen
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new PantryListFragment())
                    .commit();
            bottomNav.setSelectedItemId(R.id.nav_pantry);
        }

        bottomNav.setOnItemSelectedListener(item -> {
            Fragment selected;
            int id = item.getItemId();
            if (id == R.id.nav_pantry) {
                selected = new PantryListFragment();
            } else if (id == R.id.nav_recipes) {
                selected = new SuggestedRecipesFragment();
            } else {
                selected = new SettingsFragment();
            }
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, selected)
                    .commit();
            return true;
        });
    }
}

