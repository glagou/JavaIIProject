package com.example.covidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

import com.example.covidapp.firebase.FirebaseFunctions;
import com.example.covidapp.fragments.AddContactsFragment;
import com.example.covidapp.fragments.HomeFragment;
import com.example.covidapp.fragments.ManageContactsFragment;
import com.example.covidapp.fragments.ResultsFragment;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tab_layout);
        setTabLayoutNavigationListener();
        setNewFragment(new HomeFragment(), "Home");
    }

    //Handles Tab Layout Clicks
    private void setTabLayoutNavigationListener() {
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        setNewFragment(new HomeFragment(), "Home");
                        break;
                    case 1:
                        setNewFragment(new AddContactsFragment(), "Add_Contacts");
                        break;
                    case 2:
                        setNewFragment(new ResultsFragment(), "Results");
                        break;
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    //Sets a new fragment of type Fragment and tag FragmentTag
    private void setNewFragment(Fragment fragment, String fragmentTag) {
        // Begin the transaction
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        // Replace the contents of the container with the new fragment
        ft.replace(R.id.fragment_container, fragment, fragmentTag);
        // or ft.add(R.id.your_placeholder, new FooFragment());
        // Complete the changes added above
        ft.commit();
    }
}