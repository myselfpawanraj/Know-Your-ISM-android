package com.app.knowyourism.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.knowyourism.Api.ResultApi;
import com.app.knowyourism.Model.Result;
import com.app.knowyourism.Model.Student;
import com.app.knowyourism.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
    List<Student> studentList;
    Student student;
    TextView textviewname, textviewbranch, textviewplace,textviewadmnno,textViewfacebook;
    ImageView imageView;
    String linkmmale="https://image.freepik.com/free-vector/vector-avatar-smiling-man-facial-expression_102172-203.jpg",
            linkfemale="https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTZA_wIwT-DV4G3E3jdNZScRLQnH4faqTH2a7PrNwlhqP4W1Zjh&usqp=CAU";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_profile );
        getData();

        textviewname=findViewById( R.id.textviewname );
        textviewadmnno=findViewById( R.id.textviewadmnno );
        textviewplace=findViewById( R.id.textviewplace );
        textviewbranch=findViewById( R.id.textviewbranch );
        textViewfacebook=findViewById( R.id.facebook );
        imageView=findViewById( R.id.imageView4 );

    }
    private void getData() {
        Call<Result> postList;
        studentList = new ArrayList<>();

        postList =  ResultApi.getService().getStudents(null,null,null,null,null,getIntent().getExtras().getString( "Admnno" ).toString());
        postList.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                studentList = response.body().getStudents();
                student=studentList.get( 0 );
                Toast.makeText(getApplicationContext(), "Successful",
                        Toast.LENGTH_LONG).show();
                textviewname.setText( student.getName() );
                textviewname.setText( student.getName() );
                textviewadmnno.setText( student.getAdmissionNo() );
                textViewfacebook.setText( "@ "+student.getFacebook() );
                textviewplace.setText( "Dhanbad"+", "+"Jharkhand" );

                String branch=student.getCourse()+" "+student.getDepartment();
                if(branch.equals(" ")) branch="Not Available :(";
                textviewbranch.setText( branch);
                textviewplace.setText((student.getCity().isEmpty()?"NA":student.getCity())+", "+ (student.getState().isEmpty()?"NA":student.getState()) );

                if(!student.getPhotoURL().isEmpty()) {
                    Picasso picasso = new Picasso.Builder(ProfileActivity.this )
                            .listener( new Picasso.Listener() {
                                @Override
                                public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                                }
                            } )
                            .build();
                    picasso.load( student.getPhotoURL() )
                            .fit()
                            .into( imageView );
                }
                else{
                    Picasso.get().load( (student.getSex().equals( "M" ) ? linkmmale : linkfemale) ).into( imageView );
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "No Result!",
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}