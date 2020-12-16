package com.app.knowyourism.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.app.knowyourism.Model.Location.LocationResult;
import com.app.knowyourism.R;

public class MainActivity extends AppCompatActivity {
    CardView cardViewspy,cardViewmap, cardViewother, cardViewstudy;
    TextView updateProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor( getResources().getColor(android.R.color.white) );
        }
        cardViewspy= findViewById( R.id.cardViewSpy );
        cardViewstudy=findViewById( R.id.cardViewStudy );
        cardViewmap=findViewById( R.id.cardViewMap );
        cardViewstudy.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://examupdates.in/b-tech-books/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData( Uri.parse(url));
                startActivity(i);

            }
        } );
        cardViewspy.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(MainActivity.this,ActivitySpy.class) );
            }
        } );
        updateProfile= findViewById( R.id.textViewUpdate );
        updateProfile.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(MainActivity.this, ConfirmActivity.class) );
            }
        } );
        cardViewmap.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(MainActivity.this, LocationActivity.class) );
            }
        } );
    }
}