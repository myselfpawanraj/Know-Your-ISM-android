package com.app.knowyourism.Activity.LostAndFound;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.app.knowyourism.Activity.MainActivity;
import com.app.knowyourism.Adapter.FeedAdapter;
import com.app.knowyourism.Adapter.LostFoundAdapter;
import com.app.knowyourism.Api.ResultApi;
import com.app.knowyourism.Model.Feed.Feed;
import com.app.knowyourism.Model.Feed.FeedResponse;
import com.app.knowyourism.Model.LnF.LnFResponse;
import com.app.knowyourism.Model.LnF.LostFound;
import com.app.knowyourism.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LnFActivity extends AppCompatActivity implements Contract{
    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.floatingActionButton) FloatingActionButton floatingActionButton;
    @BindView(R.id.tv_no_post) TextView tv_no_post;

    //Recycler View
    LostFoundAdapter adapter;
    List< LostFound > feeds = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ln_f);
        ButterKnife.bind(this);
        setToolbar();
        setUpRecyclerView();

        floatingActionButton.setOnClickListener(view -> {
            LnFFrag frag= new LnFFrag();
            frag.show(getSupportFragmentManager(),null);
            Toast.makeText(this, "Fav clicked", Toast.LENGTH_SHORT).show();
        });
        getData();
    }

    private void setUpRecyclerView() {
        adapter=new LostFoundAdapter(LnFActivity.this);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void setToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void getData() {
        ResultApi.getService().getLnF().enqueue(new Callback< LnFResponse >() {
            @Override
            public void onResponse(@NonNull Call<LnFResponse> call, @NonNull Response<LnFResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getRecords() != null) {
                    feeds = response.body().getRecords();
                    adapter.setData(feeds);
                    Toast.makeText(LnFActivity.this, feeds.size() + " Feeds Available", Toast.LENGTH_SHORT).show();
                    tv_no_post.setVisibility(View.GONE);
                } else {
                    tv_no_post.setVisibility(View.VISIBLE);
                    Toast.makeText(LnFActivity.this, "No Feeds at this moment", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<LnFResponse> call, @NonNull Throwable t) {
                Toast.makeText(LnFActivity.this, "Failed to fetch Data!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}