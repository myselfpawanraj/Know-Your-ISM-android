package com.app.knowyourism.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.knowyourism.Activity.Clubs.Contract;
import com.app.knowyourism.Model.Club;
import com.app.knowyourism.Model.LnF.LostFound;
import com.app.knowyourism.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClubAdapter extends RecyclerView.Adapter< ClubAdapter.Holder > {
    private List< Club > clubs = new ArrayList<>();
    private final Contract contract;
    private final Context context;

    public ClubAdapter(Context context, Contract contract) {
        this.contract = contract;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_club, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Club model = clubs.get(position);
        Glide
                .with(context)
                .load(model.getPhotoUrl())
                .placeholder(R.drawable.ic_account_circle_black_24dp)
                .into(holder.icon);
        holder.icon.setOnClickListener(v -> contract.showDialog(model));
    }

    @Override
    public int getItemCount() {
        return clubs.size();
    }

    public void setData(List< Club > newList) {
        clubs.clear();
        clubs.addAll(newList);
        this.notifyDataSetChanged();
    }


    public static class Holder extends RecyclerView.ViewHolder {
        ImageView icon;

        public Holder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.club_icon);
        }
    }
}
