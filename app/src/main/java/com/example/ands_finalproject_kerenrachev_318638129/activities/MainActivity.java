package com.example.ands_finalproject_kerenrachev_318638129.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.ands_finalproject_kerenrachev_318638129.R;
import com.example.ands_finalproject_kerenrachev_318638129.fragments.Fragment_apps;
import com.example.ands_finalproject_kerenrachev_318638129.fragments.Fragment_home;
import com.example.ands_finalproject_kerenrachev_318638129.fragments.Fragment_permissions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openHomeFragment();
        setBottomNavView();
    }

    private void openHomeFragment() {
        Fragment_home fragment_home = new Fragment_home();
        getSupportFragmentManager().beginTransaction().replace(R.id.panel_FRAME_content, fragment_home).commit();
    }

    private void setBottomNavView() {
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        Fragment_home fragment_home = new Fragment_home();
                        getSupportFragmentManager().beginTransaction().replace(R.id.panel_FRAME_content, fragment_home).commit();
                        return true;

                    case R.id.apps:
                        Fragment_apps fragment_apps = new Fragment_apps();
                        getSupportFragmentManager().beginTransaction().replace(R.id.panel_FRAME_content, fragment_apps).commit();
                        return true;
                    case R.id.permissions:
                        Fragment_permissions fragment_permissions = new Fragment_permissions();
                        getSupportFragmentManager().beginTransaction().replace(R.id.panel_FRAME_content, fragment_permissions).commit();
                        return true;
                }
                return false;
            }
        });
    }
}