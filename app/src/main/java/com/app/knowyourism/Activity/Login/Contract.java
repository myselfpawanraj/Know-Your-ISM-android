package com.app.knowyourism.Activity.Login;

import com.app.knowyourism.Model.LoginBody;
import com.app.knowyourism.Model.OtpInitiateBody;

public class Contract {

    interface View {

        void onError(String body, int code);

        void onSuccessOTP(String body);

        void onSuccess(String body);

    }

    interface Presenter {

        void getUserOTP(OtpInitiateBody request);

        void loginUser(LoginBody request);
    }
}
