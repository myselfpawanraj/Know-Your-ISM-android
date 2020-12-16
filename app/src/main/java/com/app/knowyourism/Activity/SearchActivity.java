package com.app.knowyourism.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.app.knowyourism.Adapter.StudentResultAdapter;
import com.app.knowyourism.Api.ResultApi;
import com.app.knowyourism.Model.Result;
import com.app.knowyourism.Model.Student;
import com.app.knowyourism.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    ProgressBar progressBar;
    androidx.appcompat.widget.Toolbar toolbar;
    String name,city,state,admnno;
    List<Student> studentList;
    StudentResultAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_search );
        progressBar=findViewById( R.id.progressBar );
        toolbar= (androidx.appcompat.widget.Toolbar) findViewById( R.id.toolbar );
        toolbar.setVisibility( View.GONE );
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i= getIntent();
        name=i.getExtras().getString( "Name" ,null);
        city=i.getExtras().getString( "City" ,null);
        admnno=i.getExtras().getString( "Admnno" ,null);
        fillExampleList();
    }
    private void fillExampleList() {
        Call<Result> postList;
        studentList = new ArrayList<>();

        postList =  ResultApi.getService().getStudents(null,null,name,null,null,admnno);
        postList.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                studentList = response.body().getStudents();
                progressBar.setVisibility( View.GONE );
                toolbar.setVisibility( View.VISIBLE );
                toolbar.collapseActionView();
                setUpRecyclerView();
                Toast.makeText(getApplicationContext(), studentList.size()+" Result!",
                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "No Result!",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setUpRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new StudentResultAdapter(studentList,SearchActivity.this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}