package com.app.knowyourism.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.app.knowyourism.Activity.MainActivity;
import com.app.knowyourism.Activity.ProfileActivity;
import com.app.knowyourism.Activity.SearchActivity;
import com.app.knowyourism.Model.Student;
import com.app.knowyourism.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class StudentResultAdapter extends RecyclerView.Adapter<StudentResultAdapter.ViewHolder> {
    List<Student> postList;
    Context context;
    String linkmmale="https://image.freepik.com/free-vector/vector-avatar-smiling-man-facial-expression_102172-203.jpg",
    linkfemale="https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTZA_wIwT-DV4G3E3jdNZScRLQnH4faqTH2a7PrNwlhqP4W1Zjh&usqp=CAU";
    public StudentResultAdapter(List<Student> postList, Context context) {
        this.postList = postList;
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
        Student student = postList.get( position );
        h.textviewname.setText( student.getName() );
        h.textviewbranch.setText( student.getDepartment().equals( "" )?"NA":student.getDepartment() );
        h.textviewplace.setText( "Dhanbad"+", "+"Jharkhand" );
        //h.textviewplace.setText( student.getCity()+", "+student.getState() );

        if(!student.getPhotoURL().isEmpty()) {
            Picasso picasso = new Picasso.Builder( context )
                    .listener( new Picasso.Listener() {
                        @Override
                        public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                        }
                    } )
                    .build();
            picasso.load( student.getPhotoURL() )
                    .fit()
                    .into( h.imageView );
        }
        else{
            Picasso.get().load( (student.getSex().equals( "M" ) ? linkmmale : linkfemale) ).into( h.imageView );
        }
        h.cardview.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ProfileActivity.class);
                i.putExtra( "Admnno",student.getAdmissionNo() );
                context.startActivity(i);
            }
        } );
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textviewname=itemView.findViewById( R.id.textviewname ),
                textviewbranch=itemView.findViewById( R.id.textviewbranch ),
                textviewplace=itemView.findViewById( R.id.textviewplace );
        ImageView imageView=itemView.findViewById( R.id.imageavatar );
        ConstraintLayout cardview=itemView.findViewById( R.id.cardview );
        public ViewHolder(@NonNull View itemView) {

            super( itemView );
        }
    }
}
