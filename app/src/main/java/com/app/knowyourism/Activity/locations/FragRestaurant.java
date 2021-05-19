package com.app.knowyourism.Activity.locations;

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

import com.app.knowyourism.Adapter.RestaurantResultAdapter;
import com.app.knowyourism.Api.ResultApi;
import com.app.knowyourism.Model.Restaurant.Location2;
import com.app.knowyourism.Model.Restaurant.Restaurant;
import com.app.knowyourism.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragRestaurant extends Fragment {

    ProgressBar progressBar;
    List<Location2> studentList;
    RestaurantResultAdapter adapter;
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
        Call<Restaurant> postList;
        studentList = new ArrayList<>();

        postList =  ResultApi.getService().getRestaurant();
        postList.enqueue(new Callback<Restaurant>() {
            @Override
            public void onResponse(Call<Restaurant> call, Response<Restaurant> response) {
                studentList = response.body().getLocations();
                progressBar.setVisibility( View.GONE );
                setUpRecyclerView();
            }

            @Override
            public void onFailure(Call<Restaurant> call, Throwable t) {
                Toast.makeText(getContext(), "No Result!",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setUpRecyclerView() {
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        adapter = new RestaurantResultAdapter(studentList, getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
