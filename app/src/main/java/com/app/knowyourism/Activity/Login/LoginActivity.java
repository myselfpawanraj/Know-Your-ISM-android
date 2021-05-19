package com.app.knowyourism.Activity.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.app.knowyourism.Activity.ForgetPass.ForgetPassActivity;
import com.app.knowyourism.Activity.MainActivity;
import com.app.knowyourism.Model.LoginBody;
import com.app.knowyourism.Model.OtpInitiateBody;
import com.app.knowyourism.R;
import com.app.knowyourism.Utilities.Prefs;
import com.google.android.material.textfield.TextInputLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class LoginActivity extends AppCompatActivity implements Contract.View {

    @BindView(R.id.textInputLayout)
    TextInputLayout textInputEmail;
    @BindView(R.id.button_sign_in)
    CardView buttonSignIn;
    @BindView(R.id.textViewForgotPassword)
    TextView textViewForgotPassword;

    ProgressBar progressBar;
    Button buttonOtp;
    String userID;
    AlertDialog alertDialog;
    LoginPresenter presenter;
    Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_login);

        unbinder = ButterKnife.bind(this);
        presenter = new LoginPresenter( this, LoginActivity.this);

        findViewById(R.id.button_sign_in).setOnClickListener(v -> {
            String email = textInputEmail.getEditText().getText().toString().trim();

            if (email.isEmpty()) {
                textInputEmail.setError("Email is Required");
                textInputEmail.requestFocus();
                return;
            }
            else{
                textInputEmail.setError(null);
            }

            presenter.getUserOTP(new OtpInitiateBody(email,"instituteEmail"));
            showProgressDialogue();
        });

        textViewForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, ForgetPassActivity.class));
            }
        });

        setUpUI();
    }

    private void setUpUI() {
        textInputEmail.setError(null);
        textInputEmail.getEditText().setText(null);
    }

    private void showProgressDialogue() {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.new_dialogue, null);
        builder.setView(view);


        alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();

        alertDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        alertDialog.getWindow().setBackgroundDrawable(null);
        alertDialog.getWindow().setGravity(Gravity.BOTTOM);
    }


    private void showOtpDialogue() {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.dialogue_otp, null);

        builder.setView(view);

        alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();

        buttonOtp = view.findViewById(R.id.dismissButton2);
        progressBar = view.findViewById(R.id.progressBar2);
        TextInputLayout textInputLayout = view.findViewById(R.id.textInputLayout2);
        view.findViewById(R.id.dismissButton).setOnClickListener(view1 -> {
            if(alertDialog!=null)
                alertDialog.dismiss();
        });
        buttonOtp.setOnClickListener(view1 -> {
            String otp = textInputLayout.getEditText().getText().toString().trim();

            if(otp.length() != 6){
                textInputLayout.setError("OTP length must me 6");
                return;
            }
            else{
                textInputLayout.setError(null);
            }

            buttonOtp.setEnabled(false);
            presenter.loginUser(new LoginBody(userID, Long.parseLong(otp)));
            progressBar.setVisibility(View.VISIBLE);
        });

    }
    @Override
    public void onError(String message, int code) {
        if(code == 1){
            if(alertDialog!=null)
                alertDialog.dismiss();
            showOtpDialogue();
        }
        else{
            if(buttonOtp!=null){
                buttonOtp.setEnabled(true);
            }
            if(progressBar!=null){
                progressBar.setVisibility(View.GONE);
            }
        }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccessOTP(String userID) {
        if(alertDialog!=null)
            alertDialog.dismiss();

        this.userID = userID;
        showOtpDialogue();
    }

    @Override
    public void onSuccess(String userID) {
        if(alertDialog!=null)
            alertDialog.dismiss();

        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.putExtra("userID", userID);
        startActivity(intent);
        Prefs.setUserLoggedIn(this, true);
        finish();
    }
}