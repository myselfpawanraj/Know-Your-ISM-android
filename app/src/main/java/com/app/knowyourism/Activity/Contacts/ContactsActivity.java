package com.app.knowyourism.Activity.Contacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.knowyourism.Activity.Login.LoginActivity;
import com.app.knowyourism.Adapter.ClubAdapter;
import com.app.knowyourism.Adapter.ContactAdapter;
import com.app.knowyourism.Model.Club;
import com.app.knowyourism.Model.Contacts;
import com.app.knowyourism.R;
import com.app.knowyourism.Utilities.CustomWeb;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactsActivity extends AppCompatActivity {
    @BindView(R.id.recycler_club)
    RecyclerView recyclerView;
    @BindView(R.id.toolbar2)
    Toolbar toolbar;
    ContactAdapter adapter;
    List< Contacts > contacts = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        adapter=new ContactAdapter(ContactsActivity.this);
        recyclerView.setAdapter(adapter);

        getData();
    }

    private void getData() {

        Contacts club = new Contacts();

        contacts.add(club);
        contacts.add(club);
        contacts.add(club);
        contacts.add(club);
        contacts.add(club);

        adapter.setData(contacts);
        adapter.notifyDataSetChanged();
    }
    @Override
    public boolean onSupportNavigateUp() {
        super.onBackPressed();
        return super.onSupportNavigateUp();
    }
}