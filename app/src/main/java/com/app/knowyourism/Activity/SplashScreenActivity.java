package com.app.knowyourism.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.app.knowyourism.Activity.Clubs.ClubActivity;
import com.app.knowyourism.Activity.Contacts.ContactsActivity;
import com.app.knowyourism.Activity.Login.LoginActivity;
import com.app.knowyourism.Model.Club;
import com.app.knowyourism.R;
import com.app.knowyourism.Utilities.Prefs;


public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

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
            boolean isLoggedIn = Prefs.isUserLoggedIn(SplashScreenActivity.this);
            Intent i = null;
            if(isLoggedIn) {
                i = new Intent(SplashScreenActivity.this, LoginActivity.class);
            }
            else{
                i = new Intent(SplashScreenActivity.this, LoginActivity.class);
            }
            startActivity(i);
            SplashScreenActivity.this.finish();
        }

    }
}
