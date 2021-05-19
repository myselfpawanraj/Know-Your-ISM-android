package com.app.knowyourism.Activity.locations;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.app.knowyourism.Activity.MapsActivity;
import com.app.knowyourism.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class LocationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_location);
        
        setSupportActionBar(findViewById(R.id.toolbar3));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);

        findViewById(R.id.image_map).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LocationActivity.this, MapsActivity.class));
                Toast.makeText(LocationActivity.this, "Maps", Toast.LENGTH_SHORT).show();
            }
        });
        //I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment,
                    new FragLocation()).commit();
        }
        bottomNav.setOnNavigationItemSelectedListener(navListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            item -> {
                Fragment selectedFragment = null;

                switch (item.getItemId()) {
                    case R.id.nav_campus:
                        selectedFragment = new FragLocation();
                        break;
                    case R.id.nav_restaurant:
                        selectedFragment = new FragRestaurant();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment,
                        selectedFragment).commit();

                return true;
            };

    @Override
    public boolean onSupportNavigateUp() {
        super.onBackPressed();
        return super.onSupportNavigateUp();
    }
}