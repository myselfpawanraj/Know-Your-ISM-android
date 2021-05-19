package com.app.knowyourism.Activity.Clubs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.knowyourism.Activity.Login.LoginActivity;
import com.app.knowyourism.Activity.LostAndFound.LnFActivity;
import com.app.knowyourism.Adapter.ClubAdapter;
import com.app.knowyourism.Api.ResultApi;
import com.app.knowyourism.Model.Club;
import com.app.knowyourism.Model.ClubsResponse;
import com.app.knowyourism.Model.LnF.LnFResponse;
import com.app.knowyourism.R;
import com.app.knowyourism.Utilities.CustomWeb;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClubActivity extends AppCompatActivity implements Contract {
    @BindView(R.id.recycler_club)
    RecyclerView recyclerView;
    @BindView(R.id.toolbar2)
    Toolbar toolbar;
    ClubAdapter adapter;
    List<Club> clubList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        adapter=new ClubAdapter(ClubActivity.this,this);
//        recyclerView.setHasFixedSize(true);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        getData();
    }

    private void getData() {
        ResultApi.getService().getClubs().enqueue(new Callback< ClubsResponse >() {
            @Override
            public void onResponse(@NonNull Call< ClubsResponse > call, @NonNull Response<ClubsResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getRecords() != null) {
                    clubList = response.body().getRecords();
                    adapter.setData(clubList);
                } else {
                    Toast.makeText(ClubActivity.this, "No Feeds at this moment", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ClubsResponse> call, @NonNull Throwable t) {
                Toast.makeText(ClubActivity.this, "Failed to fetch Data!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void showDialog(Club club) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        View view = LayoutInflater.from(this).inflate(R.layout.club_bottom_dialogue, (ConstraintLayout) findViewById(R.id.constraint_layout));
        TextView title = view.findViewById(R.id.text_name);
        TextView desc = view.findViewById(R.id.text_desc);
        ImageView img = view.findViewById(R.id.imageView6);
        CardView fb = view.findViewById(R.id.card_fb);
        CardView ig = view.findViewById(R.id.card_insta);

        title.setText(club.getName());
        desc.setText(club.getDescription());
        Glide
                .with(this)
                .load(club.getPhotoUrl())
                .placeholder(R.drawable.ic_account_circle_black_24dp)
                .into(img);

        CustomWeb customWeb = new CustomWeb(this);
        fb.setOnClickListener(view1 -> customWeb.openTab(club.getFacebook()));
        ig.setOnClickListener(view1 -> customWeb.openTab(club.getIg()));

        if(club.getIg() == null) ig.setVisibility(View.GONE);

        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        super.onBackPressed();
        return super.onSupportNavigateUp();
    }
}