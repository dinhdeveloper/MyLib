package com.canhdinh.mylib;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class AnimationActivity extends AppCompatActivity {

    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        navigationView = findViewById(R.id.nav);
        showView(R.layout.grav);
        initNavigationView();
    }

    private void showView(int view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, GravSampleFragment.newInstance(view))
                .commit();

    }

    private void initNavigationView() {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override public boolean onNavigationItemSelected(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.ball_wave:
                                showView(R.layout.ball_wave);
                                return true;
                            case R.id.grav:
                                showView(R.layout.grav);
                                return true;
                            case R.id.robot:
                                showView(R.layout.robot);
                                return true;
                            case R.id.falcon:
                                showView(R.layout.falcon);
                                return true;
                            case R.id.bubble:
                                showView(R.layout.bubble);
                                return true;
                            case R.id.path:
                                showView(R.layout.path);
                                return true;
                        }
                        return false;
                    }
                });
    }
}