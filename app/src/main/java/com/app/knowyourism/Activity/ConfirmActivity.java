package com.app.knowyourism.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.knowyourism.Api.ResultApi;
import com.app.knowyourism.Model.Result;
import com.app.knowyourism.Model.Student;
import com.app.knowyourism.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConfirmActivity extends AppCompatActivity {

    EditText editTextName, editTextAdmno;
    Button buttonFindMe;
    String name, admno, userid;
    List<Student> studentList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_confirm );
        editTextAdmno=findViewById( R.id.editTextAdmnno2 );
        editTextName=findViewById( R.id.editTextCity );
        buttonFindMe=findViewById( R.id.button);

        buttonFindMe.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name=editTextName.getText().toString();
                admno=editTextAdmno.getText().toString();
                getData();
            }
        } );
    }
    private void getData() {
        Call<Result> postList;
        studentList = new ArrayList<>();

        postList =  ResultApi.getService().getStudents(null,null,name,null,null,admno,null);
        postList.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
//                studentList = response.body().getStudents();
                if(studentList.size()==0){
                    Toast.makeText(getApplicationContext(), "No Result!",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                userid=studentList.get( 0 ).getId();
                name=studentList.get( 0 ).getName();
                admno=studentList.get( 0 ).getAdmissionNo();
                Toast.makeText(getApplicationContext(), "Welcome "+ studentList.get( 0 ).getName()+"!",
                        Toast.LENGTH_LONG).show();
                Intent i= new Intent(ConfirmActivity.this, UpdateProfileActivity.class );
                i.putExtra( "userid",userid );
                i.putExtra( "name", name );
                i.putExtra( "admnno", admno );
                startActivity( i );
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "No Result!",
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}