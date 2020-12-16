package com.app.knowyourism.LocationFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.knowyourism.Activity.LocationActivity;
import com.app.knowyourism.Adapter.LocationResultAdapter;
import com.app.knowyourism.Api.ResultApi;
import com.app.knowyourism.Model.Location.Location;
import com.app.knowyourism.Model.Location.LocationResult;
import com.app.knowyourism.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragLocation extends Fragment {

    ProgressBar progressBar;
    List<Location> studentList;
    LocationResultAdapter adapter;
    RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate( R.layout.fragment_location, container, false);
        recyclerView = v.findViewById(R.id.recycler);
        progressBar = v.findViewById( R.id.progressBar2 );
        fillExampleList();
        return v;
    }
    private void fillExampleList() {
        Call<LocationResult> postList;
        studentList = new ArrayList<>();

        postList =  ResultApi.getService().getLocation();
        postList.enqueue(new Callback<LocationResult>() {
            @Override
            public void onResponse(Call<LocationResult> call, Response<LocationResult> response) {
                studentList = response.body().getLocations();
                progressBar.setVisibility( View.GONE );
                setUpRecyclerView();
                Toast.makeText(getActivity(), studentList.size()+" Result!",
                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<LocationResult> call, Throwable t) {
                Toast.makeText(getContext(), "No Result!",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setUpRecyclerView() {
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        adapter = new LocationResultAdapter(studentList, getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
