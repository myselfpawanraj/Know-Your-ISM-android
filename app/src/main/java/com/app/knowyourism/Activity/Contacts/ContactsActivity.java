package com.app.knowyourism.Activity.Contacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.app.knowyourism.Activity.Login.LoginActivity;
import com.app.knowyourism.Activity.SearchActivity;
import com.app.knowyourism.Adapter.ClubAdapter;
import com.app.knowyourism.Adapter.ContactAdapter;
import com.app.knowyourism.Adapter.StudentResultAdapter;
import com.app.knowyourism.Api.ResultApi;
import com.app.knowyourism.Model.Club;
import com.app.knowyourism.Model.Contacts;
import com.app.knowyourism.Model.Result;
import com.app.knowyourism.Model.Student;
import com.app.knowyourism.Model.User;
import com.app.knowyourism.R;
import com.app.knowyourism.Utilities.CustomWeb;
import com.arlib.floatingsearchview.FloatingSearchView;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.quinny898.library.persistentsearch.SearchBox;
import com.quinny898.library.persistentsearch.SearchResult;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactsActivity extends AppCompatActivity {
    @BindView(R.id.recycler_club)
    RecyclerView recyclerView;
    @BindView( R.id.searchbox )
    FloatingSearchView search;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;


    ContactAdapter adapter;
    List< User > contacts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        ButterKnife.bind(this);

        search.setSearchHint("Name, Department...");
        fillExampleList();

        setUpRecyclerView();
        search.setOnQueryChangeListener((oldQuery, newQuery) -> {
            if(newQuery.equals(""))
                adapter.setData(contacts);
            else
                filterList(newQuery);
        });
    }
    private void fillExampleList() {
        Call< Result > postList;

        postList =  ResultApi.getService().getStudents(null,null,null,null,null,null,"roles:FACULTY");
        postList.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                contacts = response.body().getStudents();
                progressBar.setVisibility( View.GONE );
                Toast.makeText(getApplicationContext(), contacts.size()+" Result!",
                        Toast.LENGTH_LONG).show();
                adapter.setData(contacts);
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "No Result!",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setUpRecyclerView() {
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new ContactAdapter(ContactsActivity.this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        super.onBackPressed();
        return super.onSupportNavigateUp();
    }

    private void filterList(String s) {
        s = s.toLowerCase();
        List< User > newList = new ArrayList<>();

        for(User user : contacts){
            if(user.getName()!=null && user.getName().toLowerCase().contains(s)){
                newList.add(user);
            }
            else if(user.getDepartment()!=null && user.getDepartment().toLowerCase().contains(s)){ //TODO: add special like HOD, Dean, etc
                newList.add(user);
            }
        }

        adapter.setData(newList);
        if(newList.isEmpty())
            Toast.makeText(ContactsActivity.this, "No results found", Toast.LENGTH_SHORT).show();
    }
}