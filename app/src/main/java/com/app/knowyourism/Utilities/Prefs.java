package com.app.knowyourism.Utilities;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class Prefs {

    public static boolean isUserLoggedIn(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(HelperClass.MY_PREFS_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("isUserLoggedIn", false);
    }

    public static void setToken(Context context, String token) {
        SharedPreferences.Editor editor = context.getSharedPreferences(HelperClass.MY_PREFS_NAME, Context.MODE_PRIVATE).edit();
        editor.putString("jwtToken", token);
        editor.apply();
    }

//    public static void SetUserData(Context context, UserModel user) {
//        SharedPreferences.Editor editor = context.getSharedPreferences(HelperClass.MY_PREFS_NAME, Context.MODE_PRIVATE).edit();
//        Gson gson = new Gson();
//        String json = gson.toJson(user);
//        editor.putString("user", json);
//        editor.apply();
//    }

    public static void setUserLoggedIn(Context context, boolean b) {
        SharedPreferences.Editor editor = context.getSharedPreferences(HelperClass.MY_PREFS_NAME, Context.MODE_PRIVATE).edit();
        editor.putBoolean("isUserLoggedIn", b);
        editor.apply();

        if (!b) {
            editor.clear();
            editor.apply();
        }
    }

    public static String getToken(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(HelperClass.MY_PREFS_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString("jwtToken", null);
    }

//    public static UserModel getUser(Context context) {
//        SharedPreferences sharedPreferences = context.getSharedPreferences(HelperClass.MY_PREFS_NAME, Context.MODE_PRIVATE);
//        Gson gson = new Gson();
//        String json = sharedPreferences.getString("user", null);
//        return gson.fromJson(json, UserModel.class);
//    }

    public static void setFirstUse(Context context, boolean bool) {
        Log.i("FirstChange ", bool + "");
        SharedPreferences.Editor editor = context.getSharedPreferences(HelperClass.MY_PREFS_NAME, Context.MODE_PRIVATE).edit();
        editor.putBoolean("first", bool);
        editor.apply();
    }

    public static boolean getFirstUse(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(HelperClass.MY_PREFS_NAME, Context.MODE_PRIVATE);
        Log.i("GetFirstChange ", sharedPreferences.getBoolean("first", true) + "");
        return sharedPreferences.getBoolean("first", true);
    }
}
