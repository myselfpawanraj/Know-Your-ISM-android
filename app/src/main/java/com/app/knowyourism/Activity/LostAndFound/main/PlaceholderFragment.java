package com.app.knowyourism.Activity.LostAndFound.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.knowyourism.Adapter.LostFoundAdapter;
import com.app.knowyourism.Model.LnF.LostFound;
import com.app.knowyourism.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    List<LostFound> lostFoundList;
    LostFoundAdapter adapter;
    TextView tv_no_post;
    ProgressBar progressBar;
    RecyclerView recyclerView;
    public PlaceholderFragment() {
        this.lostFoundList = new ArrayList<>();
    }

    public void setData(List<LostFound> lostFoundList) {
        this.lostFoundList = lostFoundList;
        adapter.setData(lostFoundList);
        progressBar.setVisibility(View.GONE);
        if(lostFoundList.isEmpty()){
            tv_no_post.setVisibility(View.VISIBLE);
        }
    }
    @Override
    public View onCreateView( @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        adapter = new LostFoundAdapter(getContext());
        tv_no_post = v.findViewById(R.id.tv_no_post);
        progressBar = v.findViewById(R.id.progressBar4);

        recyclerView = v.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        return v;
    }
}