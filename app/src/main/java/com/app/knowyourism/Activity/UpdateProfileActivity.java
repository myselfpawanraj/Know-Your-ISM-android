package com.app.knowyourism.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.knowyourism.Api.FacebookApi;
import com.app.knowyourism.Api.ResultApi;
import com.app.knowyourism.Model.Result;
import com.app.knowyourism.Model.Student;
import com.app.knowyourism.Model.UserImageData;
import com.app.knowyourism.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateProfileActivity extends AppCompatActivity {

    TextView textViewName, textViewAdmnno, textViewFacebook;
    EditText editDepartment, editTextAdress, editTextID;
    Button buttonSave;
    ImageView imageView;
    String admno, city, ID,imageLink, userid,name,department;
    Student student;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_profile2 );

        userid=getIntent().getStringExtra( "userid" );
        name=getIntent().getStringExtra( "name" );
        admno=getIntent().getStringExtra( "admnno" );


        textViewName=findViewById( R.id.editTextName );
        textViewAdmnno=findViewById( R.id.editTextAdmnno );
        textViewFacebook=findViewById( R.id.textView10 );
        editDepartment=findViewById( R.id.editTextDepartment );
        editTextAdress= (EditText) findViewById( R.id.editTextCity2 );
        editTextID= (EditText) findViewById( R.id.editTextFbID );
        buttonSave=findViewById( R.id.buttonSave );
        imageView=findViewById( R.id.imageView9 );

        textViewName.setText( name );
        textViewAdmnno.setText( admno );

        textViewFacebook.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://findmyfbid.in/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        } );

        buttonSave.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editDepartment.getText().toString().isEmpty() || editTextAdress.getText().toString().isEmpty() || editTextID.getText().toString().isEmpty() )
                {
                    Toast.makeText(getApplicationContext(), "Field Missing!",
                            Toast.LENGTH_LONG).show();
                    return;

                }
                department=editDepartment.getText().toString();
                city=editTextAdress.getText().toString();
                ID=editTextID.getText().toString();
                getImage(ID);
            }
        } );



    }
    private void getImage(String fbId) {
        Call<UserImageData> postList;
        postList =  FacebookApi.getService().getImageLink( fbId );
        postList.enqueue(new Callback<UserImageData>() {
            @Override
            public void onResponse(Call<UserImageData> call, Response<UserImageData> response) {
                imageLink = response.body().getData().getUrl();
                Picasso.Builder builder = new Picasso.Builder( UpdateProfileActivity.this );
                builder.listener( new Picasso.Listener() {
                    @Override
                    public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {

                    }
                } ); Picasso picasso = builder
                        .build();
                picasso.load(imageLink)
                        .fit()
                        .into(imageView);
                uploadData();
            }
            @Override
            public void onFailure(Call<UserImageData> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "No Result!",
                        Toast.LENGTH_LONG).show();
            }
        });
    }
    private void uploadData() {
        student=new Student();
        student.setDepartment( department );
        student.setCity( city );
        student.setPhotoURL(imageLink);

        Call<Student> postList;
        postList =  ResultApi.getService().updateStudents(userid,student);
        postList.enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {
                Toast.makeText(getApplicationContext(), "Successfully Updated!",
                        Toast.LENGTH_LONG).show();
                UpdateProfileActivity.this.getSharedPreferences("SHARED_PREFS", MODE_PRIVATE).edit().putInt("LOGIN_STATUS", 1).apply();
                startActivity( new Intent(UpdateProfileActivity.this,MainActivity.class) );
            }

            @Override
            public void onFailure(Call<Student> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failed!",
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}