package com.app.knowyourism.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.knowyourism.Activity.Clubs.Contract;
import com.app.knowyourism.Model.Club;
import com.app.knowyourism.Model.Feed.Feed;
import com.app.knowyourism.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FeedAdapter extends RecyclerView.Adapter< FeedAdapter.Holder > {
    private List< Feed > feedList = new ArrayList<>();
    private final Context context;

    public FeedAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_feed, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Feed model = feedList.get(position);

        holder.text_title.setText(model.getTitle());
        holder.text_desc.setText(model.getContent());

        if(model.getUser() != null){
            holder.text_name.setText(model.getUser().getName());
            holder.text_desn.setText(model.getUser().getAdmissionNumber());
            /*
            holder.linear_profile.setOnClickListener(v -> contract.showDialog(model));

            Glide
                    .with(context)
                    .load(model.getUser().get)
                    .placeholder(R.drawable.ic_account_circle_black_24dp)
                    .into(holder.profile_icon);

            */
        }
        else {
            holder.linear_profile.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return feedList.size();
    }

    public void setData(List< Feed > newList) {
        feedList.clear();
        feedList.addAll(newList);
        this.notifyDataSetChanged();
    }

    public static class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_title)
        TextView text_title;
        @BindView(R.id.text_desc)
        TextView text_desc;
        @BindView(R.id.text_name)
        TextView text_name;
        @BindView(R.id.text_desn)
        TextView text_desn;
        @BindView(R.id.profile_icon)
        ImageView profile_icon;
        @BindView(R.id.linear_profile)
        LinearLayout linear_profile;

        public Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
