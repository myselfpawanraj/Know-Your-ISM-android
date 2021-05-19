package com.app.knowyourism.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.knowyourism.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class ActivitySpy extends AppCompatActivity {
    Button buttonsearch;
    TextInputEditText editTextname,editTextcity,editTextadmnno;
    String name,city,state,admnno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_spy );
        buttonsearch= findViewById( R.id.buttonsearch );
        editTextname= findViewById( R.id.editTextname );
        editTextcity= findViewById( R.id.editTextcity );
        editTextadmnno= findViewById( R.id.editTextadmnno );

        buttonsearch.setOnClickListener(v -> {
            name=editTextname.getText().toString();
            city=editTextcity.getText().toString();
            admnno=editTextadmnno.getText().toString();

            if(name.isEmpty() && city.isEmpty() && admnno.isEmpty()){
                Toast.makeText( getApplication(),"Fill up at least one Query!",Toast.LENGTH_SHORT ).show();
                return;
            }
            Intent i = new Intent(getApplicationContext(),SearchActivity.class);
            i.putExtra( "Name", name );
            i.putExtra( "City", city );
            i.putExtra( "Admnno",admnno );
            startActivity( i );
        });
    }
}