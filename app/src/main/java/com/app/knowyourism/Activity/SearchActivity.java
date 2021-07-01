package com.app.knowyourism.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.app.knowyourism.Adapter.StudentResultAdapter;
import com.app.knowyourism.Api.ResultApi;
import com.app.knowyourism.Model.Result;
import com.app.knowyourism.Model.Student;
import com.app.knowyourism.Model.User;
import com.app.knowyourism.R;
import com.arlib.floatingsearchview.FloatingSearchView;
import com.quinny898.library.persistentsearch.SearchBox;
import com.quinny898.library.persistentsearch.SearchResult;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    @BindView( R.id.progressBar )
    ProgressBar progressBar;
    @BindView( R.id.searchbox )
    FloatingSearchView search;
    @BindView( R.id.recycler )
    RecyclerView recyclerView;


    String name,city,state,admnno;
    List< User > studentList;
    StudentResultAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_search );
        ButterKnife.bind(this);

        progressBar=findViewById( R.id.progressBar );

        Intent i= getIntent();
        name=i.getExtras().getString( "Name" ,null);
        city=i.getExtras().getString( "City" ,null);
        admnno=i.getExtras().getString( "Admnno" ,null);
        fillExampleList();

        setUpRecyclerView();

        search.setSearchHint("Name, Department...");

        setUpRecyclerView();
        search.setOnQueryChangeListener((oldQuery, newQuery) -> {
            if(newQuery.equals(""))
                adapter.setData(studentList);
            else
                filterList(newQuery);
        });
    }
    private void fillExampleList() {
        Call<Result> postList;
        studentList = new ArrayList<>();

        postList =  ResultApi.getService().getStudents(null,null,name,null,null,admnno,"roles:STUDENT");
        postList.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                studentList = response.body().getStudents();
                progressBar.setVisibility( View.GONE );
                Toast.makeText(getApplicationContext(), studentList.size()+" Result!",
                        Toast.LENGTH_LONG).show();
                adapter.setData(studentList);
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
        adapter = new StudentResultAdapter(SearchActivity.this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        super.onBackPressed();
        return super.onSupportNavigateUp();
    }

    private void filterList(String s) {
        List<User> newList = new ArrayList<>();
        s = s.toLowerCase();

        for(User student : studentList){
            if(student.getAdmissionNumber()!=null && student.getAdmissionNumber().toLowerCase().contains(s)){
                newList.add(student);
            }
            else if(student.getName()!=null && student.getName().toLowerCase().contains(s)){
                newList.add(student);
            }
            else if(student.getDepartment()!=null && student.getDepartment().toLowerCase().contains(s)){
                newList.add(student);
            }
        }

        adapter.setData(newList);
        if(newList.isEmpty())
            Toast.makeText(SearchActivity.this, "No results found", Toast.LENGTH_SHORT).show();
    }
}