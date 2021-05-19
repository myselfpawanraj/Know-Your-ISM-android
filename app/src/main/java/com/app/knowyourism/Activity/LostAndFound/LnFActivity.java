package com.app.knowyourism.Activity.LostAndFound;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.app.knowyourism.Api.ResultApi;
import com.app.knowyourism.Model.LnF.LnFResponse;
import com.app.knowyourism.Model.LnF.LostFound;
import com.app.knowyourism.R;
import com.app.knowyourism.Activity.LostAndFound.main.PlaceholderFragment;
import com.app.knowyourism.Activity.LostAndFound.main.SectionsPagerAdapter;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LnFActivity extends AppCompatActivity implements Contract{
    @BindView(R.id.app_bar) AppBarLayout app_bar;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.tabs) TabLayout tabs;
    @BindView(R.id.view_pager) ViewPager viewPager;
    @BindView(R.id.floatingActionButton) Button floatingActionButton;

    List< LostFound > feeds = new ArrayList<>();
    List<LostFound> lost = new ArrayList<>();
    List<LostFound> founds = new ArrayList<>();

    List<PlaceholderFragment> placeholderFragments = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ln_f);
        ButterKnife.bind(this);
        setToolbar();
        initFrag();

        floatingActionButton.setOnClickListener(view -> {
            LnFFrag frag= new LnFFrag();
            frag.show(getSupportFragmentManager(),null);
            Toast.makeText(this, "Fav clicked", Toast.LENGTH_SHORT).show();
        });
        getData();
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void getData() {
        ResultApi.getService().getLnF().enqueue(new Callback< LnFResponse >() {
            @Override
            public void onResponse(@NonNull Call<LnFResponse> call, @NonNull Response<LnFResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getRecords() != null) {
                    Collections.reverse(response.body().getRecords());
                    feeds = response.body().getRecords();
                    lost = new ArrayList<>();
                    founds = new ArrayList<>();
                    for( LostFound l : feeds) {
                        if(l.isFound()) founds.add(l);
                        else lost.add(l);
                    }
                    setUpFrag(lost, founds);

                } else {
                    setUpFrag(lost, founds);
                    Snackbar.make(findViewById(R.id.coordinator_layout), "Failed to load Data", Snackbar.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<LnFResponse> call, @NonNull Throwable t) {
                setUpFrag(lost, founds);
                Snackbar.make(findViewById(R.id.coordinator_layout), "Failed due to unexpected error", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    private void initFrag() {
        placeholderFragments = new ArrayList<>();

        placeholderFragments.add(new PlaceholderFragment());
        placeholderFragments.add(new PlaceholderFragment());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(LnFActivity.this, getSupportFragmentManager()
                , placeholderFragments);

        viewPager.setAdapter(sectionsPagerAdapter);
        tabs.setupWithViewPager(viewPager);
    }

    private void setUpFrag(List<LostFound> lost, List<LostFound> founds) {
        if(placeholderFragments.isEmpty()){
            initFrag();
        }
        placeholderFragments.get(0).setData(lost);
        placeholderFragments.get(1).setData(founds);
    }
}