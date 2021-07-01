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
import com.app.knowyourism.Model.User;
import com.app.knowyourism.R;
import com.app.knowyourism.Utilities.HelperClass;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
    @BindView(R.id.imageView4) ImageView imageView;
    @BindView(R.id.textviewname) TextView name;
    @BindView(R.id.textviewadmnno) TextView admNo;
    @BindView(R.id.textviewBranch) TextView branchText;

    User student;
    String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);

        student = (User) getIntent().getSerializableExtra(HelperClass.USER);
        userId = getIntent().getStringExtra(HelperClass.USER_ID);

        if(student == null) {
            getData(userId);
        }
        else {
            setData();
        }
    }

    private void setData() {
        name.setText(student.getName());
        name.setText(student.getName());
        admNo.setText(student.getAdmissionNumber());
        String branch = student.getCourse() + " " + student.getDepartment();
        if (branch.equals(" ")) {
            branch = "Not Available";
        }
        branchText.setText(branch);

        Glide
                .with(this)
                .load(student.getPhotoURI())
                .placeholder(R.drawable.ic_account_circle_black_24dp)
                .into(imageView);
    }

    private void getData(String userId) {
        ResultApi.getService().getUser(userId).enqueue(new Callback< User >() {
            @Override
            public void onResponse(Call< User > call, Response< User > response) {
                if(response.isSuccessful() && response.body()!=null){
                    student = response.body();
                    setData();
                }
                else{
                    Toast.makeText(ProfileActivity.this, "User not Available", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call< User > call, Throwable t) {
                Toast.makeText(ProfileActivity.this, "Unexpected error!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}