package com.app.knowyourism.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.knowyourism.Activity.Clubs.ClubActivity;
import com.app.knowyourism.Activity.Contacts.ContactsActivity;
import com.app.knowyourism.Activity.LostAndFound.LnFActivity;
import com.app.knowyourism.Adapter.ContactAdapter;
import com.app.knowyourism.Adapter.FeedAdapter;
import com.app.knowyourism.Api.ResultApi;
import com.app.knowyourism.Model.Club;
import com.app.knowyourism.Model.Contacts;
import com.app.knowyourism.Model.Feed.Feed;
import com.app.knowyourism.Model.Feed.FeedResponse;
import com.app.knowyourism.Model.Location.LocationResult;
import com.app.knowyourism.Model.OtpInitiateResponse;
import com.app.knowyourism.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.fa_bookmark)
    FloatingActionButton buttonBookmark;
    @BindView(R.id.fa_task)
    FloatingActionButton buttonTask;
    @BindView(R.id.fa_club)
    FloatingActionButton buttonClub;
    @BindView(R.id.imageView3)
    ImageView imageView;
    @BindView(R.id.button2)
    Button buttonPost;
    @BindView (R.id.toolbar)
    Toolbar toolbar;

    private NavigationView mDrawer;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private int mSelectedId;
    private static final String url = "https://examupdates.in/b-tech-books/";

    //Recycler View
    FeedAdapter adapter;
    List< Feed > feeds = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        ButterKnife.bind(this);

        buttonBookmark.setOnClickListener(this);
        buttonTask.setOnClickListener(this);
        buttonClub.setOnClickListener(this);
        buttonPost.setOnClickListener(this);


        setToolbar();
        initView();

        drawerToggle=new ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close);
        mDrawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
        //default it set first item as selected
        mSelectedId=savedInstanceState ==null ? R.id.dashboard: savedInstanceState.getInt("SELECTED_ID");
        itemSelection(mSelectedId);

        adapter=new FeedAdapter(MainActivity.this);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        getData();
    }


    private void setToolbar() {
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initView() {
        mDrawer= findViewById(R.id.main_drawer);
        mDrawer.setNavigationItemSelectedListener(this);
        mDrawerLayout= findViewById(R.id.drawer_layout);
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
            case R.id.lost_n_found :
                intent =  new Intent(MainActivity.this, LnFActivity.class);
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
        Intent intent;
        switch (id){
            case R.id.fa_bookmark:
                intent = new Intent(MainActivity.this,ActivitySpy.class);
                break;
            case R.id.fa_task :
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                break;
            case R.id.fa_club :
                intent = new Intent(MainActivity.this, ClubActivity.class);
                break;
            case R.id.button2 :
                intent =  new Intent(MainActivity.this, OtherStuffActivity.class);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + id);
        }
        startActivity(intent);
    }

    private void getData() {

        ResultApi.getService().getFeeds().enqueue(new Callback< FeedResponse >() {
            @Override
            public void onResponse(@NonNull Call<FeedResponse> call, @NonNull Response<FeedResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getRecords() != null) {
                    feeds = response.body().getRecords();
                    adapter.setData(feeds);

                    Toast.makeText(MainActivity.this, feeds.size() + " Feeds Available", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "No Feeds at this moment", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<FeedResponse> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, "Failed to fetch Data!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}