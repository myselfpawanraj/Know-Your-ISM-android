package com.app.knowyourism.Activity.Login;

import android.content.Context;
import android.util.Log;

import com.app.knowyourism.Api.ResultApi;
import com.app.knowyourism.Model.LoginBody;
import com.app.knowyourism.Model.LoginResponse;
import com.app.knowyourism.Model.OtpInitiateBody;
import com.app.knowyourism.Model.OtpInitiateResponse;
import com.app.knowyourism.Model.Student;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements Contract.Presenter {
    Contract.View view;
    ResultApi.PostService retrofitNetworkApi;

    public LoginPresenter(Contract.View view, Context context) {
        this.view = view;
        retrofitNetworkApi = ResultApi.getService();
    }

    @Override
    public void getUserOTP(OtpInitiateBody request) {
        retrofitNetworkApi.putOtpRequest(request).enqueue(new Callback< OtpInitiateResponse >() {
            @Override
            public void onResponse(Call<OtpInitiateResponse> call, Response<OtpInitiateResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    view.onSuccessOTP(response.body().getUserId());
                } else {
                    view.onError(response.errorBody().toString(),1);
                }
            }

            @Override
            public void onFailure(Call<OtpInitiateResponse> call, Throwable t) {
                view.onError(t.getMessage(), 1);
            }
        });
    }

    @Override
    public void loginUser(LoginBody request) {
        retrofitNetworkApi.getLogin(request).enqueue(new Callback< LoginResponse >() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    view.onSuccess(response.body().getAccessToken());
                } else {
                    view.onError("Failed ! " + response.message(), 2);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.d("LOGIn", t.getMessage());
                view.onError("Failed ! " + t.getMessage(), 2);
            }
        });
    }
}
