package com.app.knowyourism.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.app.knowyourism.R;

public class OtherStuffActivity extends AppCompatActivity implements View.OnClickListener {
    CardView c1,c2,c3,c4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_stuff);
        c1 = findViewById(R.id.card_search);
        c2 = findViewById(R.id.card_shorts);
        c3 = findViewById(R.id.card_blogs);
        c4 = findViewById(R.id.card_developers);

        c1.setOnClickListener(this);
        c2.setOnClickListener(this);
        c3.setOnClickListener(this);
        c4.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String link = "https://github.com/Know-Your-ISM/";
        switch (view.getId()){
            case R.id.card_search :
                link = "https://kyism.ga/";
                break;
            case R.id.card_shorts :
                link = "https://kyism.ga/shorts";
                break;
            case R.id.card_blogs :
                link = "https://kyism.ga/blog";
                break;
            case R.id.card_developers :
                link = "https://github.com/Know-Your-ISM/";
                break;
        }
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(link));
        startActivity(i);
    }
}