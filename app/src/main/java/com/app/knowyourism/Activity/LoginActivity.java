package com.app.knowyourism.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.app.knowyourism.R;
import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

//TODO: add implements Contract.View
public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.textInputLayout)
    TextInputLayout textInputEmail;
    @BindView(R.id.textInputLayout2)
    TextInputLayout textInputPassword;
    @BindView(R.id.button_sign_in)
    CardView buttonSignIn;
    @BindView(R.id.textViewForgotPassword)
    TextView textViewForgotPassword;
    @BindView(R.id.progress_bar)
    CardView progressBar;

//    LoginPresenter presenter;
    Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        unbinder = ButterKnife.bind(this);
//        try {
//            presenter = new LoginPresenter(this, LoginActivity.this);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        findViewById(R.id.button_sign_in).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = textInputEmail.getEditText().getText().toString().trim();
                String password = textInputPassword.getEditText().getText().toString().trim();

                // Not Mandatory for now that username is email

                if (email.isEmpty()) {
                    textInputEmail.setError("Email is Required");
                    textInputEmail.requestFocus();
                    return;
                }
                else{
                    textInputEmail.setError(null);
                }

                if (password.isEmpty()) {
                    textInputPassword.setError("Password is Required");
                    textInputPassword.requestFocus();
                    return;
                }
                else{
                    textInputPassword.setError(null);
                }

                startActivity(new Intent(LoginActivity.this,MainActivity.class));
//                if (currentState == 1) {
//
//                    UsernamePasswordAuthenticationRequest request = new UsernamePasswordAuthenticationRequest(email, password);
//                    presenter.loginUser(request);
//                } else if (currentState == 2) {
//                    presenter.resetPassword((long) Integer.parseInt(email));
//                }
//
//                HelperClass.showProgressbar(progressBar);
            }
        });

        textViewForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,ForgetPassActivity.class));
            }
        });

        setUpUI();
    }

    private void setUpUI() {
        textInputEmail.setError(null);
        textInputEmail.getEditText().setText(null);

        textInputPassword.setError(null);
        textInputPassword.getEditText().setText(null);
    }

//    @Override
//    public void onError(String message) {
//        HelperClass.hideProgressbar(progressBar);
//        HelperClass.toast(this, message);
//    }

//    @Override
//    public void onSuccess(LoginResponse body) {
//        HelperClass.hideProgressbar(progressBar);
//        HelperClass.toast(this, "Login Success!!");
//        UserModel userModel = body.getUser();
//        Prefs.setToken(LoginActivity.this, body.getToken());
//        Prefs.SetUserData(LoginActivity.this, userModel);
//        Prefs.setPermissions(LoginActivity.this, body.getPermissions());
//
//        if (body.getShouldPasswordReset()) {
//            Intent intent = new Intent(LoginActivity.this, PasswordChangeActivity.class);
//            intent.putExtra("password_type", 1);
//            intent.putExtra("is_login_intent", true);
//            intent.putExtra("should_transaction_password_reset", body.getShouldTransactionPasswordReset());
//            startActivity(intent);
//            finish();
//            return;
//        }
//
//        if (body.getShouldTransactionPasswordReset()) {
//            Intent intent = new Intent(LoginActivity.this, PasswordChangeActivity.class);
//            intent.putExtra("password_type", 2);
//            intent.putExtra("is_login_intent", true);
//            intent.putExtra("should_transaction_password_reset", body.getShouldTransactionPasswordReset());
//            startActivity(intent);
//            finish();
//            return;
//        }
//
//        Prefs.setUserLoggedIn(LoginActivity.this, true);
//        startActivity(new Intent(LoginActivity.this, MainActivity.class));
//        finish();
//    }

//    @Override
//    public void onPasswordReset(UserModel body) {
//        HelperClass.toast(this, "Password Rested Check your Email");
//        HelperClass.hideProgressbar(progressBar);
//    }
}