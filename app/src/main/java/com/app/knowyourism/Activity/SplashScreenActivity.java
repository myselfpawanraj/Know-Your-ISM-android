package com.app.knowyourism.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.app.knowyourism.R;


public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView( R.layout.activity_splash_screen);
        LogoLauncher logoLauncher = new LogoLauncher();
        logoLauncher.start();

    }

    private class LogoLauncher extends Thread{
        public void run(){
            try{
                sleep(1500);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
            int isLoggedIn = SplashScreenActivity.this.getSharedPreferences("SHARED_PREFS", MODE_PRIVATE).getInt("LOGIN_STATUS", 0);
            Intent i = null;
            switch (isLoggedIn){
                case 1: i = new Intent(SplashScreenActivity.this, MainActivity.class);
                    break;
                case 0: i = new Intent(SplashScreenActivity.this, ConfirmActivity.class);
                    break;
            }
            startActivity(i);
            SplashScreenActivity.this.finish();
        }

    }
}
