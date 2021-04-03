package com.app.knowyourism.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.app.knowyourism.Model.Location.LocationResult;
import com.app.knowyourism.R;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.cardViewSpy) CardView cardViewspy;
    @BindView(R.id.cardViewMap) CardView cardViewmap;
    @BindView(R.id.cardViewOther) CardView cardViewother;
    @BindView(R.id.cardViewStudy) CardView cardViewstudy;
    @BindView(R.id.textViewUpdate) TextView updateProfile;
    final String url = "https://examupdates.in/b-tech-books/";

    private Toolbar toolbar;
    private NavigationView mDrawer;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private int mSelectedId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        ButterKnife.bind(this);

        cardViewmap.setOnClickListener(this);
        cardViewspy.setOnClickListener(this);
        cardViewother.setOnClickListener(this);
        cardViewstudy.setOnClickListener(this);


        setToolbar();
        initView();

        drawerToggle=new ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close);
        mDrawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
        //default it set first item as selected
        mSelectedId=savedInstanceState ==null ? R.id.dashboard: savedInstanceState.getInt("SELECTED_ID");
        itemSelection(mSelectedId);
    }


    private void setToolbar() {
        toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
    }

    private void initView() {
        mDrawer= (NavigationView) findViewById(R.id.main_drawer);
        mDrawer.setNavigationItemSelectedListener(this);
        mDrawerLayout= (DrawerLayout) findViewById(R.id.drawer_layout);
    }

    private void itemSelection(int mSelectedId) {
        Intent intent = null;
        switch(mSelectedId){
            case R.id.student_spy:
                intent = new Intent(MainActivity.this,ActivitySpy.class);
                break;
            case R.id.study_materials :
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData( Uri.parse(url));
                break;
            case R.id.my_profile :
                intent = new Intent(MainActivity.this, ConfirmActivity.class);
                break;
            case R.id.contact_us :
                intent =  new Intent(MainActivity.this, OtherStuffActivity.class);
                break;
            case R.id.places :
                intent =  new Intent(MainActivity.this, LocationActivity.class);
                break;
            default:
                break;
        }
        if(intent!=null)
        startActivity(intent);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        mDrawer.setCheckedItem(menuItem.getItemId());
        mSelectedId=menuItem.getItemId();
        itemSelection(mSelectedId);
        return true;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        Intent intent = null;
        switch (id){
            case R.id.cardViewSpy:
                intent = new Intent(MainActivity.this,ActivitySpy.class);
                break;
            case R.id.cardViewStudy :
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData( Uri.parse(url));
                break;
            case R.id.textViewUpdate :
                intent = new Intent(MainActivity.this, ConfirmActivity.class);
                break;
            case R.id.cardViewOther :
                intent =  new Intent(MainActivity.this, OtherStuffActivity.class);
                break;
            case R.id.cardViewMap :
                intent =  new Intent(MainActivity.this, LocationActivity.class);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + id);
        }
        startActivity(intent);
    }
}