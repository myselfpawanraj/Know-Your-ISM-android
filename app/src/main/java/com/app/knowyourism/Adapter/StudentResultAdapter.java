package com.app.knowyourism.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.app.knowyourism.Activity.ProfileActivity;
import com.app.knowyourism.Model.Student;
import com.app.knowyourism.Model.User;
import com.app.knowyourism.R;
import com.app.knowyourism.Utilities.HelperClass;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class StudentResultAdapter extends RecyclerView.Adapter<StudentResultAdapter.ViewHolder> {
    List<User> postList = new ArrayList<>();
    Context context;
    String linkmmale="https://image.freepik.com/free-vector/vector-avatar-smiling-man-facial-expression_102172-203.jpg",
    linkfemale="https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTZA_wIwT-DV4G3E3jdNZScRLQnH4faqTH2a7PrNwlhqP4W1Zjh&usqp=CAU";
    public StudentResultAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from( parent.getContext() )
                .inflate( R.layout.card_student, parent, false );
        return new ViewHolder( v );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int position) {
        User student = postList.get( position );
        h.textviewname.setText( student.getName() );
        h.textviewbranch.setText( student.getDepartment()==null?"NA":student.getDepartment() );
        h.textviewplace.setText( "Dhanbad"+", "+"Jharkhand" );
        //h.textviewplace.setText( student.getCity()+", "+student.getState() );

        Glide
                .with(context)
                .load(student.getPhotoURI())
                .placeholder(R.drawable.ic_account_circle_black_24dp)
                .into(h.imageView);

        h.cardview.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ProfileActivity.class);
                i.putExtra(HelperClass.USER,student);
                context.startActivity(i);
            }
        } );
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public void setData(List< User > newList) {
        postList.clear();
        postList.addAll(newList);
        this.notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textviewname=itemView.findViewById( R.id.textviewname ),
                textviewbranch=itemView.findViewById( R.id.textviewbranch ),
                textviewplace=itemView.findViewById( R.id.textviewBranch);
        ImageView imageView=itemView.findViewById( R.id.imageavatar );
        ConstraintLayout cardview=itemView.findViewById( R.id.cardview );
        public ViewHolder(@NonNull View itemView) {

            super( itemView );
        }
    }
}
