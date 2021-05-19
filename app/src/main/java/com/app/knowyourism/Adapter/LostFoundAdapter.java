package com.app.knowyourism.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.app.knowyourism.Model.Feed.Feed;
import com.app.knowyourism.Model.LnF.LostFound;
import com.app.knowyourism.R;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class LostFoundAdapter extends RecyclerView.Adapter< LostFoundAdapter.ViewHolder > {
    List< LostFound > postList = new ArrayList<>();
    Context context;

    public LostFoundAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.card_lost, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int position) {
        LostFound lostFound = postList.get(position);
        h.textTitle.setText(lostFound.getTitle());
        h.textDesc.setText(lostFound.getDetails());
        String s = lostFound.getDate();
        String d = s.substring(11,16) + "\n" + s.substring(0,10);
        h.textDate.setText(d);

        Glide
                .with(context)
                .load(lostFound.getUrl())
                .placeholder(R.drawable.ic_baseline_luggage_24)
                .into(h.imageView);

        h.textUser.setOnClickListener(v -> Toast.makeText(context, "Clicked on User", Toast.LENGTH_SHORT).show());
    }

    public void setData(List<LostFound> newList) {
        postList.clear();
        postList.addAll(newList);
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textTitle = itemView.findViewById(R.id.textviewname),
                textDesc = itemView.findViewById(R.id.textviewbranch),
                textUser = itemView.findViewById(R.id.textviewBranch2),
                textDate = itemView.findViewById(R.id.textDate);
        ImageView imageView = itemView.findViewById(R.id.imageavatar);

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
