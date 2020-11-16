package com.example.covidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.covidapp.fragments.CasesFragment;
import com.example.covidapp.fragments.HomeFragment;
import com.example.covidapp.fragments.StatisticsFragment;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;

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
        tabLayout = findViewById(R.id.tab_layout);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        setNewFragment(new HomeFragment(), "Home");
                        break;
                    case 1:
                        setNewFragment(new CasesFragment(), "Cases");
                        break;
                    case 2:
                        setNewFragment(new StatisticsFragment(), "Statistics");
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
	    // Setting the fragment animation
        ft.setCustomAnimations(R.anim.slide_in, R.anim.slide_out);
        // Replace the contents of the container with the new fragment
        ft.replace(R.id.fragment_container, fragment, fragmentTag);
        // or ft.add(R.id.your_placeholder, new FooFragment());
        // Complete the changes added above
        ft.commit();
    }

    @Override
    public void onBackPressed() {
         if(isFragmentEnabled("Cases") || isFragmentEnabled("Statistics")) {
             tabLayout.getTabAt(0).select();
         } else {
             super.onBackPressed();
         }
    }

    private boolean isFragmentEnabled(String fragmentTag) {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(fragmentTag);
        if (fragment != null && fragment.isVisible()) {
            return true;
        } else {
            return  false;
        }
    }
}