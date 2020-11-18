package com.example.covidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.covidapp.fragments.CasesFragment;
import com.example.covidapp.fragments.HomeFragment;
import com.example.covidapp.fragments.StatisticsFragment;
import com.google.android.material.tabs.TabLayout;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tab_layout);
        setUpTabLayout();
        setNewFragment(new HomeFragment(), "Home");
    }

    //Handles Tab Layout Clicks
    private void setUpTabLayout() {

        tabLayout = findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_baseline_home_24));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_baseline_import_contacts_24));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_baseline_equalizer_24));

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(R.layout.main_activity_tab_layout);
                TextView  textView = tab.getCustomView().findViewById(R.id.tabTitle);
                switch (i) {
                    case 0:
                        textView.setText("Home");
                        textView.setTextColor(getResources().getColor(R.color.topBarActiveText));
                        break;
                    case 1:
                        textView.setText("Cases");
                        textView.setTextColor(getResources().getColor(R.color.topBarInctiveText));
                        break;
                    case 2:
                        textView.setText("Statistics");
                        textView.setTextColor(getResources().getColor(R.color.topBarInctiveText));
                }
            }

        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                TextView textView = tab.getCustomView().findViewById(R.id.tabTitle);
                textView.setTextColor(getResources().getColor(R.color.topBarActiveText));
                for(int i = 0; i < 3; i++) {
                    if(i != tab.getPosition()) {
                        TextView otherTextView = tabLayout.getTabAt(i).getCustomView().findViewById(R.id.tabTitle);
                        otherTextView.setTextColor(getResources().getColor(R.color.topBarInctiveText));
                    }
                 }
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